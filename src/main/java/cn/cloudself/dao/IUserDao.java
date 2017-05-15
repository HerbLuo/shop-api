package cn.cloudself.dao;

import cn.cloudself.model.UserEntity;
import cn.cloudself.model.UserPublicOfCommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IUserDao extends Repository<UserEntity, Integer> {

    UserEntity findOne(Integer id);

    @Query("SELECT e FROM UserPublicOfCommentEntity e WHERE e.id IN ?1")
    List<UserPublicOfCommentEntity> findUserPublicInfoOfComment(List<Integer> userIds);

}
