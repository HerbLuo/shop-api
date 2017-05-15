package cn.cloudself.dao;

import cn.cloudself.model.MessageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IMessageDao extends Repository<MessageEntity, Integer> {

    void save(MessageEntity messageEntity);

    Iterable<MessageEntity> findTop5ByUserIdAndAreReadIsFalse(Integer userId);

    Iterable<MessageEntity> findByUserIdAndAreReadIsFalse(Integer userId);

    @Modifying
    @Query("UPDATE MessageEntity message SET message.areRead = true WHERE message.id IN ?1")
    void makeMessageAsRead(List<Integer> id);

}
