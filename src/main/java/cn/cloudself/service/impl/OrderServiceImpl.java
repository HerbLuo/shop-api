package cn.cloudself.service.impl;

import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.*;
import cn.cloudself.exception.http.RequestBadException;
import cn.cloudself.exception.http.ServerException;
import cn.cloudself.model.*;
import cn.cloudself.service.IOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    private final IOrderDao orderDao;

    private final IItemDao itemDao;

    private final IAddressDao addressDao;

    private final IItemSellingInfoDao itemSellingInfoDao;

    private final IDeliverDao deliverDao;

    private final IOrderAShopDao orderAShopDao;

    private final IMessageDao messageDao;

    private final Logger logger;

    @Autowired
    public OrderServiceImpl(IOrderDao orderDao, IItemDao itemDao, IAddressDao addressDao, IItemSellingInfoDao itemSellingInfoDao, IDeliverDao deliverDao, IOrderAShopDao orderAShopDao, IMessageDao messageDao, Logger logger, IItemCommentDao itemCommentDao) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.addressDao = addressDao;
        this.itemSellingInfoDao = itemSellingInfoDao;
        this.deliverDao = deliverDao;
        this.orderAShopDao = orderAShopDao;
        this.messageDao = messageDao;
        this.logger = logger;
        this.itemCommentDao = itemCommentDao;
    }

    /**
     * 取得历史订单
     *
     * @param userId    用户id
     * @param page      .
     * @param aPageSize .
     * @return .
     */
    @Override
    @ParamChecker(greaterThan0 = {0, 2}, greaterOrEqual0 = {1})
    public Page<OrderEntity> getOrdersByUser(Integer userId, Integer page, Integer aPageSize) throws Exception {
        return orderDao.findByUserIdAndEnabledTrue(
                userId,
                new PageRequest(page, aPageSize, new Sort(Sort.Direction.DESC, "createTime"))
        );
    }

    /**
     * 创建订单
     *
     * @param userId      .
     * @param orderAShops 订单（分为对个店铺）
     * @return 订单id
     */
    @Override
    @ParamChecker(greaterThan0 = {0}, notEmpty = {1})
    public Integer createOrder(Integer userId, List<OrderAShopEntity> orderAShops) throws Exception {

        // 1. 过滤orderAShop，将id 发货状态 收货状态 是否评论 折扣 设为默认值
        // 2. 检查商品与商家是否对应
        // 3. 检查收货地址是否为用户名下的收货地址（防止地址信息泄露）

        // 4. 过滤orderAnItem 的id值 保证购买的商品数量（quantity）大于0
        // 5. 商品库存检查
        for (OrderAShopEntity orderAShop : orderAShops) {

            // non-null check
            Iterable<OrderAnItemEntity> orderAnItems = orderAShop.getOrderAnItems();
            if (orderAnItems == null) {
                throw new RequestBadException("items 不能为空");
            }

            /*
             * 1 request param to json bean
             */
            orderAShop.setId(0);
            orderAShop.setAreDeliver(null);
            orderAShop.setAreReceive(null);
            orderAShop.setAreEvaluate(null);
            orderAShop.setDiscount(null);
            orderAShop.setUserId(userId);

            /*
             * 2
             */
            // 需要检查的item id
            Iterable<Integer> itemIds = orderAShop.getOrderAnItems().stream()
                    .map(o -> o.getItem().getId())
                    .collect(Collectors.toCollection(ArrayList::new));

            // 数据库中取出来的Item对象
            Iterable<ItemEntity> items = itemDao.findAll(itemIds);
            Integer shopid = orderAShop.getShop().getId();
            for (ItemEntity item : items) {
                if (item.getShop().getId() != shopid) {
                    throw new RequestBadException("商品信息与店铺所售不一致");
                }
            }

            /*
             * 3
             */
            AddressEntity address = addressDao.findOne(orderAShop.getAddress().getId());
            if (address.getUserId() != userId) {
                throw new RequestBadException("地址信息不符要求");
            }


            for (OrderAnItemEntity orderAnItem : orderAnItems) {

                if (orderAnItem.getItem() == null) {
                    throw new RequestBadException("商品信息不能为空");
                }

                /*
                 * 4
                 */
                orderAnItem.setId(0);
                if (orderAnItem.getQuantity() <= 0) {
                    throw new RequestBadException("购买的商品数量不能小于0");
                }

                /*
                 * 5
                 */
                if (!kucunChecker(orderAnItem.getItem().getId(), orderAnItem.getQuantity())) {
                    throw new RequestBadException("库存不足");
                }

            }
        }


        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setOrderAShops(orderAShops);
        order = orderDao.save(order);

        return order.getId();
    }

    /**
     * 检查库存预留方法
     *
     * @return .
     */
    private boolean kucunChecker(Integer itemId, Integer quantity) {
        Assert.notNull(itemId);
        Assert.notNull(quantity);

        ItemSellingInfoEntity itemSellingInfo = itemSellingInfoDao.findOne(itemId);

        return itemSellingInfo != null &&
                (itemSellingInfo.getInOrdering()) + quantity <= itemSellingInfo.getQuantity();

    }


    /**
     * 生成订单的支付信息
     * 计算订单价格
     *
     * @param orderId .
     * @param userId  .
     * @return .
     */
    @Override
    @ParamChecker({0, 1})
    public PayEntity pay(Integer orderId, Integer userId) throws Exception {
        OrderEntity order = orderDao.findOne(orderId);
        if (order.getAreFinished()) {
            throw new RequestBadException("订单已完成");
        }

        if (order.getArePaied()) {
            throw new RequestBadException("订单已支付");
        }

        if (order.getUserId() != userId) {
            throw new RequestBadException("用户信息不符");
        }

        Collection<OrderAShopEntity> orderAShops = order.getOrderAShops();
        Assert.notEmpty(orderAShops, "创建了错误的订单？");

        /*
         * 计算订单价格
         */
        double discount = 0.0; //商家给的折扣
        double totalCost = 0.0; //商品价格合计
        for (OrderAShopEntity orderAShop : orderAShops) {

            /*
             * 计算折扣
             */
            Double t = orderAShop.getDiscount();
            discount += t == null ? 0 : t;

            /*
             * 累加商品价格
             */
            for (OrderAnItemEntity orderAnItem : orderAShop.getOrderAnItems()) {
                Integer quantity = orderAnItem.getQuantity();
                Assert.notNull(quantity, "商品数量为空？");
                if (quantity <= 0) {
                    throw new ServerException("商品数量小于0？");
                }

                ItemEntity item = orderAnItem.getItem();
                logger.debug(item);
                Assert.notNull(item, "商品信息为空？");
                Double itemPrice = item.getPrice();
                if (itemPrice <= 0) {
                    throw new ServerException("商品价格小于0？");
                }
                totalCost += itemPrice * quantity;
            }
        }

        /*
         * 最终价格
         */
        double pay = totalCost - discount;
        if (pay > 200000) {
            throw new RequestBadException("订单价格不能大于10万元");
        }

        pay = ((int) (pay * 100)) / 100.0;
        if (pay <= 0) {
            throw new ServerException("订单价格不能小于0.01元");
        }

        PayEntity payEntity = getNewPay();
        payEntity.setPrice(pay);

        return payEntity;
    }


    private PayEntity getNewPay() {
        PayEntity payEntity = new PayEntity();
        payEntity.setThirdPayUrl("https://pay.cloudself.cn/");
        return payEntity;
    }

    /**
     * 发货
     *
     * @param sellerId     店主id
     * @param orderAShopId 单个店铺的订单
     * @param delivers     快递对象
     */
    @Override
    @ParamChecker(greaterThan0 = {0, 1}, notEmpty = {2})
    public Iterable<DeliverEntity> deliver(Integer sellerId, Integer orderAShopId, List<DeliverEntity> delivers) throws Exception {
        // 单个店铺，单个订单不允许上传大于10个运单号
        if (delivers.size() > 10) {
            throw new RequestBadException("单个订单不允许上传大于10个物流运单号");
        } else if(delivers.size() > 5) {
            logger.warn("运单号过多");
        }

        // 检测该订单是否属于 该店主
        OrderAShopEntity ordershop = orderAShopDao.findOne(orderAShopId);
        if (ordershop == null) {
            throw new RequestBadException("订单不存在");
        }
        if (!Objects.equals(ordershop.getShop().getSellerId(), sellerId)) {
            throw new RequestBadException("店铺信息不符");
        }
        // 检测订单是否已发货
        if (ordershop.getAreDeliver()) {
            throw new RequestBadException("该订单已发货");
        }

        // 更改所有的id信息
        for (DeliverEntity deliver : delivers) {
            deliver.setOrderAShopId(orderAShopId);
        }

        Iterable<DeliverEntity> newdelivers = deliverDao.save(delivers);

        // 将订单状态设置为已发货
        ordershop.setAreDeliver(true);
        orderAShopDao.save(ordershop);

        // 通知用户已发货
        messageDao.save(new MessageEntity("您的订单已发货", "发货信息", ordershop.getUserId()));

        return newdelivers;
    }

    /**
     * 收货
     *
     * @param userId       下单用户id
     * @param orderAShopId 订单id（店铺）
     * @return 是否执行成功
     */
    @Override
    public Boolean receiveOne(Integer userId, Integer orderAShopId) {
        return null;
    }

    private IItemCommentDao itemCommentDao;


    /**
     * 评论
     *
     * @param itemComments 评论
     */
    @Override
    public Iterable<ItemCommentEntity> comment(Iterable<ItemCommentEntity> itemComments, Integer userId) {

        for (ItemCommentEntity itemComment : itemComments) {
            itemComment.setCreateTime(null);
            itemComment.setId(0);
            itemComment.setUserId(userId);
        }

        return itemCommentDao.save(itemComments);

    }

}
