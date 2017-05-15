package cn.cloudself.model;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class PayEntity {

    private int id;
    private Double price;
    private String thirdPayUrl;
    private String callbackUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getThirdPayUrl() {
        return thirdPayUrl;
    }

    public void setThirdPayUrl(String thirdPayUrl) {
        this.thirdPayUrl = thirdPayUrl;
    }


}
