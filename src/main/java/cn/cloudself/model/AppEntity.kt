package cn.cloudself.model

import org.hibernate.annotations.DynamicUpdate

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/4/9 HerbLuo 首次创建
 */
@Entity
@DynamicUpdate
@Table(name = "app", schema = "shop")
data class AppEntity (
        @get:Id
        @get:Column(name = "id", nullable = false)
        var id: Int = 0,

        @get:Basic
        @get:Column(name = "version_code", nullable = false)
        var versionCode: Int = 0,

        @get:Basic
        @get:Column(name = "version_name", nullable = false, length = 16)
        var versionName: String? = null,

        @get:Basic
        @get:Column(name = "android_download_url", nullable = false)
        var androidDownloadUrl: String? = null,

        @get:Basic
        @get:Column(name = "app_entrance_version", nullable = false)
        var appEntranceVersion: String? = null,

        @get:Transient
        var appComponentVersion: Iterable<AppComponentVersionEntity>? = null
)