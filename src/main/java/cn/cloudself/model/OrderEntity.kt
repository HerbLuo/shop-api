package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*
import java.sql.Timestamp

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "`order`", schema = "shop")
data class OrderEntity (
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "enabled")
        var enabled: Boolean? = null,

        @get:Basic
        @get:Column(name = "create_time", nullable = true)
        var createTime: Timestamp? = null,

        @get:Basic
        @get:Column(name = "user_id", nullable = false)
        var userId: Int = 0,

        @get:Basic
        @get:Column(name = "is_finished", nullable = true)
        var areFinished: Boolean? = null,

        @get:Basic
        @get:Column(name = "is_paied", nullable = true)
        var arePaied: Boolean? = null,

        @get:OneToMany(cascade = arrayOf(CascadeType.PERSIST))
        @get:JoinColumn(name = "order_id", referencedColumnName = "id", nullable = true)
        var orderAShops: Collection<OrderAShopEntity>? = null
)
