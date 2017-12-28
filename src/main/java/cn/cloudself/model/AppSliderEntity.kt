package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/3/28 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_slider", schema = "shop")
data class AppSliderEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,
        @get:Basic
        @get:Column(name = "enabled", nullable = false)
        var enabled: Byte = 0,
        @get:Basic
        @get:Column(name = "imgSrc", nullable = false, length = 128)
        var imgSrc: String? = null,
        @get:Basic
        @get:Column(name = "itemId", nullable = true)
        var itemId: Int? = null,
        @get:Basic
        @get:Column(name = "link", nullable = true, length = 255)
        var link: String? = null,
        @get:Basic
        @get:Column(name = "category", nullable = true)
        var category: Int? = null,
        @get:Basic
        @get:Column(name = "shopId", nullable = true)
        var shopId: Int? = null
)
