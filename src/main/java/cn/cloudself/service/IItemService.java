package cn.cloudself.service;

import cn.cloudself.model.*;
import cn.cloudself.util.rest.LastLink;
import cn.cloudself.util.rest.Link;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IItemService {

    /**
     * 获取商品详情
     * @param id id
     * @return 商品详情div
     */
    ItemDetailHtmlEntity getItemDetail(Integer id) throws Exception;

    /**
     * 获取商品描述详情
     * @param id .
     * @return .
     */
    ItemDetailDescribeEntity getItemDetailDescribe(Integer id) throws Exception;

    /**
     * 搜索商品
     * @param key 关键字
     * @param orderBy .
     * @param aPageSize .
     * @param page .
     * @return 商品
     */
    Page<ItemEntity> searchItemsOrderBy(String key, String orderBy, Integer page, Integer aPageSize) throws Exception;

    /**
     * 根据id获取商品
     * @param ids List id
     */
    Iterable<ItemEntity> getItemsByIds(Iterable<Integer> ids) throws Exception;

    /**
     * 获取评论
     * @param itemId .
     * @param aPageSize .
     * @param page .
     * @return 评论
     */
    List<ItemCommentEntity> getComment(Integer itemId, Integer aPageSize, Integer page);

}
