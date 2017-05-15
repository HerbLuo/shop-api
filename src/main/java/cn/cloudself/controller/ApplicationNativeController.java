package cn.cloudself.controller;

import cn.cloudself.bean.EntitysWithVersion;
import cn.cloudself.model.AppEntity;
import cn.cloudself.model.AppEntranceEntity;
import cn.cloudself.model.AppHotbarEntity;
import cn.cloudself.model.AppRushbuyEntity;
import cn.cloudself.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@RestController
@RequestMapping("/app/native")
public class ApplicationNativeController {

    private final IAppService appService;

    @Autowired
    public ApplicationNativeController(IAppService appService) {
        this.appService = appService;
    }

    @RequestMapping(value = "/info/", method = RequestMethod.GET)
    public AppEntity version() throws Exception {
        return appService.getAppInfo();
    }

    @RequestMapping(value = "/entrance/", method = RequestMethod.GET)
    public EntitysWithVersion<AppEntranceEntity> getEntrance() throws Exception {
        return appService.getEntraceBar();
    }

    @RequestMapping(value = "/hotbar/", method = RequestMethod.GET)
    public Iterable<AppHotbarEntity> getHotbar() throws Exception {
        return appService.getHotbar();
    }

    @RequestMapping(value = "/rushbuy/", method = RequestMethod.GET)
    public Iterable<AppRushbuyEntity> getRushbuy() throws Exception {
        return appService.getRushbuy();
    }

}
