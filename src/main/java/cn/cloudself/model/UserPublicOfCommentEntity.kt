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
data class UserPublicOfCommentEntity (
        @get:Id
        @get:Column(name = "id", nullable = false, updatable = false, insertable = false)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "nickname", length = 16, updatable = false, insertable = false)
        var nickname: String? = null,

        @get:Basic
        @get:Column(name = "avatar_src", length = 100, updatable = false, insertable = false)
        var avatarSrc: String? = null,

        @get:Basic
        @get:Column(name = "credit")
        var credit: Int = 0
) {

    fun clone(): UserPublicOfCommentEntity {
        return UserPublicOfCommentEntity(id, nickname, avatarSrc, credit)
    }

}
