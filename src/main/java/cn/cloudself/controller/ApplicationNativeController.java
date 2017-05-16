package cn.cloudself.controller;

import cn.cloudself.bean.EntitysWithVersion;
import cn.cloudself.model.*;
import cn.cloudself.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 获取app信息，包括版本信息，组件版本信息
     */
    @RequestMapping(value = "/info/", method = RequestMethod.GET)
    public AppEntity version() throws Exception {
        return appService.getAppInfo();
    }

    /**
     * 获取app的十个入口
     */
    @RequestMapping(value = "/entrance/", method = RequestMethod.GET)
    public EntitysWithVersion<AppEntranceEntity> getEntrance() throws Exception {
        return appService.getEntraceBar();
    }

    /**
     * 获取热门信息
     */
    @RequestMapping(value = "/hotbar/", method = RequestMethod.GET)
    public Iterable<AppHotbarEntity> getHotbar() throws Exception {
        return appService.getHotbar();
    }

    /**
     * 获取抢购信息
     */
    @RequestMapping(value = "/rushbuy/", method = RequestMethod.GET)
    public Iterable<AppRushbuyEntity> getRushbuy() throws Exception {
        return appService.getRushbuy();
    }

    /**
     * 获取极有家
     * @param page start from 0
     */
    @RequestMapping(value = "/jiyoujia/page/{page}/apagesize/{aPageSize}", method = RequestMethod.GET)
    public Page<AppJiyoujiaEntity> getJiyoujia(@PathVariable("page") Integer page,
                                               @PathVariable("aPageSize") Integer aPageSize) throws Exception {
        return appService.getJiyoujia(page, aPageSize);
    }

}
