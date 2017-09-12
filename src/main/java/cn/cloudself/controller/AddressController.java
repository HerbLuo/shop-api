package cn.cloudself.controller;

import cn.cloudself.model.AddressEntity;
import cn.cloudself.model.ProvCityAreaStreetEntity;
import cn.cloudself.service.IAddressService;
import cn.cloudself.service.ICityService;
import cn.cloudself.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Api("收货地址和城市信息")
@RestController
@RequestMapping("/address")
public class AddressController {

    private final IAddressService addressService;
    private final IUserService userService;
    private final ICityService cityService;

    @Autowired
    public AddressController(IAddressService addressService, IUserService userService, ICityService cityService) {
        this.addressService = addressService;
        this.userService = userService;
        this.cityService = cityService;
    }

    @RequestMapping(value = "/user/{username}/", method = RequestMethod.GET)
    public Iterable<AddressEntity> getAddressByUser(@PathVariable("username") String username) throws Exception {
        Integer userId = userService.getUserIdByUsername(username);
//        Thread.sleep(3000);
        return addressService.getAddressByUserId(userId);
    }

    @RequestMapping(value = "/city-info/province-city-area/", method = RequestMethod.GET)
    public List<ProvCityAreaStreetEntity> getProvCityArea() {
        return cityService.getProvCityArea();
    }

    @RequestMapping(value = "/city-info/street/area-code/{areaCode}/", method = RequestMethod.GET)
    public List<ProvCityAreaStreetEntity> getStreet(@PathVariable Integer areaCode) throws Exception {
        return cityService.getStreet(areaCode);
    }


}
