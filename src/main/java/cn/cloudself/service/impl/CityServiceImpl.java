package cn.cloudself.service.impl;

import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.IProvCityAreaStreetDao;
import cn.cloudself.model.ProvCityAreaStreetEntity;
import cn.cloudself.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/6/13 HerbLuo 首次创建
 */
@Service
public class CityServiceImpl implements ICityService {

    private final IProvCityAreaStreetDao provCityAreaStreetDao;

    @Autowired
    public CityServiceImpl(IProvCityAreaStreetDao provCityAreaStreetDao) {
        this.provCityAreaStreetDao = provCityAreaStreetDao;
    }

    /**
     * 获取省，市，区三级区划信息
     */
    @Override
    public List<ProvCityAreaStreetEntity> getProvCityArea() {
        return provCityAreaStreetDao.findAllByLevelLessThanEqual(3);
    }

    /**
     * 获取某个区下的街道
     *
     * @param parCode 区代码
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public List<ProvCityAreaStreetEntity> getStreet(Integer parCode) throws Exception {
        return provCityAreaStreetDao.findAllByParentIdEquals(parCode);
    }

}
