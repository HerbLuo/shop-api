package cn.cloudself.controller;

import cn.cloudself.exception.http.RequestBadException;
import cn.cloudself.model.*;
import cn.cloudself.service.IItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    private final IItemService itemService;
    private final Logger logger;

    @Autowired
    public ItemController(IItemService itemService, Logger logger) {
        this.itemService = itemService;
        this.logger = logger;
    }

    /**
     * 得到商品详情（展示界面div）
     * sec: low
     *
     * @param id 商品id
     * @return obj.div
     */
    @RequestMapping(value = "/{id}/detail/div/", method = RequestMethod.GET)
    public ItemDetailHtmlEntity getItemDetail(@PathVariable("id") Integer id) throws Exception {
        return itemService.getItemDetail(id);
    }

    /**
     * 得到商品描述详情，以JSON格式记录
     *
     * @param id 商品id
     * @return obj {id: Number, describeJsonArray: JSON}
     */
    @RequestMapping(value = "/{id}/detail/describe/json/", method = RequestMethod.GET)
    public ItemDetailDescribeEntity getItemDetailDescribe(@PathVariable("id") Integer id) throws Exception {
        return itemService.getItemDetailDescribe(id);
    }

    /**
     * 搜索商品
     *
     * @param key       关键字
     * @param order     排序方法
     * @param page      .
     * @param aPageSize .
     * @return 商品s
     */
    @RequestMapping(value = "/key/{key}/order/{order}/page/{page}/apagesize/{aPageSize}/", method = RequestMethod.GET)
    public Page<ItemEntity> searchItem(@PathVariable("key") String key,
                                       @PathVariable("order") String order,
                                       @PathVariable("page") Integer page,
                                       @PathVariable("aPageSize") Integer aPageSize) throws Exception {
//        Thread.sleep(3000);
        return itemService.searchItemsOrderBy(key, order, page, aPageSize);
    }

    /**
     * 根据id 获取商品简单信息
     *
     * @param itemIds .
     * @return 商品s 无序
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<ItemEntity> getItemsbyId(@RequestParam("itemIds") Integer[] itemIds) throws Exception {
//        Thread.sleep(3000);
        return itemService.getItemsByIds(Arrays.asList(itemIds));
    }

    /**
     * 得到商品评论
     *
     * @param id        商品id
     * @param page      .
     * @param aPageSize .
     * @return 评论s
     */
    @RequestMapping(value = "/{id}/comments/page/{page}/apagesize/{aPageSize}/", method = RequestMethod.GET)
    public List<ItemCommentEntity> getComments(@PathVariable("id") Integer id,
                                               @PathVariable("page") Integer page,
                                               @PathVariable("aPageSize") Integer aPageSize) throws Exception {

        if (aPageSize > 100) {
            throw new RequestBadException("不支持该级别的分页，单页过大");
        }

        return itemService.getComment(id, aPageSize, page);
    }


}
