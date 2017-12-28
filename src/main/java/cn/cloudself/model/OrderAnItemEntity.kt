package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "order_an_item", schema = "shop")
data class OrderAnItemEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "quantity", nullable = false)
        var quantity: Int = 0,

        @get:ManyToOne
        @get:JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
        var item: ItemEntity? = null
)
