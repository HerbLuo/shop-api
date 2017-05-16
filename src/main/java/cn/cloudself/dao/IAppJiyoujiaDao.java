package cn.cloudself.dao;

import cn.cloudself.model.AppJiyoujiaEntity;
import cn.cloudself.model.IntegerEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/16 HerbLuo 首次创建
 */
public interface IAppJiyoujiaDao extends Repository<AppJiyoujiaEntity, Integer> {

    List<AppJiyoujiaEntity> getData(int startFrom, int endWith);

    IntegerEntity maxCount();

}
