package cn.cloudself.model

import javax.persistence.*
import java.sql.Timestamp

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/5/10 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_rushbuy", schema = "shop")
@NamedNativeQueries(
        NamedNativeQuery(
                name = "AppRushbuyContentEntity.getData",
                query = "(SELECT * FROM app_rushbuy WHERE `index` = 1 LIMIT ?1, ?2)" +
                        "UNION (SELECT * FROM app_rushbuy WHERE `index` = 2 LIMIT ?1, ?2) " +
                        "UNION (SELECT * FROM app_rushbuy WHERE `index` = 3 LIMIT ?1, ?2) " +
                        "UNION (SELECT * FROM app_rushbuy WHERE `index` = 4 LIMIT ?1, ?2)",
                resultClass = AppRushbuyContentEntity::class
        ),
        NamedNativeQuery(
                name = "AppRushbuyContentEntity.maxCount",
                query = "SELECT MAX(`group_count`) AS `id` FROM (" +
                "(SELECT COUNT(*) AS `group_count` FROM app_rushbuy WHERE `index` = 1)" +
                "UNION(SELECT COUNT(*) FROM app_rushbuy WHERE `index` = 2)" +
                "UNION(SELECT COUNT(*) FROM app_rushbuy WHERE `index` = 3)" +
                "UNION(SELECT COUNT(*) FROM app_rushbuy WHERE `index` = 4)" +
                ") as v_table;",
                resultClass = IntegerEntity::class
        )
)
data class AppRushbuyContentEntity(
        @get:Id
        @get:Column(name = "id")
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "type")
        var type: String? = null,

        @get:Basic
        @get:Column(name = "img")
        var img: String? = null,

        @get:Basic
        @get:Column(name = "index")
        var index: Byte = 0,

        @get:Basic
        @get:Column(name = "link")
        var link: String? = null,

        @get:Basic
        @get:Column(name = "start_timestamp")
        var startTimestamp: Timestamp? = null
)
