package cn.cloudself.service;

import cn.cloudself.model.DeliverEntity;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IDeliveryService {

    /**
     * 添加运单号
     * @param sellerId 销售者id
     * @param orderAShopId 订单id（单个店铺）
     * @param delivers 运单号list
     * @return 添加了id的deliver对象
     */
    Iterable<DeliverEntity> deliver(Integer sellerId, Integer orderAShopId, List<DeliverEntity> delivers) throws Exception;

    /**
     * 修改运单号
     * @param deliver 需修改的对象
     */
    DeliverEntity modify(DeliverEntity deliver) throws Exception;

}
