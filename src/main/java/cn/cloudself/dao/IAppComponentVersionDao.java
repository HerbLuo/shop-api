package cn.cloudself.dao;

import cn.cloudself.model.AppComponentVersionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/6/13 HerbLuo 首次创建
 */
public interface IAppComponentVersionDao extends CrudRepository<AppComponentVersionEntity, Integer> {
}
