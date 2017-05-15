package cn.cloudself.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "`order`", schema = "shop")
public class OrderEntity {
    private int id;
    private Boolean enabled;
    private Timestamp createTime;
    private int userId;
    private Boolean areFinished;
    private Boolean arePaied;
    private Collection<OrderAShopEntity> orderAShops;

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
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enable) {
        this.enabled = enable;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
    @Column(name = "is_finished", nullable = true)
    public Boolean getAreFinished() {
        return areFinished;
    }

    public void setAreFinished(Boolean areFinished) {
        this.areFinished = areFinished;
    }

    @Basic
    @Column(name = "is_paied", nullable = true)
    public Boolean getArePaied() {
        return arePaied;
    }

    public void setArePaied(Boolean arePaied) {
        this.arePaied = arePaied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (enabled != that.enabled) return false;
        if (userId != that.userId) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (areFinished != null ? !areFinished.equals(that.areFinished) : that.areFinished != null) return false;
        if (arePaied != null ? !arePaied.equals(that.arePaied) : that.arePaied != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (areFinished != null ? areFinished.hashCode() : 0);
        result = 31 * result + (arePaied != null ? arePaied.hashCode() : 0);
        return result;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = true)
    public Collection<OrderAShopEntity> getOrderAShops() {
        return orderAShops;
    }

    public void setOrderAShops(Collection<OrderAShopEntity> orderAShops) {
        this.orderAShops = orderAShops;
    }
}
