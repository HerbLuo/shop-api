package cn.cloudself.dao;

import cn.cloudself.model.AddressEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IAddressDao extends CrudRepository<AddressEntity, Integer> {

    Iterable<AddressEntity> findTop5ByUserIdAndEnabledTrue(Integer id);

}
