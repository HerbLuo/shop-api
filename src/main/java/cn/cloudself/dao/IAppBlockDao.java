package cn.cloudself.dao;

import cn.cloudself.model.AppBlockEntity;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/19 HerbLuo 首次创建
 */
public interface IAppBlockDao extends Repository<AppBlockEntity, Integer> {

    AppBlockEntity findOneByNameEquals(String name);

}
