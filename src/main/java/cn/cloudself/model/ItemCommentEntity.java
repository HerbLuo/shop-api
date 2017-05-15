package cn.cloudself.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "item_comment", schema = "shop")
public class ItemCommentEntity {
    private int id;
    private int itemId;
    private Timestamp createTime;
    private String comment;
    private String imgsJson;
    private double score;
    private int userId;
    private Boolean isAnonymous;
    private UserPublicOfCommentEntity user;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "item_id", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "score", nullable = false, precision = 0)
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "imgs_json", nullable = true)
    public String getImgsJson() {
        return imgsJson;
    }

    public void setImgsJson(String imgsJson) {
        this.imgsJson = imgsJson;
    }

    @Basic
    @Column(name = "is_anonymous", nullable = true)
    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    @Transient
    public UserPublicOfCommentEntity getUser() {
        return user;
    }

    public void setUser(UserPublicOfCommentEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemCommentEntity that = (ItemCommentEntity) o;

        if (id != that.id) return false;
        if (itemId != that.itemId) return false;
        if (Double.compare(that.score, score) != 0) return false;
        if (userId != that.userId) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (imgsJson != null ? !imgsJson.equals(that.imgsJson) : that.imgsJson != null) return false;
        if (isAnonymous != null ? !isAnonymous.equals(that.isAnonymous) : that.isAnonymous != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + itemId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (imgsJson != null ? imgsJson.hashCode() : 0);
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + userId;
        result = 31 * result + (isAnonymous != null ? isAnonymous.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
