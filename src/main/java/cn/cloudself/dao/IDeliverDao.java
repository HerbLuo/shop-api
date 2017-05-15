package cn.cloudself.dao;

import cn.cloudself.model.DeliverEntity;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IDeliverDao extends Repository<DeliverEntity, Integer> {

    DeliverEntity save(DeliverEntity deliverEntity);

    Iterable<DeliverEntity> save(Iterable<DeliverEntity> delivers);

    DeliverEntity findOne(Integer id);

}
