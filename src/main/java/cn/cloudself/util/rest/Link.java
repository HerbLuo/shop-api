package cn.cloudself.util.rest;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class Link<T, K extends Link> {

    private String entryName;
    private T entry;
    private K nextLink;

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }

    public K getNextLink() {
        return nextLink;
    }

    public void setNextLink(K nextLink) {
        this.nextLink = nextLink;
    }

    public Link() {
    }

    public Link(T entry) {
        this.entry = entry;
    }

    public Link(String entryName, T entry) {
        this.entryName = entryName;
        this.entry = entry;
    }

    public Link(T entry, K nextLink) {
        this.entry = entry;
        this.nextLink = nextLink;
    }

    public Link(String entryName, T entry, K nextLink) {
        this.entryName = entryName;
        this.entry = entry;
        this.nextLink = nextLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link<?, ?> link = (Link<?, ?>) o;

        if (entryName != null ? !entryName.equals(link.entryName) : link.entryName != null) return false;
        if (entry != null ? !entry.equals(link.entry) : link.entry != null) return false;
        return nextLink != null ? nextLink.equals(link.nextLink) : link.nextLink == null;
    }

    @Override
    public int hashCode() {
        int result = entryName != null ? entryName.hashCode() : 0;
        result = 31 * result + (entry != null ? entry.hashCode() : 0);
        result = 31 * result + (nextLink != null ? nextLink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "entryName='" + entryName + '\'' +
                ", entry=" + entry +
                ", nextLink=" + nextLink +
                '}';
    }

}
