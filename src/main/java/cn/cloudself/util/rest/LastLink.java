package cn.cloudself.util.rest;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class LastLink<T> extends Link<T, Link> {

    @Override
    public void setNextLink(Link nextLink) throws Error {
        throw new Error("终节点不允许引用其它Link");
    }

    public LastLink(T entry, Link nextLink) throws Error {
        throw new Error("终节点不允许引用其它Link");
    }

    public LastLink(String entryName, T entry, Link nextLink) throws Error {
        throw new Error("终节点不允许引用其它Link");
    }

    public LastLink() {
        super();
    }

    public LastLink(T entry) {
        super(entry);
    }

    public LastLink(String entryName, T entry) {
        super(entryName, entry);
    }
}
