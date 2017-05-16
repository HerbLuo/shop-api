package cn.cloudself.model;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/10 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_rushbuy", schema = "shop")
@NamedNativeQuery(
        name = "AppRushbuyEntity.getRushbuy",
        query = "(SELECT * FROM app_rushbuy WHERE `index` = 1 LIMIT 2) " + // 该字符串编译后自动合并
                "UNION (SELECT * FROM app_rushbuy WHERE `index` = 2 LIMIT 2) " +
                "UNION (SELECT * FROM app_rushbuy WHERE `index` = 3 LIMIT 2) " +
                "UNION (SELECT * FROM app_rushbuy WHERE `index` = 4 LIMIT 2)",
        resultClass = AppRushbuyEntity.class
)
public class AppRushbuyEntity {
    private int id;
    private String type;
    private String img;
    private byte index;
    private String link;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "index")
    public byte getIndex() {
        return index;
    }

    public void setIndex(byte index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppRushbuyEntity that = (AppRushbuyEntity) o;

        if (id != that.id) return false;
        if (index != that.index) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (int) index;
        return result;
    }

    @Override
    public String toString() {
        return "AppRushbuyEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", img='" + img + '\'' +
                ", index=" + index +
                ", link='" + link + '\'' +
                '}';
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
