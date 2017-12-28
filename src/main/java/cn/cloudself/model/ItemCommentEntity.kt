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
@Table(name = "item_comment", schema = "shop")
data class ItemCommentEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "item_id", nullable = false)
        var itemId: Int = 0,

        @get:Basic
        @get:Column(name = "create_time", nullable = false)
        var createTime: Timestamp? = null,

        @get:Basic
        @get:Column(name = "comment")
        var comment: String? = null,

        @get:Basic
        @get:Column(name = "imgs_json", nullable = true)
        var imgsJson: String? = null,

        @get:Basic
        @get:Column(name = "score", nullable = false, precision = 0)
        var score: Double = 0.0,

        @get:Basic
        @get:Column(name = "user_id", nullable = false)
        var userId: Int = 0,

        @get:Basic
        @get:Column(name = "is_anonymous", nullable = true)
        var anonymous: Boolean? = null,

        @get:Transient
        var user: UserPublicOfCommentEntity? = null
)
