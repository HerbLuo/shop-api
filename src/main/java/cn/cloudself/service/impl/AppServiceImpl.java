package cn.cloudself.service.impl;

import cn.cloudself.bean.EntitysWithVersion;
import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.*;
import cn.cloudself.exception.http.ServerException;
import cn.cloudself.model.*;
import cn.cloudself.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/3/28 HerbLuo 首次创建
 */
@Service
@Transactional
public class AppServiceImpl implements IAppService {

    private final IAppDao appDao;

    private final IAppSliderDao appSliderDao;

    private final IAppEntranceDao appEntranceDao;

    private final IAppHotbarDao appHotbarDao;

    private final IAppRushbuyDao appRushbuyDao;

    private final IAppJiyoujiaDao appJiyoujiaDao;

    private final IAppBlockDao appBlockDao;

    private final IAppJiyoujiaHeadDao appJiyoujiaHeadDao;

    @Autowired
    public AppServiceImpl(IAppDao appDao, IAppSliderDao appSliderDao, IAppEntranceDao appEntranceDao, IAppHotbarDao appHotbarDao, IAppRushbuyDao appRushbuyDao, IAppJiyoujiaDao appJiyoujiaDao, IAppBlockDao appBlockDao, IAppJiyoujiaHeadDao appJiyoujiaHeadDao) {
        this.appDao = appDao;
        this.appSliderDao = appSliderDao;
        this.appEntranceDao = appEntranceDao;
        this.appHotbarDao = appHotbarDao;
        this.appRushbuyDao = appRushbuyDao;
        this.appJiyoujiaDao = appJiyoujiaDao;
        this.appBlockDao = appBlockDao;
        this.appJiyoujiaHeadDao = appJiyoujiaHeadDao;
    }

    /**
     * 获取当前app的版本
     *
     * @return .
     */
    @Override
    public AppEntity getAppInfo() throws Exception {
        return appDao.getAppInfo();
    }

    /**
     * TODO 未完成
     * 获取轮播图内容
     *
     * @param userId 用户
     * @param size   大小
     * @return 轮播图s
     */
    @Override
    @ParamChecker(greaterThan0 = 1, lessThan = 20, lessThanIndex = 1)
    public Iterable<AppSliderEntity> getSilder(Integer userId, Integer size) throws Exception {

        List<Integer> ids = new ArrayList<>(8);
        for (int i = 1; i <= 8; i++) {
            ids.add(i);
        }

        return appSliderDao.findAll(ids);
    }

    /**
     * 获取app的十个入口
     *
     * @return .
     */
    @Override
    public EntitysWithVersion<AppEntranceEntity> getEntraceBar() throws Exception {

        Iterable<AppEntranceEntity> appEntranceEntities = appEntranceDao.findFirst10ByEnabledTrue();

        return new EntitysWithVersion<>(getAppInfo().getAppEntranceVersion(), appEntranceEntities);
    }

    /**
     * 获取hotBar的内容
     *
     * @return .
     */
    @Override
    public Iterable<AppHotbarEntity> getHotbar() throws Exception {
        return appHotbarDao.findAll();
    }

    /**
     * 获取抢购内容
     */
    @Override
    public Iterable<AppRushbuyEntity> getRushbuy() throws Exception {
        return appRushbuyDao.getRushbuy();
    }

    /**
     * 获取极有家的内容
     *
     * @param page start from 0
     */
    @Override
    @ParamChecker(greaterOrEqual0 = 0, greaterThan0 = 1)
    public AppBlockEntity getJiyoujia(Integer page, Integer aPageSize) throws Exception {

        Pageable pageable = new PageRequest(page, aPageSize);

        AppBlockEntity appBlockEntity = appBlockDao.findOneByNameEquals("jiyoujia");

        // 创建 head page
        Page<AppJiyoujiaHeadEntity> head = null;
        switch (appBlockEntity.getColumnType()) {
            case 2:
                IntegerEntity count = appJiyoujiaHeadDao.maxCountOfDoubleColumn();

                // 需查询的页数大于总页数，页数不足
                if (page >= Math.ceil(count.doubleValue() / aPageSize)) {
                    head = new PageImpl<>(new ArrayList<>(), pageable, count.intValue());
                    break;
                }

                // 极有家的头
                List<AppJiyoujiaHeadEntity> appJiyoujiaHeadEntities =
                        appJiyoujiaHeadDao.getDoubleColumn(page * aPageSize, aPageSize);
                head = new PageImpl<>(appJiyoujiaHeadEntities, pageable, count.intValue());
                break;
            default:
                break;
        }

        if (head == null) {
            throw new ServerException("逻辑错误");
        }

        // 创建 content page
        Page<AppJiyoujiaEntity> content;

        // 总页数
        IntegerEntity count = appJiyoujiaDao.maxCount();

        // 需查询的页数大于总页数，页数不足
        if (page >= Math.ceil(count.doubleValue() / aPageSize)) {
            content = new PageImpl<>(new ArrayList<>(), pageable, count.longValue());
        } else {
            // 极有家的内容
            List<AppJiyoujiaEntity> jiyoujiaEntities = appJiyoujiaDao.getData(page * aPageSize, aPageSize);
            content = new PageImpl<>(jiyoujiaEntities, new PageRequest(page, aPageSize), count.longValue());
        }

        appBlockEntity.setHead(head);
        appBlockEntity.setContent(content);

        return appBlockEntity;

    }

}
