package cn.cloudself.dao;

import cn.cloudself.model.ItemCommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public interface IItemCommentDao extends Repository<ItemCommentEntity, Integer> {

    //评论信息 只许添加 修改 查询

    /**
     * 添加或修改评论
     * @param itemComment 评论
     * @return 评论
     */
    ItemCommentEntity save(ItemCommentEntity itemComment);


    /**
     * 添加评论
     * @param itemCommentDaos 评论s
     */
    Iterable<ItemCommentEntity> save(Iterable<ItemCommentEntity> itemCommentDaos);

    /**
     * 分页查找评论信息
     * @param itemId .
     * @param pageable 分页及排序
     * @return 评论s
     */
    List<ItemCommentEntity> findByItemId(Integer itemId, Pageable pageable);

}
