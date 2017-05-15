package cn.cloudself.dao;

import cn.cloudself.model.CarEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface ICarDao extends CrudRepository<CarEntity, Integer>{

    Iterable<CarEntity> findTop5ByUserId(Integer userId);

    Iterable<CarEntity> findByUserId(Integer userId);

}
