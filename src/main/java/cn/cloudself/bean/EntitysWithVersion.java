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
    private Iterable<T> entitys;

    public EntitysWithVersion() {
    }

    public EntitysWithVersion(String version, Iterable<T> entitys) {
        this.version = version;
        this.entitys = entitys;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Iterable<T> getEntitys() {
        return entitys;
    }

    public void setEntitys(Iterable<T> entitys) {
        this.entitys = entitys;
    }

    @Override
    public String toString() {
        return "EntitysWithVersion{" +
                "version='" + version + '\'' +
                ", entitys=" + entitys +
                '}';
    }

    @Override
    @SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntitysWithVersion<?> that = (EntitysWithVersion<?>) o;

        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        return entitys != null ? entitys.equals(that.entitys) : that.entitys == null;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (entitys != null ? entitys.hashCode() : 0);
        return result;
    }
}
