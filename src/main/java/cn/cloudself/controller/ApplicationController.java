package cn.cloudself.controller;

import cn.cloudself.model.AppSliderEntity;
import cn.cloudself.service.IAppService;
import cn.cloudself.service.IUserService;
import cn.cloudself.util.rest.RestString;
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
@RequestMapping("/app")
public class ApplicationController {

    private final IAppService appService;
    private final IUserService userService;

    @Autowired
    public ApplicationController(IAppService appService, IUserService userService) {
        this.appService = appService;
        this.userService = userService;
    }

    @RequestMapping(value = "/hotkey/", method = RequestMethod.GET)
    public RestString hotKey() {
        return new RestString("宝宝们圣诞快乐");
    }

    @RequestMapping(value = "/slider/optionalUser/{username}/size/{size}/", method = RequestMethod.GET)
    public Iterable<AppSliderEntity> getSlider(@PathVariable("username") String username,
                                               @PathVariable("size") Integer size) throws Exception {

        Integer userId = userService.getUserIdByUsername(username);
        return appService.getSilder(userId, size);
    }

}
