package cn.cloudself.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/4/9 HerbLuo 首次创建
 */
@Entity
@DynamicUpdate
@Table(name = "app", schema = "shop")
public class AppEntity {
    private int id;
    private int versionCode;
    private String versionName;
    private String androidDownloadUrl;
    private String appEntranceVersion;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "android_download_url", nullable = false)
    public String getAndroidDownloadUrl() {
        return androidDownloadUrl;
    }

    public void setAndroidDownloadUrl(String androidDownloadUrl) {
        this.androidDownloadUrl = androidDownloadUrl;
    }

    @Basic
    @Column(name = "version_code", nullable = false)
    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    @Basic
    @Column(name = "version_name", nullable = false, length = 16)
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Basic
    @Column(name = "app_entrance_version", nullable = false)
    public String getAppEntranceVersion() {
        return appEntranceVersion;
    }

    public void setAppEntranceVersion(String appEntranceVersion) {
        this.appEntranceVersion = appEntranceVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppEntity appEntity = (AppEntity) o;

        if (id != appEntity.id) return false;
        if (versionCode != appEntity.versionCode) return false;
        if (versionName != null ? !versionName.equals(appEntity.versionName) : appEntity.versionName != null)
            return false;
        if (androidDownloadUrl != null ? !androidDownloadUrl.equals(appEntity.androidDownloadUrl) : appEntity.androidDownloadUrl != null)
            return false;
        return appEntranceVersion != null ? appEntranceVersion.equals(appEntity.appEntranceVersion) : appEntity.appEntranceVersion == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + versionCode;
        result = 31 * result + (versionName != null ? versionName.hashCode() : 0);
        result = 31 * result + (androidDownloadUrl != null ? androidDownloadUrl.hashCode() : 0);
        result = 31 * result + (appEntranceVersion != null ? appEntranceVersion.hashCode() : 0);
        return result;
    }
}
