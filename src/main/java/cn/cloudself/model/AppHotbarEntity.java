package cn.cloudself.model;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/4/19 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_hotbar", schema = "shop")
public class AppHotbarEntity {
    private int id;
    private String typeText;
    private String titleText;
    private int type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_text", nullable = false, length = 2)
    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    @Basic
    @Column(name = "title_text", nullable = false, length = 20)
    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppHotbarEntity that = (AppHotbarEntity) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (typeText != null ? !typeText.equals(that.typeText) : that.typeText != null) return false;
        if (titleText != null ? !titleText.equals(that.titleText) : that.titleText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeText != null ? typeText.hashCode() : 0);
        result = 31 * result + (titleText != null ? titleText.hashCode() : 0);
        result = 31 * result + type;
        return result;
    }
}
