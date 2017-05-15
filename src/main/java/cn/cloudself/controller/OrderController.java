package cn.cloudself.controller;

import cn.cloudself.model.*;
import cn.cloudself.service.IOrderService;
import cn.cloudself.service.IUserService;
import cn.cloudself.util.rest.RestId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创建订单 .
 * <p>
 * 修改订单（店铺修改价格）
 * 支付
 * 发货
 * 收货
 * 评价（最终）
 * <p>
 * 查询订单 .
 * 删除订单
 *
 * @author HerbLuo
 * @version 1.0.0.d
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    private final IUserService userService;

    @Autowired
    public OrderController(IOrderService orderService, IUserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    /**
     * 查询历史订单
     */
    @RequestMapping(value = "/user/{username}/page/{page}/apagesize/{aPageSize}/", method = RequestMethod.GET)
    public Page<OrderEntity> getOrderByUser(@PathVariable("username") String username,
                                            @PathVariable("page") Integer page,
                                            @PathVariable("aPageSize") Integer aPageSize) throws Exception {

        Integer userId = userService.getUserIdByUsername(username);
        return orderService.getOrdersByUser(userId, page, aPageSize);
    }

    /**
     * 创建订单
     */
    @RequestMapping(value = "/user/{username}/", method = RequestMethod.POST)
    public RestId<Integer> createOrder(@PathVariable("username") String username,
                                       @RequestBody List<OrderAShopEntity> orderAShops) throws Exception {

        Integer userId = userService.getUserIdByUsername(username);
        Integer id = orderService.createOrder(userId, orderAShops);
        return new RestId<>(id);
    }

    /**
     * 生成支付信息
     */
    @RequestMapping(value = "/{orderId}/user/{username}/payment/", method = RequestMethod.POST)
    public PayEntity pay(@PathVariable("orderId") Integer orderId,
                         @PathVariable("username") String username) throws Exception {

        Integer userId = userService.getUserIdByUsername(username);
        return orderService.pay(orderId, userId);
    }

    /**
     * 预留
     * 该请求由第三方支付平台调用
     * 信息验证通过后，调用paySuccess服务（写入成功支付信息到数据库）
     */
    @ResponseBody
    @RequestMapping(value = "/payment/checker/")
    public String paySuccess() {
        // 验证请求
        // ....
        // 写入支付成功信息
        return "success";
    }

    /**
     * 发货
     */
    @RequestMapping(value = "/delivery/{orderAShopId}/seller/{username}/", method = RequestMethod.POST)
    public Iterable<DeliverEntity> deliver(@PathVariable("orderAShopId") Integer orderAShopId,
                                           @PathVariable("username") String username,
                                           @RequestBody List<DeliverEntity> delivers) throws Exception {

        Integer sellerId = userService.getUserIdByUsername(username);
        return orderService.deliver(sellerId, orderAShopId, delivers);
    }

    /**
     * 评论
     */
    public Iterable<ItemCommentEntity> comment() {

        return null;
    }

}
