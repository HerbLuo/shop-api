package cn.cloudself.dao;

import cn.cloudself.model.ProvCityAreaStreetEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/6/13 HerbLuo 首次创建
 */
public interface IProvCityAreaStreetDao extends Repository<ProvCityAreaStreetEntity, Integer> {

    @SuppressWarnings("SameParameterValue")
    List<ProvCityAreaStreetEntity> findAllByLevelLessThanEqual(Integer levelMax);

    List<ProvCityAreaStreetEntity> findAllByParentIdEquals(Integer parCode);

}
