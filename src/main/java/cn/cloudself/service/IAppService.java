package cn.cloudself.service;

import cn.cloudself.bean.EntitysWithVersion;
import cn.cloudself.model.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/3/28 HerbLuo 首次创建
 */
public interface IAppService {

    /**
     * 获取当前app的版本
     * @return .
     */
    AppEntity getAppInfo() throws Exception;

    /**
     * 获取轮播图内容
     * @param userId 用户
     * @param size 大小
     * @return 轮播图s
     */
    Iterable<AppSliderEntity> getSilder(Integer userId, Integer size) throws Exception;

    /**
     * 获取app的十个入口
     * @return .
     */
    EntitysWithVersion<AppEntranceEntity> getEntraceBar() throws Exception;

    /**
     * 获取hotBar的内容
     * @return .
     */
    Iterable<AppHotbarEntity> getHotbar() throws Exception;

    /**
     * 获取抢购内容
     * @return .
     */
    Iterable<AppRushbuyEntity> getRushbuy() throws Exception;

    /**
     * 获取极有家的内容
     *
     * @param page 从0开始计
     * @return .
     */
    AppBlockEntity getJiyoujia(Integer page, Integer aPageSize) throws Exception;

}
