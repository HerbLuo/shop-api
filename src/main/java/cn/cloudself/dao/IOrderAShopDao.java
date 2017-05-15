package cn.cloudself.dao;

import cn.cloudself.model.OrderAShopEntity;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IOrderAShopDao extends Repository<OrderAShopEntity, Integer> {

    void save(OrderAShopEntity orderAShopEntity);

    OrderAShopEntity findOne(Integer id);

}
