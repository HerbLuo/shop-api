package cn.cloudself.service.impl;

import cn.cloudself.dao.IAddressDao;
import cn.cloudself.model.AddressEntity;
import cn.cloudself.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

    private final IAddressDao addressDao;

    @Autowired
    public AddressServiceImpl(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    /**
     * 根据用户id找出对应的收货地址
     *
     * @param id
     */
    @Override
    public Iterable<AddressEntity> getAddressByUserId(Integer id) throws Exception {
        return addressDao.findTop5ByUserIdAndEnabledTrue(id);
    }

}
