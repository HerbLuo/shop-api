package cn.cloudself.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/10 HerbLuo 首次创建
 */
@SuppressWarnings("SqlDialectInspection")
@Entity
@Table(name = "app_rushbuy", schema = "shop")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AppRushbuyContentEntity.getData",
                query = "(SELECT * FROM app_rushbuy WHERE `index` = 1 LIMIT ?1, ?2)" + // 该字符串编译后自动合并
                        "UNION (SELECT * FROM app_rushbuy WHERE `index` = 2 LIMIT ?1, ?2) " +
                        "UNION (SELECT * FROM app_rushbuy WHERE `index` = 3 LIMIT ?1, ?2) " +
                        "UNION (SELECT * FROM app_rushbuy WHERE `index` = 4 LIMIT ?1, ?2)",
                resultClass = AppRushbuyContentEntity.class
        ),
        @NamedNativeQuery(
                name = "AppRushbuyContentEntity.maxCount",
                query = "SELECT MAX(`group_count`) AS `id` FROM (" +
                        "(SELECT COUNT(*) AS `group_count` FROM app_rushbuy WHERE `index` = 1)" +
                        "UNION(SELECT COUNT(*) FROM app_rushbuy WHERE `index` = 2)" +
                        "UNION(SELECT COUNT(*) FROM app_rushbuy WHERE `index` = 3)" +
                        "UNION(SELECT COUNT(*) FROM app_rushbuy WHERE `index` = 4)" +
                        ") as v_table;",
                resultClass = IntegerEntity.class
        )
})
public class AppRushbuyContentEntity {
    private int id;
    private String type;
    private String img;
    private byte index;
    private String link;
    private Timestamp startTimestamp;

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

    @Basic
    @Column(name = "start_timestamp")
    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppRushbuyContentEntity that = (AppRushbuyContentEntity) o;

        if (id != that.id) return false;
        if (index != that.index) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        return startTimestamp != null ? startTimestamp.equals(that.startTimestamp) : that.startTimestamp == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (int) index;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (startTimestamp != null ? startTimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppRushbuyContentEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", img='" + img + '\'' +
                ", index=" + index +
                ", link='" + link + '\'' +
                ", startTimestamp=" + startTimestamp +
                '}';
    }
}
