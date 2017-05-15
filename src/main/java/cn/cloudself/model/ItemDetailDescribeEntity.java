package cn.cloudself.model;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "item_detail_describe", schema = "shop")
public class ItemDetailDescribeEntity {
    private int id;
    private String describeJsonArray;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "describe_json_array", nullable = true, length = -1)
    public String getDescribeJsonArray() {
        return describeJsonArray;
    }

    public void setDescribeJsonArray(String describeJsonArray) {
        this.describeJsonArray = describeJsonArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDetailDescribeEntity that = (ItemDetailDescribeEntity) o;

        if (id != that.id) return false;
        if (describeJsonArray != null ? !describeJsonArray.equals(that.describeJsonArray) : that.describeJsonArray != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (describeJsonArray != null ? describeJsonArray.hashCode() : 0);
        return result;
    }
}
