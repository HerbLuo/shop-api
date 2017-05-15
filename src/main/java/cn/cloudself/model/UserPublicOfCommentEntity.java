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
public class UserPublicOfCommentEntity {

    private int id;
    private String nickname;
    private String avatarSrc;
    private int credit;

    @Id
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", length = 16, updatable = false, insertable = false)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "avatar_src", length = 100, updatable = false, insertable = false)
    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String imgSrc) {
        this.avatarSrc = imgSrc;
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

        UserPublicOfCommentEntity that = (UserPublicOfCommentEntity) o;

        if (id != that.id) return false;
        if (credit != that.credit) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        return avatarSrc != null ? avatarSrc.equals(that.avatarSrc) : that.avatarSrc == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (avatarSrc != null ? avatarSrc.hashCode() : 0);
        result = 31 * result + credit;
        return result;
    }

    @Override
    public String toString() {
        return "UserPublicOfCommentEntity{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatarSrc='" + avatarSrc + '\'' +
                ", credit=" + credit +
                '}';
    }

    public UserPublicOfCommentEntity() {
    }

    private UserPublicOfCommentEntity(int id, String nickname, String avatarSrc, int credit) {
        this.id = id;
        this.nickname = nickname;
        this.avatarSrc = avatarSrc;
        this.credit = credit;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public UserPublicOfCommentEntity clone() {
        return new UserPublicOfCommentEntity(id, nickname, avatarSrc, credit);
    }

}
