package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/6/13 HerbLuo 首次创建
 */
@Entity
@Table(name = "prov_city_area_street", schema = "shop")
data class ProvCityAreaStreetEntity(
        @get:Id
        @get:Column(name = "id")
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "code")
        var code: Int? = null,

        @get:Basic
        @get:Column(name = "parentId")
        var parentId: Int? = null,

        @get:Basic
        @get:Column(name = "name")
        var name: String? = null,

        @get:Basic
        @get:Column(name = "level", length = 3)
        var level: Int? = null
)

