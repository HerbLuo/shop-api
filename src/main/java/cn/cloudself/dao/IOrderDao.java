package cn.cloudself.dao;

import cn.cloudself.model.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IOrderDao extends Repository<OrderEntity, Integer> {

    /**
     * 查询用户的历史订单
     * @param userId .
     * @param pageable .
     * @return .
     */
    Page<OrderEntity> findByUserIdAndEnabledTrue(Integer userId, Pageable pageable);

    /**
     * 保存订单
     * @param orderEntity .
     * @return id
     */
    OrderEntity save(OrderEntity orderEntity);

    OrderEntity findOne(Integer orderId);


}
