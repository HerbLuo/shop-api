package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "user", schema = "shop")
data class UserEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "nickname", nullable = true, length = 16)
        var nickname: String? = null,

        @get:Basic
        @get:Column(name = "vip", nullable = true)
        var vip: Byte? = null,

        @get:Basic
        @get:Column(name = "message_num", nullable = true)
        var messageNum: Int? = null,

        @get:Basic
        @get:Column(name = "car_num", nullable = true)
        var carNum: Int? = null,

        @get:Basic
        @get:Column(name = "avatar_src")
        var imgAvatar: String? = null,

        @get:Basic
        @get:Column(name = "credit")
        var credit: Int = 0
)
