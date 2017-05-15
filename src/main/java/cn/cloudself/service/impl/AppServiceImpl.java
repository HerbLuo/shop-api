package cn.cloudself.service.impl;

import cn.cloudself.bean.EntitysWithVersion;
import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.*;
import cn.cloudself.model.*;
import cn.cloudself.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AppServiceImpl(IAppDao appDao, IAppSliderDao appSliderDao, IAppEntranceDao appEntranceDao, IAppHotbarDao appHotbarDao, IAppRushbuyDao appRushbuyDao) {
        this.appDao = appDao;
        this.appSliderDao = appSliderDao;
        this.appEntranceDao = appEntranceDao;
        this.appHotbarDao = appHotbarDao;
        this.appRushbuyDao = appRushbuyDao;
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

//    private EntityManager entityManager;
//
//    @PersistenceContext
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    public Iterable<AppRushbuyEntity> getRushbuy() throws Exception {
        return appRushbuyDao.getRushbuy();
    }

}
