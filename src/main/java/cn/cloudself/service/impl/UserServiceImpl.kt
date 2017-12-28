package cn.cloudself.service.impl

import cn.cloudself.components.annotation.ParamChecker
import cn.cloudself.dao.IUserDao
import cn.cloudself.model.UserEntity
import cn.cloudself.model.UserPublicOfCommentEntity
import cn.cloudself.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
open class UserServiceImpl @Autowired
constructor(private val userDao: IUserDao) : IUserService {

    private var entityManager: EntityManager? = null

    @PersistenceContext
    fun setEntityManager(entityManager: EntityManager) {
        this.entityManager = entityManager
    }

    /**
     * 根据用户名获取用户id
     *
     * @param username 用户名
     * @return 用户id null 表示未找到
     */
    @Cacheable(cacheNames = arrayOf("username_userid"))
    override fun getUserIdByUsername(username: String): Int? =
            userIdDao(username)

    private fun userIdDao(username: String): Int? {
        val query = entityManager!!
                .createQuery(
                        "SELECT id FROM UsersEntity WHERE username = :username")
                .setParameter("username", username)
                .setMaxResults(1)

        val ids = query.resultList
        return if (ids.size == 1) {
            ids[0] as Int
        } else null
    }

    /**
     * 根据用户id获取用户信息（包括用户私有信息）
     *
     * @param userId .
     * @return .
     * @throws Exception .
     */
    @ParamChecker(0)
    @Throws(Exception::class)
    override fun getUser(userId: Int?): UserEntity = userDao.findOne(userId)

    /**
     * 用户公开信息
     * 显示在评论界面上
     *
     * @param userIds .
     * @return .
     */
    override fun getUserPublicInfoOfComment(userIds: List<Int>)
            : List<UserPublicOfCommentEntity> =
            userDao.findUserPublicInfoOfComment(userIds)

}
