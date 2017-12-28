package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "item_detail_html", schema = "shop")
data class ItemDetailHtmlEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "version", nullable = true, length = 10)
        var version: String? = null,

        @get:Basic
        @get:Column(name = "div", nullable = true, length = 1024)
        var div: String? = null
)
