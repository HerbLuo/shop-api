package cn.cloudself.service;

import cn.cloudself.model.ProvCityAreaStreetEntity;

import java.util.List;

/**
 * 获取行政区划信息
 *
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/6/13 HerbLuo 首次创建
 */
public interface ICityService {

    /**
     * 获取省，市，区三级区划信息
     */
    List<ProvCityAreaStreetEntity> getProvCityArea();

    /**
     * 获取某个区下的街道
     *
     * @param parCode 区代码
     */
    List<ProvCityAreaStreetEntity> getStreet(Integer parCode) throws Exception;

}
