package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "shop", schema = "shop")
data class ShopEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "enabled", nullable = false)
        var isEnabled: Boolean = false,

        @get:Basic
        @get:Column(name = "name", nullable = true, length = 16)
        var name: String? = null,

        @get:Basic
        @get:Column(name = "seller_id", nullable = false)
        var sellerId: Int? = null
)
