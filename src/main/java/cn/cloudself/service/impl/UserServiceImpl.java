package cn.cloudself.service.impl;

import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.dao.IUserDao;
import cn.cloudself.model.UserEntity;
import cn.cloudself.model.UserPublicOfCommentEntity;
import cn.cloudself.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserDao userDao;

    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * 根据用户名获取用户id
     *
     * @param username 用户名
     * @return 用户id null 表示未找到
     */
    @Override
    @Cacheable(cacheNames = "username_userid")
    public Integer getUserIdByUsername(String username) {
        return userIdDao(username);
    }

    private Integer userIdDao(String username) {
        Query query = entityManager.createQuery("SELECT id FROM UsersEntity WHERE username = :username");
        query.setParameter("username", username);
        query.setMaxResults(1);

        List ids = query.getResultList();
        if (ids.size() == 1) {
            return (Integer) ids.get(0);
        }
        return null;
    }

    /**
     * 根据用户id获取用户信息（包括用户私有信息）
     *
     * @param userId .
     * @return .
     * @throws Exception .
     */
    @Override
    @ParamChecker({0})
    public UserEntity getUser(Integer userId) throws Exception {
        return userDao.findOne(userId);
    }

    /**
     * 用户公开信息
     * 显示在评论界面上
     *
     * @param userIds .
     * @return .
     */
    @Override
    public List<UserPublicOfCommentEntity> getUserPublicInfoOfComment(List<Integer> userIds) {
        return userDao.findUserPublicInfoOfComment(userIds);
    }

}
