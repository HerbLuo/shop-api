package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/4/13 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_entrance", schema = "shop")
data class AppEntranceEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "enabled", nullable = false)
        var enabled: Boolean = false,

        @get:Basic
        @get:Column(name = "index", nullable = false)
        var index: Byte = 0,

        @get:Basic
        @get:Column(name = "name", nullable = false, length = 6)
        var name: String? = null,

        @get:Basic
        @get:Column(name = "img", nullable = false, length = 255)
        var img: String? = null,

        @get:Basic
        @get:Column(name = "link", nullable = false, length = 255)
        var link: String? = null
)