package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "message", schema = "shop")
data class MessageEntity (
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "message", nullable = false, length = 100)
        var message: String? = null,

        @get:Basic
        @get:Column(name = "type", nullable = false, length = 5)
        var type: String? = null,

        @get:Basic
        @get:Column(name = "is_read", nullable = false)
        var isAreRead: Boolean = false,

        @get:Basic
        @get:Column(name = "user_id", nullable = false)
        var userId: Int = 0
){
    constructor(message: String, type: String, userId: Int) : this() {
        this.message = message
        this.type = type
        this.userId = userId
    }
}
