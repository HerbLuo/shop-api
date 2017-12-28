package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "address", schema = "shop")
data class AddressEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "enabled", nullable = false)
        var isEnabled: Boolean = false,

        @get:Basic
        @get:Column(name = "address", nullable = false, length = 255)
        var address: String? = null,

        @get:Basic
        @get:Column(name = "post_code", nullable = false, length = 6)
        var postCode: String? = null,

        @get:Basic
        @get:Column(name = "receiver_name", nullable = false, length = 12)
        var receiverName: String? = null,

        @get:Basic
        @get:Column(name = "phone", nullable = false, length = 16)
        var phone: String? = null,

        @get:Basic
        @get:Column(name = "user_id", nullable = false)
        var userId: Int = 0,

        @get:Basic
        @get:Column(name = "is_default", nullable = false)
        var isAreDefault: Boolean = false
)