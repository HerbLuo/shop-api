package cn.cloudself.service;

import cn.cloudself.model.CarEntity;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface ICarService {

    /**
     * 添加到购物车
     * @param userId .
     * @param itemIds .
     * @return .
     */
    Iterable<CarEntity> add2Car(Integer userId, List<Integer> itemIds) throws Exception;

    /**
     * 删除购物车中的商品
     * @param userId .
     * @param carIds .
     * @return 是否删除成功
     */
    Boolean delete(Integer userId, List<Integer> carIds) throws Exception;

    /**
     * 获取购物车中的商品id
     * @param userId .
     * @return .
     */
    Iterable<CarEntity> get5Items(Integer userId) throws Exception;

    /**
     * 得到购物车中的所有商品
     * @param userId .
     * @return .
     */
    Iterable<CarEntity> getAllItems(Integer userId) throws Exception;

}
