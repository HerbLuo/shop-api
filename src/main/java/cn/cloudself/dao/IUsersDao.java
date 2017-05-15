package cn.cloudself.dao;

import cn.cloudself.model.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IUsersDao extends Repository<UsersEntity, Integer> {
}
