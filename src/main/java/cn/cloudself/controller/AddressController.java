package cn.cloudself.controller;

import cn.cloudself.model.AddressEntity;
import cn.cloudself.service.IAddressService;
import cn.cloudself.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    private final IAddressService addressService;
    private final IUserService userService;

    @Autowired
    public AddressController(IAddressService addressService, IUserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @RequestMapping(value = "/user/{username}/", method = RequestMethod.GET)
    public Iterable<AddressEntity> getAddressByUser(@PathVariable("username") String username) throws Exception {
        Integer userId = userService.getUserIdByUsername(username);
//        Thread.sleep(3000);
        return addressService.getAddressByUserId(userId);
    }


}
