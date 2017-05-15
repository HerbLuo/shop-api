package cn.cloudself.util.rest;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class RestString {

    private String name;
    private String value;

    public String getType() {
        return "string";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RestString() {
    }

    public RestString(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public RestString(String value) {
        this.value = value;
    }
}
