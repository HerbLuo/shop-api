package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class RequestNotFindException extends HttpWarm {
    public RequestNotFindException() {
        super();
    }
    public RequestNotFindException(String message) {
        super(message);
    }
}
