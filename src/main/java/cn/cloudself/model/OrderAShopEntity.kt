package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "order_a_shop", schema = "shop")
data class OrderAShopEntity (
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)

        var id: Int = 0,
        @get:Basic
        @get:Column(name = "enabled")
        var enabled: Boolean? = null,

        @get:Basic
        @get:Column(name = "is_deliver")
        var areDeliver: Boolean? = null,

        @get:Basic
        @get:Column(name = "is_receive")
        var areReceive: Boolean? = null,

        @get:Basic
        @get:Column(name = "is_evaluate")
        var areEvaluate: Boolean? = null,

        @get:Basic
        @get:Column(name = "discount", nullable = true, precision = 0)
        var discount: Double? = null,

        @get:Basic
        @get:Column(name = "memo", nullable = true, length = 64)
        var memo: String? = null,

        @get:ManyToOne
        @get:JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false)
        var shop: ShopEntity? = null,

        @get:ManyToOne
        @get:JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
        var address: AddressEntity? = null,

        @get:Basic
        @get:Column(name = "user_id", nullable = true)
        var userId: Int? = null,

        @get:OneToMany(cascade = arrayOf(CascadeType.PERSIST))
        @get:JoinColumn(name = "order_a_shop_id", referencedColumnName = "id")
        var orderAnItems: Collection<OrderAnItemEntity>? = null
)
