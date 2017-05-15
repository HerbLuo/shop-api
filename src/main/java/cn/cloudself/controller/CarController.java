package cn.cloudself.controller;

import cn.cloudself.model.CarEntity;
import cn.cloudself.service.ICarService;
import cn.cloudself.service.IUserService;
import cn.cloudself.util.rest.RestString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@RestController
@RequestMapping("/car")
public class CarController {

    private final ICarService carService;
    private final IUserService userService;

    @Autowired
    public CarController(ICarService carService, IUserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    /**
     * 添加商品到购物车
     *
     * @param username 进行该操作的用户的用户名
     * @param itemIds  商品的ids request body
     */
    @RequestMapping(value = "/user/{username}/", method = RequestMethod.POST)
    public Iterable<CarEntity> addToCar(@PathVariable("username") String username,
                                        @RequestBody List<Integer> itemIds) throws Exception {

//        Thread.sleep(3000);
        Integer userId = userService.getUserIdByUsername(username);
        return carService.add2Car(userId, itemIds);
    }

    /**
     * 删除购物车内的商品
     *
     * @param username 进行该操作的用户
     * @param itemIds  商品id
     */
    @RequestMapping(value = "/user/{username}/", method = RequestMethod.DELETE)
    public ResponseEntity<RestString> deleteItems(@PathVariable("username") String username,
                                                  @RequestBody List<Integer> itemIds) throws Exception {

        Integer userId = userService.getUserIdByUsername(username);

        Boolean areSuccess = carService.delete(userId, itemIds);
        RestString result = new RestString(areSuccess ? "success" : "fail");

        return new ResponseEntity<>(result, areSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 得到购物车内的商品
     */
    @RequestMapping(value = "/user/{username}/", method = RequestMethod.GET)
    public Iterable<CarEntity> getCar(/*@PathVariable("count") String count,*/
                                      @PathVariable("username") String username) throws Exception {

        Integer userId = userService.getUserIdByUsername(username);
//        Thread.sleep(3000);
/*        if ("5".equals(count)) {
            return carService.get5Items(userId);
        } else {*/
        return carService.getAllItems(userId);
//        }
    }

}
