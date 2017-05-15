package cn.cloudself.service;

import cn.cloudself.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 订单相关服务
 * 包括 创建取得订单 支付 发货 收获 评论
 * 退货 修改价格
 *
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IOrderService {

    /**
     * 取得历史订单
     * @param userId 用户id
     * @param page .
     * @param aPageSize .
     * @return .
     */
    Page<OrderEntity> getOrdersByUser(Integer userId, Integer page, Integer aPageSize) throws Exception;

    /**
     * 创建订单
     *
     * @param userId .
     * @param orderAShops 订单（分为对个店铺）
     * @return 订单id
     */
    Integer createOrder(Integer userId, List<OrderAShopEntity> orderAShops) throws Exception;

    /**
     * 生成订单的支付信息
     * @param orderId .
     * @param userId .
     * @return .
     */
    PayEntity pay(Integer orderId, Integer userId) throws Exception;

    /** 支付 */

    /**
     * 发货
     * @param sellerId 店主id
     * @param orderAShopId 单个店铺的订单
     * @param delivers 快递对象s
     */
    Iterable<DeliverEntity> deliver(Integer sellerId, Integer orderAShopId, List<DeliverEntity> delivers) throws Exception;

    /**
     * 收货
     * @param userId 下单用户id
     * @param orderAShopId 订单id（店铺）
     * @return 是否执行成功
     */
    Boolean receiveOne(Integer userId, Integer orderAShopId);

    /**
     * 评论
     * @param itemComments 评论对象s
     * @param userId 评论者id
     * @return 包含id的评论对象s
     */
    Iterable<ItemCommentEntity> comment(Iterable<ItemCommentEntity> itemComments, Integer userId);

    /**
     * 退货申请
     */

    /**
     * 同意退货
     */




}
