package cn.cloudself.service;

import cn.cloudself.model.UserEntity;
import cn.cloudself.model.UserPublicOfCommentEntity;

import java.util.List;

/**
 *
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IUserService {

    /**
     * 根据用户名获取用户id
     * @param username 用户名
     * @return 用户id null, <=0 表示未找到
     */
    Integer getUserIdByUsername(String username);

    /**
     * 用户私有信息
     * 用于登陆
     * @param userId .
     * @return .
     * @throws Exception .
     */
    UserEntity getUser(Integer userId) throws Exception;

    /**
     * 用户公开信息
     * 显示在评论界面上
     *
     * @param userIds .
     * @return .
     */
    List<UserPublicOfCommentEntity> getUserPublicInfoOfComment(List<Integer> userIds);

}
