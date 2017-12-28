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
@Table(name = "app_component_version", schema = "shop")
data class AppComponentVersionEntity(
        @get:Id
        @get:Column(name = "id")
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "name")
        var name: String? = null,

        @get:Basic
        @get:Column(name = "version")
        var version: String? = null
)
