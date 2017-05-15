package cn.cloudself.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "order_a_shop", schema = "shop")
public class OrderAShopEntity {
    private int id;
    private Boolean enabled;
    private Boolean areDeliver;
    private Boolean areReceive;
    private Boolean areEvaluate;
    private Double discount;
    private String memo;
    private ShopEntity shop;
    private AddressEntity address;
    private Integer userId;
    private Collection<OrderAnItemEntity> orderAnItems;

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

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "is_deliver")
    public Boolean getAreDeliver() {
        return areDeliver;
    }

    public void setAreDeliver(Boolean areDeliver) {
        this.areDeliver = areDeliver;
    }

    @Basic
    @Column(name = "is_receive")
    public Boolean getAreReceive() {
        return areReceive;
    }

    public void setAreReceive(Boolean areReceive) {
        this.areReceive = areReceive;
    }

    @Basic
    @Column(name = "is_evaluate")
    public Boolean getAreEvaluate() {
        return areEvaluate;
    }

    public void setAreEvaluate(Boolean areEvaluate) {
        this.areEvaluate = areEvaluate;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 64)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderAShopEntity that = (OrderAShopEntity) o;

        if (id != that.id) return false;
        if (enabled != that.enabled) return false;
        if (areDeliver != that.areDeliver) return false;
        if (areReceive != that.areReceive) return false;
        if (areEvaluate != that.areEvaluate) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (areDeliver ? 1 : 0);
        result = 31 * result + (areReceive ? 1 : 0);
        result = 31 * result + (areEvaluate ? 1 : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false)
    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "order_a_shop_id", referencedColumnName = "id")
    public Collection<OrderAnItemEntity> getOrderAnItems() {
        return orderAnItems;
    }

    public void setOrderAnItems(Collection<OrderAnItemEntity> orderAnItems) {
        this.orderAnItems = orderAnItems;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
