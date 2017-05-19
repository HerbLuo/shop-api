package cn.cloudself.model;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/19 HerbLuo 首次创建
 */
@SuppressWarnings("SqlDialectInspection")
@Entity
@Table(name = "app_jiyoujia_head", schema = "shop")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AppJiyoujiaHeadEntity.getDoubleColumn",
                query = "(SELECT * FROM app_jiyoujia_head WHERE `type` = 0 LIMIT ?1, ?2)" +
                        "UNION (SELECT * FROM app_jiyoujia_head WHERE `type` = 1 LIMIT ?1, ?2)",
                resultClass = AppJiyoujiaHeadEntity.class
        ),
        @NamedNativeQuery(
                name = "AppJiyoujiaHeadEntity.maxCountOfDoubleColumn",
                query = "SELECT MAX(`group_count`) AS `id` FROM (" +
                        "(SELECT COUNT(*) AS `group_count` FROM app_jiyoujia_head WHERE `type` = 0)" +
                        "UNION(SELECT COUNT(*) FROM app_jiyoujia_head WHERE `type` = 1)" +
                        ") as v_table;",
                resultClass = IntegerEntity.class
        )
})
public class AppJiyoujiaHeadEntity {
    private int id;
    private byte type;
    private String img;
    private String text;

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
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
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
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppJiyoujiaHeadEntity that = (AppJiyoujiaHeadEntity) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) type;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
