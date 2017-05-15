package cn.cloudself.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "item", schema = "shop")
public class ItemEntity {
    private int id;
    private boolean enabled;
    private Timestamp modifyTime;
    private String version;
    private String name;
    private double price;
    private String description;
    private String picLinksJson;
    private ItemSellingInfoEntity itemSellingInfo;
    private Integer detailDivId;
    private ShopEntity shop;

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
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "modify_time", nullable = true)
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "version", nullable = true, length = 6)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "pic_links_json", nullable = true, length = 500)
    public String getPicLinksJson() {
        return picLinksJson;
    }

    public void setPicLinksJson(String picLinksJson) {
        this.picLinksJson = picLinksJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (id != that.id) return false;
        if (enabled != that.enabled) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (picLinksJson != null ? !picLinksJson.equals(that.picLinksJson) : that.picLinksJson != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picLinksJson != null ? picLinksJson.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", enabled=" + enabled +
                ", modifyTime=" + modifyTime +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", picLinksJson='" + picLinksJson + '\'' +
                ", itemSellingInfo=" + itemSellingInfo +
                '}';
    }

    public ItemEntity() {
    }

    public ItemEntity(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "item_id")
    public ItemSellingInfoEntity getItemSellingInfo() {
        return itemSellingInfo;
    }

    public void setItemSellingInfo(ItemSellingInfoEntity itemSellingInfo) {
        this.itemSellingInfo = itemSellingInfo;
    }

    @Basic
    @Column(name = "detail_div_id", nullable = true)
    public Integer getDetailDivId() {
        return detailDivId;
    }

    public void setDetailDivId(Integer detailDivId) {
        this.detailDivId = detailDivId;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }
}
