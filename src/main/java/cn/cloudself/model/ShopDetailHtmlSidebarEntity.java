package cn.cloudself.model;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@Table(name = "shop_detail_html_sidebar", schema = "shop")
public class ShopDetailHtmlSidebarEntity {
    private int id;
    private String xssHtml;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "xss_html", nullable = true, length = -1)
    public String getXssHtml() {
        return xssHtml;
    }

    public void setXssHtml(String xssHtml) {
        this.xssHtml = xssHtml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopDetailHtmlSidebarEntity that = (ShopDetailHtmlSidebarEntity) o;

        if (id != that.id) return false;
        if (xssHtml != null ? !xssHtml.equals(that.xssHtml) : that.xssHtml != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (xssHtml != null ? xssHtml.hashCode() : 0);
        return result;
    }
}
