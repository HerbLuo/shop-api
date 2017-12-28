package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/5/16 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_jiyoujia", schema = "shop")
@NamedNativeQueries(NamedNativeQuery(name = "AppJiyoujiaContentEntity.getData", query = "(SELECT * FROM app_jiyoujia WHERE `index` = 1 LIMIT ?1, ?2)" +
        "UNION (SELECT * FROM app_jiyoujia WHERE `index` = 2 LIMIT ?1, ?2)" +
        "UNION (SELECT * FROM app_jiyoujia WHERE `index` = 3 LIMIT ?1, ?2)" +
        "UNION (SELECT * FROM app_jiyoujia WHERE `index` = 4 LIMIT ?1, ?2)", resultClass = AppJiyoujiaContentEntity::class), NamedNativeQuery(name = "AppJiyoujiaContentEntity.maxCount", query = "SELECT MAX(`group_count`) AS `id` FROM (" +
        "(SELECT COUNT(*) AS `group_count` FROM app_jiyoujia WHERE `index` = 1)" +
        "UNION(SELECT COUNT(*) FROM app_jiyoujia WHERE `index` = 2)" +
        "UNION(SELECT COUNT(*) FROM app_jiyoujia WHERE `index` = 3)" +
        "UNION(SELECT COUNT(*) FROM app_jiyoujia WHERE `index` = 4)" +
        ") as v_table;", resultClass = IntegerEntity::class))
class AppJiyoujiaContentEntity {
    @get:Id
    @get:Column(name = "id")
    var id: Int = 0
    @get:Basic
    @get:Column(name = "index")
    var index: Byte? = null
    @get:Basic
    @get:Column(name = "img")
    var img: String? = null
    @get:Basic
    @get:Column(name = "link")
    var link: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as AppJiyoujiaContentEntity?

        if (id != that!!.id) return false
        if (if (index != null) index != that.index else that.index != null) return false
        if (if (img != null) img != that.img else that.img != null) return false
        return if (if (link != null) link != that.link else that.link != null) false else true

    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + if (index != null) index!!.hashCode() else 0
        result = 31 * result + if (img != null) img!!.hashCode() else 0
        result = 31 * result + if (link != null) link!!.hashCode() else 0
        return result
    }
}
