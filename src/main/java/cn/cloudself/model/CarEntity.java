package cn.cloudself.model;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "car", schema = "shop")
public class CarEntity {
    private int id;
    private int userId;
    private ItemEntity item;

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
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (id != carEntity.id) return false;
        if (userId != carEntity.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public CarEntity() {
    }

    public CarEntity(int userId, ItemEntity item) {
        this.userId = userId;
        this.item = item;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", item=" + item +
                '}';
    }
}
