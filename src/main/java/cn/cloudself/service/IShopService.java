package cn.cloudself.service;

import cn.cloudself.model.ShopDetailEntity;
import cn.cloudself.model.ShopDetailHtmlSidebarEntity;
import cn.cloudself.model.ShopDetailHtmlTopbarEntity;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IShopService {


    /**
     * 得到店铺详细信息
     * 就是店铺资质，掌柜，评分什么的
     *
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    ShopDetailEntity getShopDetail(Integer shopId) throws Exception;

    /**
     * 得到店铺的顶部栏
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    ShopDetailHtmlTopbarEntity getShopDetailHtmlTopbarEntity(Integer shopId) throws Exception;

    /**
     * 得到店铺的侧边栏
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    ShopDetailHtmlSidebarEntity getShopDetailSidebarEntity(Integer shopId) throws Exception;

}
