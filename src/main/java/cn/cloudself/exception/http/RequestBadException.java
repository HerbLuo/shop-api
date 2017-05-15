package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class RequestBadException extends HttpWarm {
    public RequestBadException() {
        super();
    }
    public RequestBadException(String message) {
        super(message, 100);
    }
}
