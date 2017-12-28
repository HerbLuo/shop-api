package cn.cloudself.service.impl

import cn.cloudself.dao.IAddressDao
import cn.cloudself.model.AddressEntity
import cn.cloudself.service.IAddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
open class AddressServiceImpl @Autowired
constructor(private val addressDao: IAddressDao) : IAddressService {

    /**
     * 根据用户id找出对应的收货地址
     */
    @Throws(Exception::class)
    override fun getAddressByUserId(id: Int?): Iterable<AddressEntity> =
            addressDao.findTop10ByUserIdAndEnabledTrue(id)

}