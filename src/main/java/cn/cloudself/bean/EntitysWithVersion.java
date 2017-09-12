package cn.cloudself.bean;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *          <p>
 *          change logs:
 *          2017/5/5 HerbLuo 首次创建
 */
@SuppressWarnings("unused")
public class EntitysWithVersion<T> {

    private String version;
    private Iterable<T> entity;

    public EntitysWithVersion() {
    }

    public EntitysWithVersion(String version, Iterable<T> entity) {
        this.version = version;
        this.entity = entity;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Iterable<T> getEntity() {
        return entity;
    }

    public void setEntity(Iterable<T> entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "EntitysWithVersion{" +
                "version='" + version + '\'' +
                ", entity=" + entity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntitysWithVersion<?> that = (EntitysWithVersion<?>) o;

        if (version != null ? !version.equals(that.version) : that.version != null)
            return false;
        return entity != null ? entity.equals(that.entity) : that.entity == null;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        return result;
    }
}
