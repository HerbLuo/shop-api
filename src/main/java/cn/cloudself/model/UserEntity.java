package cn.cloudself.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@Entity
@DynamicInsert
@Table(name = "user", schema = "shop")
public class UserEntity {
    private int id;
    private String nickname;
    private Byte vip;
    private Integer messageNum;
    private Integer carNum;
    private String imgAvatar;
    private int credit;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 16)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "vip", nullable = true)
    public Byte getVip() {
        return vip;
    }

    public void setVip(Byte vip) {
        this.vip = vip;
    }

    @Basic
    @Column(name = "message_num", nullable = true)
    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }

    @Basic
    @Column(name = "car_num", nullable = true)
    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    @Basic
    @Column(name = "avatar_src")
    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgSrc) {
        this.imgAvatar = imgSrc;
    }

    @Basic
    @Column(name = "credit")
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (credit != that.credit) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (vip != null ? !vip.equals(that.vip) : that.vip != null) return false;
        if (messageNum != null ? !messageNum.equals(that.messageNum) : that.messageNum != null) return false;
        if (carNum != null ? !carNum.equals(that.carNum) : that.carNum != null) return false;
        return imgAvatar != null ? imgAvatar.equals(that.imgAvatar) : that.imgAvatar == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (vip != null ? vip.hashCode() : 0);
        result = 31 * result + (messageNum != null ? messageNum.hashCode() : 0);
        result = 31 * result + (carNum != null ? carNum.hashCode() : 0);
        result = 31 * result + (imgAvatar != null ? imgAvatar.hashCode() : 0);
        result = 31 * result + credit;
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", vip=" + vip +
                ", messageNum=" + messageNum +
                ", carNum=" + carNum +
                ", imgAvatar='" + imgAvatar + '\'' +
                ", credit=" + credit +
                '}';
    }
}
