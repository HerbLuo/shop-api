package cn.cloudself.service.impl

import cn.cloudself.components.annotation.ParamChecker
import cn.cloudself.dao.ICarDao
import cn.cloudself.exception.http.RequestBadException
import cn.cloudself.model.CarEntity
import cn.cloudself.model.ItemEntity
import cn.cloudself.service.ICarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
open class CarServiceImpl @Autowired
constructor(private val carDao: ICarDao) : ICarService {

    /**
     * 添加到购物车
     *
     * @param userId  .
     * @param itemIds .
     * @return .
     */
    @ParamChecker(greaterThan0 = intArrayOf(0), notEmpty = intArrayOf(1))
    @Throws(Exception::class)
    override fun add2Car(userId: Int, itemIds: List<Int>): Iterable<CarEntity> {

        val car = itemIds.map { id -> CarEntity(userId, ItemEntity(id)) }

        return carDao.save(car)
    }

    /**
     * 删除购物车中的商品
     *
     * @param userId  .
     * @param carIds .
     * @return 是否删除成功
     */
    @ParamChecker(greaterThan0 = intArrayOf(0), notEmpty = intArrayOf(1))
    @Throws(Exception::class)
    override fun delete(userId: Int, carIds: List<Int>): Boolean? {

        // 检查购物车的归属
        val cars = carDao.findAll(carIds)
        cars
                .filter { it.userId != userId }
                .forEach { throw RequestBadException("用户信息不符") }

        carDao.delete(cars)
        return true
    }

    /**
     * 得到购物车中的所有商品
     *
     * @param userId .
     * @return .
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getAllItems(userId: Int): Iterable<CarEntity> {
        return carDao.findByUserId(userId)
    }

    /**
     * 获取购物车中的商品id
     *
     * @param userId .
     * @return .
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun get5Items(userId: Int): Iterable<CarEntity> {
        return carDao.findTop5ByUserId(userId)
    }


}
