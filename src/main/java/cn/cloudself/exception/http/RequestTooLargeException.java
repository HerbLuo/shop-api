package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class RequestTooLargeException extends HttpWarm {
    public RequestTooLargeException() {
        super();
    }
    public RequestTooLargeException(String message) {
        super(message);
    }
}
