package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class HttpWarm extends HttpException {

    public HttpWarm() {
    }

    public HttpWarm(int code) {
        super(code);
    }

    public HttpWarm(String message) {
        super(message);
    }

    public HttpWarm(String message, int code) {
        super(message, code);
    }

    public HttpWarm(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpWarm(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public HttpWarm(Throwable cause) {
        super(cause);
    }

}
