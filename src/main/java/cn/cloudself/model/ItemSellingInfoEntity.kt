package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "item_selling_info", schema = "shop")
data class ItemSellingInfoEntity(
        @get:Id
        @get:Column(name = "item_id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var itemId: Int = 0,

        @get:Basic
        @get:Column(name = "quantity", nullable = false)
        var quantity: Int = 0,

        @get:Basic
        @get:Column(name = "sales", nullable = false)
        var sales: Int = 0,

        @get:Basic
        @get:Column(name = "comment_num", nullable = false)
        var commentNum: Int = 0,

        @get:Basic
        @get:Column(name = "score", nullable = false, precision = 0)
        var score: Double = 0.0,

        @get:Basic
        @get:Column(name = "in_ordering")
        var inOrdering: Int = 0
)
