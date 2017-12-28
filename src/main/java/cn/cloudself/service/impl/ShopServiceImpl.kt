package cn.cloudself.service.impl

import cn.cloudself.components.annotation.ParamChecker
import cn.cloudself.dao.IShopDetailDao
import cn.cloudself.dao.IShopDetailHtmlSlidebarDao
import cn.cloudself.dao.IShopDetailHtmlTopbarDao
import cn.cloudself.model.ShopDetailEntity
import cn.cloudself.model.ShopDetailHtmlSidebarEntity
import cn.cloudself.model.ShopDetailHtmlTopbarEntity
import cn.cloudself.service.IShopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
open class ShopServiceImpl @Autowired
constructor(
        private val shopDetailDao: IShopDetailDao,
        private val shopDetailHtmlTopbarDao: IShopDetailHtmlTopbarDao,
        private val shopDetailHtmlSlidebarDao: IShopDetailHtmlSlidebarDao
) : IShopService {

    /**
     * 得到店铺详细信息
     * 就是店铺资质，掌柜，评分什么的
     *
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getShopDetail(shopId: Int?): ShopDetailEntity =
            shopDetailDao.findOne(shopId)

    /**
     * 得到店铺的顶部栏
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getShopDetailHtmlTopbarEntity(shopId: Int?): ShopDetailHtmlTopbarEntity =
            shopDetailHtmlTopbarDao.findOne(shopId)

    /**
     * 得到店铺的侧边栏
     *
     * @param shopId 店铺id
     * @return .
     * @throws Exception .
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getShopDetailSidebarEntity(shopId: Int?): ShopDetailHtmlSidebarEntity =
            shopDetailHtmlSlidebarDao.findOne(shopId)


}
