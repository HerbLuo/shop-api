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

    IntegerEntity maxCountOfDoubleColumn();

    Page<AppJiyoujiaHeadEntity> findByType(byte type, Pageable pageable);

}
