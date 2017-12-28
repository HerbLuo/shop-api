package cn.cloudself.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.domain.Page

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/5/19 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_block", schema = "shop")
@ApiModel
data class AppBlockEntity<H, C>(
        @get:Id
        @get:Column(name = "id")
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "name")
        var name: String? = null,

        @get:Basic
        @get:Column(name = "title")
        var title: String? = null,

        @get:Basic
        @get:Column(name = "columnType")
        @get:ApiModelProperty("标志着数据的类型，（UI布局的类型）暂时没什么用")
        var columnType: Byte = 0,

        @get:Transient
        var head: Page<H>? = null,

        @get:Transient
        var content: Page<C>? = null
)
