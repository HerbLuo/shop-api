package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/5/19 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_jiyoujia_head", schema = "shop")
@NamedNativeQueries(

        NamedNativeQuery(
                name = "AppJiyoujiaHeadEntity.getDoubleColumn",
                query = "(SELECT * FROM app_jiyoujia_head WHERE `type` = 0 LIMIT ?1, ?2)" +
                        "UNION (SELECT * FROM app_jiyoujia_head WHERE `type` = 1 LIMIT ?1, ?2)",
                resultClass = AppJiyoujiaHeadEntity::class),

        NamedNativeQuery(
                name = "AppJiyoujiaHeadEntity.maxCountOfDoubleColumn",
                query = "SELECT MAX(`group_count`) AS `id` FROM (" +
                        "(SELECT COUNT(*) AS `group_count` FROM app_jiyoujia_head WHERE `type` = 0)" +
                        "UNION(SELECT COUNT(*) FROM app_jiyoujia_head WHERE `type` = 1)" +
                        ") as v_table;",
                resultClass = IntegerEntity::class)
)
data class AppJiyoujiaHeadEntity (
        @get:Id
        @get:Column(name = "id")
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "type")
        var type: Byte = 0,

        @get:Basic
        @get:Column(name = "img")
        var img: String? = null,

        @get:Basic
        @get:Column(name = "text")
        var text: String? = null
)