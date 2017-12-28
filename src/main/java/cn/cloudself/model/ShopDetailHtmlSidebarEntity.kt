package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "shop_detail_html_sidebar", schema = "shop")
data class ShopDetailHtmlSidebarEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,
        @get:Basic
        @get:Column(name = "xss_html", nullable = true, length = -1)
        var xssHtml: String? = null
)
