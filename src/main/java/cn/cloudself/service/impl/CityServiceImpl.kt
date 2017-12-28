package cn.cloudself.service.impl

import cn.cloudself.components.annotation.ParamChecker
import cn.cloudself.dao.IProvCityAreaStreetDao
import cn.cloudself.model.ProvCityAreaStreetEntity
import cn.cloudself.service.ICityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/6/13 HerbLuo 首次创建
 */
@Service
@Transactional
open class CityServiceImpl @Autowired
constructor(private val provCityAreaStreetDao: IProvCityAreaStreetDao) : ICityService {

    /**
     * 获取省，市，区三级区划信息
     */
    override fun getProvCityArea(): List<ProvCityAreaStreetEntity> {
        return provCityAreaStreetDao.findAllByLevelLessThanEqual(3)
    }

    /**
     * 获取某个区下的街道
     *
     * @param parCode 区代码
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getStreet(parCode: Int): List<ProvCityAreaStreetEntity> {
        return provCityAreaStreetDao.findAllByParentIdEquals(parCode)
    }

}
