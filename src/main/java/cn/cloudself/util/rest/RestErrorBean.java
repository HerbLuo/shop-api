package cn.cloudself.util.rest;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@SuppressWarnings("unused")
public class RestErrorBean {

    private int status;
    private int code;
    private String message;
    private String devMessage;
    private String infoUrl;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public RestErrorBean() {
    }

    public RestErrorBean(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestErrorBean(int status, int code, String message, String devMessage, String infoUrl) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.devMessage = devMessage;
        this.infoUrl = infoUrl;
    }

}
