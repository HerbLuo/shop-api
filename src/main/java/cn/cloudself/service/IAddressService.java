package cn.cloudself.service;

import cn.cloudself.model.AddressEntity;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IAddressService {

    /**
     * 根据用户id找出对应的收货地址
     */
    Iterable<AddressEntity> getAddressByUserId(Integer id) throws Exception;

}
