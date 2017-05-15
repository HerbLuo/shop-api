package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class RequestTooManyException extends HttpWarm {
    public RequestTooManyException() {
        super();
    }
    public RequestTooManyException(String message) {
        super(message);
    }
}
