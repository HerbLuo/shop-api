package cn.cloudself.util.rest;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class RestId<T> {

    private T id;


    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestId<?> restId = (RestId<?>) o;

        return id != null ? id.equals(restId.id) : restId.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public RestId() {
    }

    public RestId(T id) {
        this.id = id;
    }
}
