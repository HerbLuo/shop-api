package cn.cloudself.model

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.OnDelete

import javax.persistence.*
import java.sql.Timestamp

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "item", schema = "shop")
data class ItemEntity(
        @get:Id
        @get:Column(name = "id", nullable = false)
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "enabled", nullable = false)
        var isEnabled: Boolean = false,

        @get:Basic
        @get:Column(name = "modify_time", nullable = true)
        var modifyTime: Timestamp? = null,

        @get:Basic
        @get:Column(name = "version", nullable = true, length = 6)
        var version: String? = null,

        @get:Basic
        @get:Column(name = "name", nullable = false, length = 50)
        var name: String? = null,

        @get:Basic
        @get:Column(name = "price", nullable = false, precision = 0)
        var price: Double = 0.0,

        @get:Basic
        @get:Column(name = "description", nullable = true, length = 255)
        var description: String? = null,

        @get:Basic
        @get:Column(name = "pic_links_json", nullable = true, length = 500)
        var picLinksJson: String? = null,

        @get:OneToOne(fetch = FetchType.LAZY)
        @get:JoinColumn(name = "id", referencedColumnName = "item_id")
        var itemSellingInfo: ItemSellingInfoEntity? = null,

        @get:Basic
        @get:Column(name = "detail_div_id", nullable = true)
        var detailDivId: Int? = null,

        @get:ManyToOne
        @get:JoinColumn(name = "shop_id", referencedColumnName = "id")
        var shop: ShopEntity? = null
) {
    constructor(id: Int) : this() {
        this.id = id
    }
}
