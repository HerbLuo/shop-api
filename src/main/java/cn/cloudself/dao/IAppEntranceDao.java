package cn.cloudself.dao;

import cn.cloudself.model.AppEntranceEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/4/13 HerbLuo 首次创建
 */
public interface IAppEntranceDao extends CrudRepository<AppEntranceEntity, Integer>{

    Iterable<AppEntranceEntity> findFirst10ByEnabledTrue();

}
