package cn.cloudself.dao;

import cn.cloudself.model.AppJiyoujiaHeadEntity;
import cn.cloudself.model.IntegerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/19 HerbLuo 首次创建
 */
public interface IAppJiyoujiaHeadDao extends Repository<AppJiyoujiaHeadEntity, Integer> {

    List<AppJiyoujiaHeadEntity> getDoubleColumn(int start, int length);

    /**
     * Max of 各类型(type 放置于左边还是右边)的记录数
     * 如：type为0的记录数有3个，type为1的记录数有4个，返回结果就为4
     */
    IntegerEntity maxCountOfDoubleColumn();

    Page<AppJiyoujiaHeadEntity> findByType(byte type, Pageable pageable);

}
