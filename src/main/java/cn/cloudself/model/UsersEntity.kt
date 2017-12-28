package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "users", schema = "shop")
data class UsersEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "enabled", nullable = true)
        var enabled: Boolean? = null,

        @get:Basic
        @get:Column(name = "username", nullable = false, length = 16)
        var username: String? = null,

        @get:Basic
        @get:Column(name = "password", nullable = false, length = 32)
        var password: String? = null,

        @get:Basic
        @get:Column(name = "phone", nullable = true, length = 11)
        var phone: String? = null,

        @get:Basic
        @get:Column(name = "email", nullable = true, length = 64)
        var email: String? = null,

        @get:Basic
        @get:Column(name = "authority", nullable = false, length = 5)
        var authority: String? = null
)