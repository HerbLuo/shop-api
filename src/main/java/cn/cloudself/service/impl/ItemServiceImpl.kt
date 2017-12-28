package cn.cloudself.service.impl

import cn.cloudself.components.annotation.ParamChecker
import cn.cloudself.dao.IItemCommentDao
import cn.cloudself.dao.IItemDao
import cn.cloudself.dao.IItemDetailDescribeDao
import cn.cloudself.dao.IItemDetailHtmlDao
import cn.cloudself.exception.http.RequestBadException
import cn.cloudself.model.*
import cn.cloudself.service.IItemService
import cn.cloudself.service.IUserService
import cn.cloudself.util.StringUtils
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.*
import java.util.stream.Collectors

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
open class ItemServiceImpl @Autowired
constructor(
        private val itemDao: IItemDao,
        private val itemCommentDao: IItemCommentDao,
        private val itemDetailHtmlDao: IItemDetailHtmlDao,
        private val iItemDetailDescribeDao: IItemDetailDescribeDao,
        private val userService: IUserService,
        private val logger: Logger
) : IItemService {


    /**
     * 获取商品详情
     *
     * @param id id
     * @return 商品详情div
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getItemDetail(id: Int): ItemDetailHtmlEntity {
        return itemDetailHtmlDao.findOne(id)
    }

    /**
     * 获取商品描述详情
     *
     * @param id .
     * @return .
     */
    @ParamChecker(greaterThan0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getItemDetailDescribe(id: Int): ItemDetailDescribeEntity {
        return iItemDetailDescribeDao.findOne(id)
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
    @ParamChecker(
            notEmpty = intArrayOf(0, 1),
            greaterOrEqual0 = intArrayOf(2),
            greaterThan0 = intArrayOf(3)
    )
    @Throws(Exception::class)
    override fun searchItemsOrderBy(
            key: String, orderBy: String, page: Int, aPageSize: Int
    ): Page<ItemEntity> {

        val sort = orderMapColumn[orderBy] ?: throw RequestBadException("错误的参数：order")

        return itemDao.findByNameLikeAndEnabledTrue(
                "%$key%",
                PageRequest(page, aPageSize, sort)
        )
    }

    /**
     * 排序相关
     * 参数中的排序方式是一个简单的英文单词，
     * 该map保存了单词与Sort对象的映射
     */
    private val orderMapColumn = HashMap<String, Sort>()

    init {
        orderMapColumn.put("score", Sort(Sort.Direction.DESC, "itemSellingInfo.score"))
        orderMapColumn.put("sales", Sort(Sort.Direction.DESC, "itemSellingInfo.sales"))
        orderMapColumn.put("priceDesc", Sort(Sort.Direction.DESC, "price"))
        orderMapColumn.put("priceAsc", Sort(Sort.Direction.ASC, "price"))
    }

    /**
     * 根据id获取商品
     *
     * @param ids List id
     */
    @ParamChecker(notEmpty = intArrayOf(0))
    @Throws(Exception::class)
    override fun getItemsByIds(ids: Iterable<Int>): Iterable<ItemEntity> {
        return itemDao.findAll(ids)
    }


    /**
     * 获取评论
     *
     * @param itemId    .
     * @param aPageSize .
     * @param page      .
     * @return 评论
     */
    @ParamChecker(greaterThan0 = intArrayOf(0, 1), greaterOrEqual0 = intArrayOf(2))
    override fun getComment(
            itemId: Int, aPageSize: Int, page: Int
    ): List<ItemCommentEntity> {

        // 基本评论信息，不包含用户id外的信息
        val itemComments = itemCommentDao.findByItemId(itemId,
                PageRequest(page, aPageSize, Sort(Sort.Direction.DESC, "createTime"))
        )

        // 获取所有评论用户 id组成的数组
        val userIds = itemComments
                .map({ it.userId }) // 仅取ItemCommentEntity下的userId
                .distinct() // 去重

        // 查询用户信息
        val users = userService.getUserPublicInfoOfComment(userIds)

        // 为每个评论对象设置user
        itemComments.forEach { comment ->

            // 从users里 复制一份对应的user信息
            val user = users
                    .stream()
                    .filter { it.id == comment.userId }
                    .findAny()
                    .get().clone()

            /*
             * 用户选择了匿名评论，为用户名打码
             * 循环体内的代码块暂不抽离
             */
            if (comment.anonymous!!) {
                var nickname = StringBuilder(user.nickname)
                when (nickname.length) {
                    2, 3 -> nickname = nickname.replace(1, 2, "*")
                    4 -> nickname = nickname.replace(1, 3, "**")
                    else -> if (nickname.length > 4) {
                        nickname = nickname.replace(2, nickname.length - 2, "**")
                    } else {
                        logger.warn("用户昵称不符合规范，id为: " + user.id)
                    }
                }
                user.nickname = nickname.toString()
            }

            // 将user对象装入到评论中
            comment.user = user

        }

        return itemComments
    }

}
