package cn.cloudself.service.impl;

import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.IItemCommentDao;
import cn.cloudself.dao.IItemDao;
import cn.cloudself.dao.IItemDetailDescribeDao;
import cn.cloudself.dao.IItemDetailHtmlDao;
import cn.cloudself.exception.http.RequestBadException;
import cn.cloudself.model.*;
import cn.cloudself.service.IItemService;
import cn.cloudself.service.IUserService;
import cn.cloudself.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
public class ItemServiceImpl implements IItemService {

    private final IItemDao itemDao;

    private final IItemCommentDao itemCommentDao;

    private final IItemDetailHtmlDao itemDetailHtmlDao;

    private final IItemDetailDescribeDao iItemDetailDescribeDao;

    private final IUserService userService;

    private final Logger logger;

    @Autowired
    public ItemServiceImpl(IItemDao itemDao, IItemCommentDao itemCommentDao, IItemDetailHtmlDao itemDetailHtmlDao, IItemDetailDescribeDao iItemDetailDescribeDao, IUserService userService, Logger logger) {
        this.itemDao = itemDao;
        this.itemCommentDao = itemCommentDao;
        this.itemDetailHtmlDao = itemDetailHtmlDao;
        this.iItemDetailDescribeDao = iItemDetailDescribeDao;
        this.userService = userService;
        this.logger = logger;
    }


    /**
     * 获取商品详情
     *
     * @param id id
     * @return 商品详情div
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public ItemDetailHtmlEntity getItemDetail(Integer id) throws Exception {
        return itemDetailHtmlDao.findOne(id);
    }

    /**
     * 获取商品描述详情
     *
     * @param id .
     * @return .
     */
    @Override
    @ParamChecker(greaterThan0 = 0)
    public ItemDetailDescribeEntity getItemDetailDescribe(Integer id) throws Exception {
        return iItemDetailDescribeDao.findOne(id);
    }

    /**
     * 搜索商品
     *
     * @param key       关键字
     * @param orderBy   .
     * @param aPageSize .
     * @param page      .
     * @return 商品
     */
    @Override
    @ParamChecker(notEmpty = {0, 1}, greaterOrEqual0 = {2}, greaterThan0 = {3})
    public Page<ItemEntity> searchItemsOrderBy(
            String key, String orderBy, Integer page, Integer aPageSize) throws Exception {

        Sort sort = orderMapColumn.get(orderBy);
        if (sort == null) {
            throw new RequestBadException("错误的参数：order");
        }

        return itemDao.findByNameLikeAndEnabledTrue(
                "%" + key + "%",
                new PageRequest(page, aPageSize, sort)
        );
    }

    /**
     * 排序相关
     * 参数中的排序方式是一个简单的英文单词，
     * 该map保存了单词与Sort对象的映射
     */
    private Map<String, Sort> orderMapColumn = new HashMap<>();

    {
        orderMapColumn.put("score", new Sort(Sort.Direction.DESC, "itemSellingInfo.score"));
        orderMapColumn.put("sales", new Sort(Sort.Direction.DESC, "itemSellingInfo.sales"));
        orderMapColumn.put("priceDesc", new Sort(Sort.Direction.DESC, "price"));
        orderMapColumn.put("priceAsc", new Sort(Sort.Direction.ASC, "price"));
    }

    /**
     * 根据id获取商品
     *
     * @param ids List id
     */
    @Override
    @ParamChecker(notEmpty = {0})
    public Iterable<ItemEntity> getItemsByIds(Iterable<Integer> ids) throws Exception {
        return itemDao.findAll(ids);
    }


    /**
     * 获取评论
     *
     * @param itemId    .
     * @param aPageSize .
     * @param page      .
     * @return 评论
     */
    @Override
    @ParamChecker(greaterThan0 = {0, 1}, greaterOrEqual0 = {2})
    public List<ItemCommentEntity> getComment(Integer itemId, Integer aPageSize, Integer page) {

        // 基本评论信息，不包含用户id外的信息
        List<ItemCommentEntity> itemComments = itemCommentDao.findByItemId(itemId,
                new PageRequest(page, aPageSize, new Sort(Sort.Direction.DESC, "createTime"))
        );

        // 获取所有评论用户 id组成的数组
        List<Integer> userIds = itemComments
                .stream()
                .map(ItemCommentEntity::getUserId) // 仅取ItemCommentEntity下的userId
                .distinct() // 去重
                .collect(Collectors.toList());

        // 查询用户信息
        List<UserPublicOfCommentEntity> users = userService.getUserPublicInfoOfComment(userIds);

        // 为每个评论对象设置user
        itemComments.forEach(comment -> {

            // 从users里 复制一份对应的user信息
            UserPublicOfCommentEntity user = users
                    .stream()
                    .filter(u -> u.getId() == comment.getUserId())
                    .findAny()
                    .get().clone();

            /*
             * 用户选择了匿名评论，为用户名打码
             * 循环体内的代码块暂不抽离
             */
            if (comment.getAnonymous()) {
                StringBuilder nickname = new StringBuilder(user.getNickname());
                switch (nickname.length()) {
                    case 2:
                    case 3:
                        nickname = nickname.replace(1, 2, "*");
                        break;
                    case 4:
                        nickname = nickname.replace(1, 3, "**");
                        break;
                    default:
                        if (nickname.length() > 4) {
                            nickname = nickname.replace(2, nickname.length() - 2, "**");
                        } else {
                            logger.warn("用户昵称不符合规范，id为: " + user.getId());
                        }
                }
                user.setNickname(nickname.toString());
            }

            // 将user对象装入到评论中
            comment.setUser(user);

        });

        return itemComments;
    }

}
