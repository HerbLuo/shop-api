package cn.cloudself.service.impl;

import cn.cloudself.bean.EntitysWithVersion;
import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.*;
import cn.cloudself.exception.http.RequestBadException;
import cn.cloudself.exception.http.ServerException;
import cn.cloudself.model.*;
import cn.cloudself.service.IAppService;
import com.sun.javaws.util.JavawsConsoleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.PAData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 * <p>
 * change logs:
 * 2017/3/28 HerbLuo 首次创建
 */
@Service
@Transactional
public class AppServiceImpl implements IAppService {

    private final IAppDao appDao;

    private final IAppSliderDao appSliderDao;

    private final IAppEntranceDao appEntranceDao;

    private final IAppHotbarDao appHotbarDao;

    private final IAppRushbuyContentDao appRushbuyDao;

    private final IAppJiyoujiaContentDao appJiyoujiaDao;

    private final IAppBlockDao appBlockDao;

    private final IAppJiyoujiaHeadDao appJiyoujiaHeadDao;

    private final IAppComponentVersionDao appComponentVersionDao;

    @Autowired
    public AppServiceImpl(IAppDao appDao, IAppSliderDao appSliderDao, IAppEntranceDao appEntranceDao, IAppHotbarDao appHotbarDao, IAppRushbuyContentDao appRushbuyDao, IAppJiyoujiaContentDao appJiyoujiaDao, IAppBlockDao appBlockDao, IAppJiyoujiaHeadDao appJiyoujiaHeadDao, IAppComponentVersionDao appComponentVersionDao) {
        this.appDao = appDao;
        this.appSliderDao = appSliderDao;
        this.appEntranceDao = appEntranceDao;
        this.appHotbarDao = appHotbarDao;
        this.appRushbuyDao = appRushbuyDao;
        this.appJiyoujiaDao = appJiyoujiaDao;
        this.appBlockDao = appBlockDao;
        this.appJiyoujiaHeadDao = appJiyoujiaHeadDao;
        this.appComponentVersionDao = appComponentVersionDao;
    }

    /**
     * 获取当前app的版本
     * <p>
     * change log:
     * 170613
     * herbluo
     * 抽离app组件version
     *
     * @return .
     */
    @Override
    public AppEntity getAppInfo() throws Exception {
        Iterable<AppComponentVersionEntity> acvs = appComponentVersionDao.findAll();

        AppEntity appEntity = appDao.getAppInfo();
        appEntity.setAppComponentVersion(acvs);

        return appEntity;
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
    @ParamChecker(greaterOrEqual0 = 0)
    public AppBlockEntity<?, AppRushbuyContentEntity> getRushbuy(Integer page, Integer aPageSize) throws Exception {

        AppBlockEntity<?, AppRushbuyContentEntity> blockEntity = appBlockDao.findRushBuyBlock();

        blockEntity.setContent(AppServiceImpl.getBlock(
                page,
                aPageSize,
                appRushbuyDao::maxCount,
                appRushbuyDao::getData
        ));

        return blockEntity;
    }

    /**
     * 获取极有家的内容
     *
     * @param page start from 0
     */
    @Override
    @ParamChecker(greaterOrEqual0 = 0)
    public AppBlockEntity<AppJiyoujiaHeadEntity, AppJiyoujiaContentEntity> getJiyoujia(
            Integer page, Integer aPageSize) throws Exception {

        // check
        if (aPageSize < -1 || aPageSize == 0) {
            throw new RequestBadException("错误的参数: aPageSize");
        }

        // block 信息，包含极有家的描述等
        AppBlockEntity<AppJiyoujiaHeadEntity, AppJiyoujiaContentEntity> appBlockEntity =
                appBlockDao.findJiYouJiaBlock();

        // check
        if (appBlockEntity == null) {
            throw new ServerException("数据库出错，未查询到AppBlock_极有家");
        }

        // check
        if (appBlockEntity.getColumnType() != 2) {
            throw new ServerException("逻辑错误");
        }

        // 创建 head page
        Page<AppJiyoujiaHeadEntity> head = AppServiceImpl.getBlock(
                page,
                aPageSize,
                appJiyoujiaHeadDao::maxCountOfDoubleColumn,
                appJiyoujiaHeadDao::getDoubleColumn
        );


        // 创建 content page
        Page<AppJiyoujiaContentEntity> content = AppServiceImpl.getBlock(
                page,
                aPageSize,
                appJiyoujiaDao::maxCount,
                appJiyoujiaDao::getData
        );

        // package
        appBlockEntity.setHead(head);
        appBlockEntity.setContent(content);

        // return
        return appBlockEntity;

    }

    /*
     * private
     */
    interface FunctionGetCount {
        IntegerEntity getCount();
    }

    interface FunctionGetData<T> {
        List<T> getData(int startFrom, int length);
    }

    private static <T> Page<T> getBlock(
            int page,
            int aPageSize,
            FunctionGetCount fGetCount,
            FunctionGetData<T> fGetData
    ) {

        // block有多少组
        int count = fGetCount.getCount().intValue();

        // 如果aPageSize为 -1 重新定义page和aPageSize
        page = aPageSize == -1 ? 0 : page;
        aPageSize = aPageSize == -1 ? count : aPageSize;

        Pageable pageable = new PageRequest(page, aPageSize);

        // 需查询的页数大于总页数，页数不足
        if (page >= Math.ceil(count / aPageSize)) {
            return new PageImpl<>(new ArrayList<>(), pageable, count);
        }

        return new PageImpl<>(fGetData.getData(page * aPageSize, aPageSize), pageable, count);
    }

}
