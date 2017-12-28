package cn.cloudself.service.impl

import cn.cloudself.bean.EntitysWithVersion
import cn.cloudself.components.annotation.ParamChecker
import cn.cloudself.dao.*
import cn.cloudself.exception.http.RequestBadException
import cn.cloudself.exception.http.ServerException
import cn.cloudself.model.*
import cn.cloudself.service.IAppService
import com.sun.javaws.util.JavawsConsoleController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sun.security.krb5.internal.PAData

import java.util.ArrayList

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/3/28 HerbLuo 首次创建
 */
@Service
@Transactional
open class AppServiceImpl @Autowired
constructor(
        private val appDao: IAppDao,
        private val appSliderDao: IAppSliderDao,
        private val appEntranceDao: IAppEntranceDao,
        private val appHotbarDao: IAppHotbarDao,
        private val appRushbuyDao: IAppRushbuyContentDao,
        private val appJiyoujiaDao: IAppJiyoujiaContentDao,
        private val appBlockDao: IAppBlockDao,
        private val appJiyoujiaHeadDao: IAppJiyoujiaHeadDao,
        private val appComponentVersionDao: IAppComponentVersionDao
) : IAppService {

    /**
     * 获取当前app的版本
     *
     *
     * change log:
     * 170613
     * herbluo
     * 抽离app组件version
     *
     * @return .
     */
    @Throws(Exception::class)
    override fun getAppInfo(): AppEntity {

        val appEntity = appDao.appInfo
        appEntity.appComponentVersion = appComponentVersionDao.findAll()

        return appEntity
    }

    /**
     * TODO 未完成
     * 获取轮播图内容
     *
     * @param userId 用户
     * @param size   大小
     * @return 轮播图s
     */
    @ParamChecker(
            greaterThan0 = intArrayOf(1),
            lessThan = intArrayOf(20),
            lessThanIndex = intArrayOf(1)
    )
    @Throws(Exception::class)
    override fun getSilder(userId: Int, size: Int): Iterable<AppSliderEntity> {

        val ids = ArrayList<Int>(8)
        ids += 1..8

        return appSliderDao.findAll(ids)
    }

    /**
     * 获取app的十个入口
     *
     * @return .
     */
    @Throws(Exception::class)
    override fun getEntraceBar(): EntitysWithVersion<AppEntranceEntity> {

        val appEntranceEntities = appEntranceDao.findFirst10ByEnabledTrue()

        return EntitysWithVersion(appInfo.appEntranceVersion, appEntranceEntities)
    }

    /**
     * 获取hotBar的内容
     *
     * @return .
     */
    @Throws(Exception::class)
    override fun getHotbar(): Iterable<AppHotbarEntity> {
        return appHotbarDao.findAll()
    }

    /**
     * 获取抢购内容
     */
    @ParamChecker(greaterOrEqual0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getRushbuy(page: Int, aPageSize: Int): AppBlockEntity<*, AppRushbuyContentEntity> {

        val blockEntity = appBlockDao.findRushBuyBlock()

        blockEntity.content = getBlock<AppRushbuyContentEntity>(
                page,
                aPageSize,
                { appRushbuyDao.maxCount() },
                { startFrom, size -> appRushbuyDao.getData(startFrom, size) }
        )

        return blockEntity
    }

    /**
     * 获取极有家的内容
     *
     * @param page start from 0
     */
    @ParamChecker(greaterOrEqual0 = intArrayOf(0))
    @Throws(Exception::class)
    override fun getJiyoujia(
            page: Int, aPageSize: Int
    ): AppBlockEntity<AppJiyoujiaHeadEntity, AppJiyoujiaContentEntity> {

        // check
        if (aPageSize < -1 || aPageSize == 0) {
            throw RequestBadException("错误的参数: aPageSize")
        }

        // block 信息，包含极有家的描述等
        val appBlockEntity = appBlockDao.findJiYouJiaBlock()
                ?: throw ServerException("数据库出错，未查询到AppBlock_极有家")

        // check
        if (appBlockEntity.columnType.toInt() != 2) {
            throw ServerException("逻辑错误")
        }

        // 创建 head page
        appBlockEntity.head = getBlock<AppJiyoujiaHeadEntity>(
                page,
                aPageSize,
                { appJiyoujiaHeadDao.maxCountOfDoubleColumn() },
                { start, length -> appJiyoujiaHeadDao.getDoubleColumn(start, length) }
        )


        // 创建 content page
        appBlockEntity.content = getBlock<AppJiyoujiaContentEntity>(
                page,
                aPageSize,
                { appJiyoujiaDao.maxCount() },
                { startFrom, size -> appJiyoujiaDao.getData(startFrom, size) }
        )

        // return
        return appBlockEntity

    }

    /*
     * private
     */
    private fun <T> getBlock(
            page: Int,
            aPageSize: Int,
            fGetCount: () -> IntegerEntity,
            fGetData: (startFrom: Int, length: Int) -> List<T>
    ): Page<T> {

        // block有多少组
        val count = fGetCount().toInt()

        // 如果aPageSize为 -1 重新定义page和aPageSize
        val _page = if (aPageSize == -1) 0 else page
        val _aPageSize = if (aPageSize == -1) count else aPageSize

        val pageable = PageRequest(_page, _aPageSize)

        // 页数不足
        val isPageOverflow = _page >= Math.ceil((count / _aPageSize).toDouble())

        // 返回的具体内容
        val content = if (isPageOverflow)
            ArrayList()
        else
            fGetData(_page * _aPageSize, _aPageSize)

        return PageImpl(content, pageable, count.toLong())

    }

}
