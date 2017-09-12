package cn.cloudself.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/19 HerbLuo 首次创建
 */
@Entity
@Table(name = "app_block", schema = "shop")
@ApiModel
public class AppBlockEntity<H, C> {
    private int id;
    private String name;
    private String title;
    private byte columnType;

    private Page<H> head;
    private Page<C> content;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppBlockEntity that = (AppBlockEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "columnType")
    @ApiModelProperty("标志着数据的类型，（UI布局的类型）暂时没什么用")
    public byte getColumnType() {
        return columnType;
    }

    public void setColumnType(byte columnType) {
        this.columnType = columnType;
    }

    @Transient
    public Page<H> getHead() {
        return head;
    }

    public void setHead(Page<H> head) {
        this.head = head;
    }

    @Transient
    public Page<C> getContent() {
        return content;
    }

    public void setContent(Page<C> content) {
        this.content = content;
    }
}
