package cn.cloudself.dao;

import cn.cloudself.model.AppEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/4/9 HerbLuo 首次创建
 */
public interface IAppDao extends Repository<AppEntity, Integer> {

    @Query("SELECT app FROM AppEntity app WHERE app.id = 1")
    AppEntity getAppInfo();

    AppEntity save(AppEntity appEntity);

}
