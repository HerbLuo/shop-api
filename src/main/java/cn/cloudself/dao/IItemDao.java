package cn.cloudself.dao;

import cn.cloudself.model.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IItemDao extends CrudRepository<ItemEntity, Integer> {

    Page<ItemEntity> findByNameLikeAndEnabledTrue(String name, Pageable pageable);

}
