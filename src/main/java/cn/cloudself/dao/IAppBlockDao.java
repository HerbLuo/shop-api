package cn.cloudself.dao;

import cn.cloudself.model.AppBlockEntity;
import cn.cloudself.model.AppJiyoujiaContentEntity;
import cn.cloudself.model.AppJiyoujiaHeadEntity;
import cn.cloudself.model.AppRushbuyContentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/19 HerbLuo 首次创建
 */
public interface IAppBlockDao extends Repository<AppBlockEntity, Integer> {

    @Query("SELECT block FROM AppBlockEntity block WHERE block.name = 'ji_you_jia'")
    AppBlockEntity<AppJiyoujiaHeadEntity, AppJiyoujiaContentEntity> findJiYouJiaBlock();

    @Query("SELECT block FROM AppBlockEntity block WHERE block.name = 'rush_buy'")
    AppBlockEntity<?, AppRushbuyContentEntity> findRushBuyBlock();

}
