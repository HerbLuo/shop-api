package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/4/19 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_hotbar", schema = "shop")
data class AppHotbarEntity (
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "type_text", nullable = false, length = 2)
        var typeText: String? = null,

        @get:Basic
        @get:Column(name = "title_text", nullable = false, length = 20)
        var titleText: String? = null,

        @get:Basic
        @get:Column(name = "type", nullable = false)
        var type: Int = 0
)