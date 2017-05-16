package cn.cloudself.model;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/16 HerbLuo 首次创建
 */
@SuppressWarnings("SqlDialectInspection")
@Entity
@Table(name = "app_jiyoujia", schema = "shop")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AppJiyoujiaEntity.getData",
                query = "(SELECT * FROM app_jiyoujia WHERE `index` = 1 LIMIT ?1, ?2)" +
                        "UNION (SELECT * FROM app_jiyoujia WHERE `index` = 2 LIMIT ?1, ?2)" +
                        "UNION (SELECT * FROM app_jiyoujia WHERE `index` = 3 LIMIT ?1, ?2)" +
                        "UNION (SELECT * FROM app_jiyoujia WHERE `index` = 4 LIMIT ?1, ?2)",
                resultClass = AppJiyoujiaEntity.class
        ),
        @NamedNativeQuery(
                name = "AppJiyoujiaEntity.maxCount",
                query = "SELECT MAX(`group_count`) AS `id` FROM (" +
                        "(SELECT COUNT(*) AS `group_count` FROM app_jiyoujia WHERE `index` = 1)" +
                        "UNION(SELECT COUNT(*) FROM app_jiyoujia WHERE `index` = 2)" +
                        "UNION(SELECT COUNT(*) FROM app_jiyoujia WHERE `index` = 3)" +
                        "UNION(SELECT COUNT(*) FROM app_jiyoujia WHERE `index` = 4)" +
                        ") as v_table;",
                resultClass = IntegerEntity.class
        )
})
public class AppJiyoujiaEntity {
    private int id;
    private Byte index;
    private String img;
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
    @Column(name = "index")
    public Byte getIndex() {
        return index;
    }

    public void setIndex(Byte index) {
        this.index = index;
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
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppJiyoujiaEntity that = (AppJiyoujiaEntity) o;

        if (id != that.id) return false;
        if (index != null ? !index.equals(that.index) : that.index != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
