package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class RequestUnauthorizedException extends HttpWarm {
    public RequestUnauthorizedException() {
        super();
    }
    public RequestUnauthorizedException(String message) {
        super(message);
    }
}
