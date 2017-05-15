package cn.cloudself.service.impl;

import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.IShopDetailDao;
import cn.cloudself.dao.IShopDetailHtmlSlidebarDao;
import cn.cloudself.dao.IShopDetailHtmlTopbarDao;
import cn.cloudself.model.ShopDetailEntity;
import cn.cloudself.model.ShopDetailHtmlSidebarEntity;
import cn.cloudself.model.ShopDetailHtmlTopbarEntity;
import cn.cloudself.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
public class ShopServiceImpl implements IShopService {

    private final IShopDetailDao shopDetailDao;
    private final IShopDetailHtmlTopbarDao shopDetailHtmlTopbarDao;
    private final IShopDetailHtmlSlidebarDao shopDetailHtmlSlidebarDao;

    @Autowired
    public ShopServiceImpl(IShopDetailDao shopDetailDao, IShopDetailHtmlTopbarDao shopDetailHtmlTopbarDao, IShopDetailHtmlSlidebarDao shopDetailHtmlSlidebarDao) {
        this.shopDetailDao = shopDetailDao;
        this.shopDetailHtmlTopbarDao = shopDetailHtmlTopbarDao;
        this.shopDetailHtmlSlidebarDao = shopDetailHtmlSlidebarDao;
    }

    /**
     * 得到店铺详细信息
     * 就是店铺资质，掌柜，评分什么的
     *
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public ShopDetailEntity getShopDetail(Integer shopId) throws Exception {
        return shopDetailDao.findOne(shopId);
    }

    /**
     * 得到店铺的顶部栏
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public ShopDetailHtmlTopbarEntity getShopDetailHtmlTopbarEntity(Integer shopId) throws Exception {
        return shopDetailHtmlTopbarDao.findOne(shopId);
    }

    /**
     * 得到店铺的侧边栏
     *
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public ShopDetailHtmlSidebarEntity getShopDetailSidebarEntity(Integer shopId) throws Exception {
        return shopDetailHtmlSlidebarDao.findOne(shopId);
    }


}
