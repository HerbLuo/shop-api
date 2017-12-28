package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "car", schema = "shop")
data class CarEntity(

        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "user_id", nullable = false)
        var userId: Int = 0,

        @Suppress("MemberVisibilityCanPrivate")
        @get:ManyToOne
        @get:JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
        var item: ItemEntity? = null

) {
    constructor(id: Int, item: ItemEntity?) : this() {
        this.id = id
        this.item = item
    }
}
