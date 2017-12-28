package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "shop_detail", schema = "shop")
@DynamicInsert
data class ShopDetailEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "bail", nullable = false)
        var bail: Int = 0,

        @get:Basic
        @get:Column(name = "credit", nullable = false)
        var credit: Int = 0,

        @get:Basic
        @get:Column(name = "description_score", nullable = false)
        var descriptionScore: Double = 0.0,

        @get:Basic
        @get:Column(name = "service_score", nullable = false)
        var serviceScore: Double = 0.0,

        @get:Basic
        @get:Column(name = "logistics_score", nullable = false)
        var logisticsScore: Double = 0.0,

        @get:Basic
        @get:Column(name = "seller_name", nullable = false, length = 16)
        var sellerName: String? = null
)
