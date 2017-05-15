package cn.cloudself.controller;

import cn.cloudself.model.ShopDetailEntity;
import cn.cloudself.model.ShopDetailHtmlSidebarEntity;
import cn.cloudself.model.ShopDetailHtmlTopbarEntity;
import cn.cloudself.service.IShopService;
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
@RequestMapping("/shop")
public class ShopController {

    private final IShopService shopService;

    @Autowired
    public ShopController(IShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = "/detail/{id}/", method = RequestMethod.GET)
    public ShopDetailEntity getShopDetail(@PathVariable("id") Integer id) throws Exception {
        return shopService.getShopDetail(id);
    }

    @RequestMapping(value = "/detail/topbar/{id}/", method = RequestMethod.GET)
    public ShopDetailHtmlTopbarEntity getShopDetailTopbar(@PathVariable("id") Integer id) throws Exception {
        return shopService.getShopDetailHtmlTopbarEntity(id);
    }

    @RequestMapping(value = "/detail/sidebar/{id}/")
    public ShopDetailHtmlSidebarEntity getShopDetailSidebar(@PathVariable("id") Integer id) throws Exception {
        return shopService.getShopDetailSidebarEntity(id);
    }

}
