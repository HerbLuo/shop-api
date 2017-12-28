package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "item_detail_describe", schema = "shop")
data class ItemDetailDescribeEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "describe_json_array", nullable = true, length = -1)
        var describeJsonArray: String? = null
)