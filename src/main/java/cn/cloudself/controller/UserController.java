package cn.cloudself.controller;

import cn.cloudself.model.UserEntity;
import cn.cloudself.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CURD 收货地址
 *
 * 获取用户基本信息
 *
 * @author HerbLuo
 * @version 1.0.0.d
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{username}/", method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable("username") String username) throws Exception {
        Integer userId = userService.getUserIdByUsername(username);
//        Thread.sleep(3000);
        return userService.getUser(userId);
    }

}
