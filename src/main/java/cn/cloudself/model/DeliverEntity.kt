package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "deliver", schema = "shop")
data class DeliverEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "number", nullable = true, length = 64)
        var number: String? = null,

        @get:Basic
        @get:Column(name = "name", nullable = true, length = 10)
        var name: String? = null,

        @get:Basic
        @get:Column(name = "order_a_shop_id")
        var orderAShopId: Int? = null
)
