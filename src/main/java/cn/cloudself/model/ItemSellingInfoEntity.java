package cn.cloudself.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "item_selling_info", schema = "shop")
public class ItemSellingInfoEntity {
    private int itemId;
    private int quantity;
    private int sales;
    private int commentNum;
    private double score;
    private int inOrdering;

    @Id
    @Column(name = "item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "sales", nullable = false)
    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Basic
    @Column(name = "comment_num", nullable = false)
    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    @Basic
    @Column(name = "score", nullable = false, precision = 0)
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemSellingInfoEntity that = (ItemSellingInfoEntity) o;

        if (itemId != that.itemId) return false;
        if (quantity != that.quantity) return false;
        if (sales != that.sales) return false;
        if (commentNum != that.commentNum) return false;
        if (Double.compare(that.score, score) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemId;
        result = 31 * result + quantity;
        result = 31 * result + sales;
        result = 31 * result + commentNum;
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Basic
    @Column(name = "in_ordering")
    public int getInOrdering() {
        return inOrdering;
    }

    public void setInOrdering(int inOrdering) {
        this.inOrdering = inOrdering;
    }
}
