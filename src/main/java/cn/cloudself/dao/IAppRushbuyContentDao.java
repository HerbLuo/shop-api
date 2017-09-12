package cn.cloudself.dao;

import cn.cloudself.model.AppRushbuyContentEntity;
import cn.cloudself.model.IntegerEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/10 HerbLuo 首次创建
 */
public interface IAppRushbuyContentDao extends Repository<AppRushbuyContentEntity, Integer> {

    List<AppRushbuyContentEntity> getData(int startFrom, int size);

    IntegerEntity maxCount();

}
