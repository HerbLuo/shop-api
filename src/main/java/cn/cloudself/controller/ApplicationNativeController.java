package cn.cloudself.controller;

import cn.cloudself.bean.EntitysWithVersion;
import cn.cloudself.model.*;
import cn.cloudself.service.IAppService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Api(description = "应用级的Api，如App版本信息")
@RestController
@RequestMapping("/app/native")
public class ApplicationNativeController {

    private final IAppService appService;

    @Autowired
    public ApplicationNativeController(IAppService appService) {
        this.appService = appService;
    }


    @ApiOperation(value = "获取app信息", notes = "获取app信息，包括版本信息，组件版本信息")
    @RequestMapping(value = "/info/", method = RequestMethod.GET)
    public AppEntity version() throws Exception {
        return appService.getAppInfo();
    }

    @ApiOperation(value = "获取app的十个入口")
    @RequestMapping(value = "/entrance/", method = RequestMethod.GET)
    public EntitysWithVersion<AppEntranceEntity> getEntrance() throws Exception {
        return appService.getEntraceBar();
    }

    @ApiOperation(value = "获取热门信息")
    @RequestMapping(value = "/hotbar/", method = RequestMethod.GET)
    public Iterable<AppHotbarEntity> getHotbar() throws Exception {
        return appService.getHotbar();
    }

    @ApiOperation(value = "获取抢购信息")
    @RequestMapping(value = "/rushbuy/page/{page}/apagesize/{aPageSize}/", method = RequestMethod.GET)
    public AppBlockEntity<?, AppRushbuyContentEntity> getRushbuy(
            @ApiParam(value = "页码，从0开始计",
                    defaultValue = "0", allowableValues = "range[0, infinity]", required = true)
            @PathVariable("page") Integer page,

            @ApiParam(value = "单页大小；-1代表获取全部数据，此时page失效\"",
                    defaultValue = "2", allowableValues = "range[-1, infinity]", required = true)
            @PathVariable("aPageSize") Integer aPageSize
    ) throws Exception {

        return appService.getRushbuy(page, aPageSize);
    }

    @ApiOperation(value = "获取极有家")
    @RequestMapping(value = "/jiyoujia/page/{page}/apagesize/{aPageSize}/", method = RequestMethod.GET)
    public AppBlockEntity<AppJiyoujiaHeadEntity, AppJiyoujiaContentEntity> getJiyoujia(
            @ApiParam(value = "页码，从0开始计",
                    defaultValue = "0", allowableValues = "range[0, infinity]", required = true)
            @PathVariable("page") Integer page,

            @ApiParam(value = "单页大小；-1代表获取全部数据，此时page失效",
                    defaultValue = "2", allowableValues = "range[-1, infinity]", required = true)
            @PathVariable("aPageSize") Integer aPageSize) throws Exception {

        return appService.getJiyoujia(page, aPageSize);
    }

}
