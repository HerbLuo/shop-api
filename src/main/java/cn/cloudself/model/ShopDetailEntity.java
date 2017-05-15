package cn.cloudself.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "shop_detail", schema = "shop")
@DynamicInsert
public class ShopDetailEntity {
    private int id;
    private int bail;
    private int credit;
    private double descriptionScore;
    private double serviceScore;
    private double logisticsScore;
    private String sellerName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bail", nullable = false)
    public int getBail() {
        return bail;
    }

    public void setBail(int bail) {
        this.bail = bail;
    }

    @Basic
    @Column(name = "credit", nullable = false)
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "description_score", nullable = false)
    public double getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(double descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    @Basic
    @Column(name = "service_score", nullable = false)
    public double getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(double serviceScore) {
        this.serviceScore = serviceScore;
    }

    @Basic
    @Column(name = "logistics_score", nullable = false)
    public double getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(double logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    @Basic
    @Column(name = "seller_name", nullable = false, length = 16)
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopDetailEntity that = (ShopDetailEntity) o;

        if (id != that.id) return false;
        if (bail != that.bail) return false;
        if (credit != that.credit) return false;
        if (Double.compare(that.descriptionScore, descriptionScore) != 0) return false;
        if (Double.compare(that.serviceScore, serviceScore) != 0) return false;
        if (Double.compare(that.logisticsScore, logisticsScore) != 0) return false;
        return sellerName != null ? sellerName.equals(that.sellerName) : that.sellerName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + bail;
        result = 31 * result + credit;
        temp = Double.doubleToLongBits(descriptionScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(serviceScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(logisticsScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sellerName != null ? sellerName.hashCode() : 0);
        return result;
    }
}
