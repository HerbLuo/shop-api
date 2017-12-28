package cn.cloudself.service.impl

import cn.cloudself.components.annotation.ParamChecker
import cn.cloudself.dao.*
import cn.cloudself.exception.http.RequestBadException
import cn.cloudself.exception.http.ServerException
import cn.cloudself.model.*
import cn.cloudself.service.IOrderService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
open class OrderServiceImpl @Autowired
constructor(
        private val orderDao: IOrderDao,
        private val itemDao: IItemDao,
        private val addressDao: IAddressDao,
        private val itemSellingInfoDao: IItemSellingInfoDao,
        private val deliverDao: IDeliverDao,
        private val orderAShopDao: IOrderAShopDao,
        private val messageDao: IMessageDao,
        private val logger: Logger,
        private val itemCommentDao: IItemCommentDao
) : IOrderService {

    /**
     * 取得历史订单
     *
     * @param userId    用户id
     * @param page      .
     * @param aPageSize .
     * @return .
     */
    @ParamChecker(
            greaterThan0 = intArrayOf(0, 2),
            greaterOrEqual0 = intArrayOf(1)
    )
    @Throws(Exception::class)
    override fun getOrdersByUser(
            userId: Int, page: Int, aPageSize: Int
    ): Page<OrderEntity> {
        return orderDao.findByUserIdAndEnabledTrue(
                userId,
                PageRequest(page, aPageSize, Sort(Sort.Direction.DESC, "createTime"))
        )
    }

    /**
     * 创建订单
     *
     * @param userId      .
     * @param orderAShops 订单（分为对个店铺）
     * @return 订单id
     */
    @ParamChecker(greaterThan0 = intArrayOf(0), notEmpty = intArrayOf(1))
    @Throws(Exception::class)
    override fun createOrder(userId: Int, orderAShops: List<OrderAShopEntity>): Int? {

        // 1. 过滤orderAShop，将id 发货状态 收货状态 是否评论 折扣 设为默认值
        // 2. 检查商品与商家是否对应
        // 3. 检查收货地址是否为用户名下的收货地址（防止地址信息泄露）

        // 4. 过滤orderAnItem 的id值 保证购买的商品数量（quantity）大于0
        // 5. 商品库存检查
        for (orderAShop in orderAShops) {

            /*
             * 1 request param to json bean
             */
            orderAShop.id = 0
            orderAShop.areDeliver = null
            orderAShop.areReceive = null
            orderAShop.areEvaluate = null
            orderAShop.discount = null
            orderAShop.userId = userId

            /*
             * 2
             */
            // non-null check
            val orderAnItems = orderAShop.orderAnItems
                    ?: throw RequestBadException("items 不能为空")

            val shopid = orderAShop.shop?.id ?: throw RequestBadException("shop 不能为空")

            // 需要检查的item id
            val itemIds = orderAnItems
                    .map { it.item ?: throw RequestBadException("items 不能为空")}
                    .map { it.id }


            // 数据库中取出来的Item对象
            itemDao.findAll(itemIds)
                    .filter { it.shop?.id != shopid }
                    .forEach { throw RequestBadException("商品信息与店铺所售不一致") }

            /*
             * 3
             */
            val address = addressDao.findOne(orderAShop.address?.id
                    ?: throw RequestBadException("address不能未空")
            )

            address.userId != userId &&
                throw RequestBadException("地址信息不符要求")


            for (orderAnItem in orderAnItems) {

                orderAnItem.item == null &&
                    throw RequestBadException("商品信息不能为空")

                /*
                 * 4
                 */
                orderAnItem.id = 0
                orderAnItem.quantity <= 0 &&
                    throw RequestBadException("购买的商品数量不能小于0")

                /*
                 * 5
                 */
                if (!kucunChecker(orderAnItem.item!!.id, orderAnItem.quantity)) {
                    throw RequestBadException("库存不足")
                }

            }
        }


        var order = OrderEntity()
        order.userId = userId
        order.orderAShops = orderAShops
        order = orderDao.save(order)

        return order.id
    }

    /**
     * 检查库存预留方法
     *
     * @return .
     */
    private fun kucunChecker(itemId: Int?, quantity: Int?): Boolean {
        Assert.notNull(itemId)
        Assert.notNull(quantity)

        val itemSellingInfo = itemSellingInfoDao.findOne(itemId)

        return itemSellingInfo != null &&
                itemSellingInfo.inOrdering + quantity!! <= itemSellingInfo.quantity

    }


    /**
     * 生成订单的支付信息
     * 计算订单价格
     *
     * @param orderId .
     * @param userId  .
     * @return .
     */
    @ParamChecker(0, 1)
    @Throws(Exception::class)
    override fun pay(orderId: Int, userId: Int): PayEntity {
        val order = orderDao.findOne(orderId)
        if (order.areFinished!!) {
            throw RequestBadException("订单已完成")
        }

        if (order.arePaied!!) {
            throw RequestBadException("订单已支付")
        }

        if (order.userId != userId) {
            throw RequestBadException("用户信息不符")
        }

        val orderAShops = order.orderAShops
        Assert.notEmpty(orderAShops, "创建了错误的订单？")

        /*
         * 计算订单价格
         */
        var discount = 0.0 //商家给的折扣
        var totalCost = 0.0 //商品价格合计
        for (orderAShop in orderAShops!!) {

            /*
             * 计算折扣
             */
            val t = orderAShop.discount
            discount += t ?: 0.0

            val orderAnItems = orderAShop.orderAnItems!!

            /*
             * 累加商品价格
             */
            for ((_, quantity, item) in orderAnItems) {
                Assert.notNull(quantity, "商品数量为空？")
                if (quantity <= 0) {
                    throw ServerException("商品数量小于0？")
                }

                logger.debug(item)
                Assert.notNull(item, "商品信息为空？")
                val itemPrice = item!!.price
                if (itemPrice <= 0) {
                    throw ServerException("商品价格小于0？")
                }
                totalCost += itemPrice * quantity
            }
        }

        /*
         * 最终价格
         */
        var pay = totalCost - discount
        if (pay > 200000) {
            throw RequestBadException("订单价格不能大于10万元")
        }

        pay = (pay * 100).toInt() / 100.0
        if (pay <= 0) {
            throw ServerException("订单价格不能小于0.01元")
        }

        val payEntity = newPay
        payEntity.price = pay

        return payEntity
    }


    private val newPay: PayEntity
        get() {
            val payEntity = PayEntity()
            payEntity.thirdPayUrl = "https://pay.cloudself.cn/"
            return payEntity
        }

    /**
     * 发货
     *
     * @param sellerId     店主id
     * @param orderAShopId 单个店铺的订单
     * @param delivers     快递对象
     */
    @ParamChecker(greaterThan0 = intArrayOf(0, 1), notEmpty = intArrayOf(2))
    @Throws(Exception::class)
    override fun deliver(
            sellerId: Int, orderAShopId: Int, delivers: List<DeliverEntity>
    ): Iterable<DeliverEntity> {

        // 单个店铺，单个订单不允许上传大于10个运单号
        if (delivers.size > 10) {
            throw RequestBadException("单个订单不允许上传大于10个物流运单号")
        } else if (delivers.size > 5) {
            logger.warn("运单号过多")
        }

        // 检测该订单是否属于 该店主
        val ordershop = orderAShopDao.findOne(orderAShopId)
                ?: throw RequestBadException("订单不存在")
        if (ordershop.shop!!.sellerId != sellerId) {
            throw RequestBadException("店铺信息不符")
        }
        // 检测订单是否已发货
        if (ordershop.areDeliver!!) {
            throw RequestBadException("该订单已发货")
        }

        // 更改所有的id信息
        for (deliver in delivers) {
            deliver.orderAShopId = orderAShopId
        }

        val newdelivers = deliverDao.save(delivers)

        // 将订单状态设置为已发货
        ordershop.areDeliver = true
        orderAShopDao.save(ordershop)

        // 通知用户已发货
        messageDao.save(MessageEntity("您的订单已发货", "发货信息", ordershop.userId!!))

        return newdelivers
    }

    /**
     * 收货
     *
     * @param userId       下单用户id
     * @param orderAShopId 订单id（店铺）
     * @return 是否执行成功
     */
    override fun receiveOne(userId: Int?, orderAShopId: Int?): Boolean? {
        return null
    }


    /**
     * 评论
     *
     * @param itemComments 评论
     */
    override fun comment(
            itemComments: Iterable<ItemCommentEntity>, userId: Int
    ): Iterable<ItemCommentEntity> {

        for (itemComment in itemComments) {
            itemComment.createTime = null
            itemComment.id = 0
            itemComment.userId = userId
        }

        return itemCommentDao.save(itemComments)

    }

}