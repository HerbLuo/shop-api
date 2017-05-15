package cn.cloudself.dao;

import cn.cloudself.model.AppRushbuyEntity;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/10 HerbLuo 首次创建
 */
public interface IAppRushbuyDao extends Repository<AppRushbuyEntity, Integer> {

    Iterable<AppRushbuyEntity> getRushbuy();

}
