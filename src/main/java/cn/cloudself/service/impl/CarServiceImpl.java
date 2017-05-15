package cn.cloudself.service.impl;

import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.ICarDao;
import cn.cloudself.exception.http.RequestBadException;
import cn.cloudself.model.CarEntity;
import cn.cloudself.model.ItemEntity;
import cn.cloudself.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
public class CarServiceImpl implements ICarService {

    private final ICarDao carDao;

    @Autowired
    public CarServiceImpl(ICarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * 添加到购物车
     *
     * @param userId  .
     * @param itemIds .
     * @return .
     */
    @Override
    @ParamChecker(greaterThan0 = {0}, notEmpty = {1})
    public Iterable<CarEntity> add2Car(Integer userId, List<Integer> itemIds) throws Exception {
        List<CarEntity> car = new ArrayList<>(
                itemIds.stream().map(id -> new CarEntity(userId, new ItemEntity(id)))
                        .collect(Collectors.toList())
        );

        return carDao.save(car);
    }

    /**
     * 删除购物车中的商品
     *
     * @param userId  .
     * @param carIds .
     * @return 是否删除成功
     */
    @Override
    @ParamChecker(greaterThan0 = 0, notEmpty = 1)
    public Boolean delete(Integer userId, List<Integer> carIds) throws Exception {

        // 检查购物车的归属
        Iterable<CarEntity> car = carDao.findAll(carIds);
        for (CarEntity carAnItem : car) {
            if (carAnItem.getUserId() != userId) {
                throw new RequestBadException("用户信息不符");
            }
        }

        carDao.delete(car);
        return true;
    }

    /**
     * 得到购物车中的所有商品
     *
     * @param userId .
     * @return .
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public Iterable<CarEntity> getAllItems(Integer userId) throws Exception {
        return carDao.findByUserId(userId);
    }

    /**
     * 获取购物车中的商品id
     *
     * @param userId .
     * @return .
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public Iterable<CarEntity> get5Items(Integer userId) throws Exception {
        return carDao.findTop5ByUserId(userId);
    }



}
