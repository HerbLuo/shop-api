package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class HttpError extends HttpException{
    public HttpError() {
    }

    public HttpError(int code) {
        super(code);
    }

    public HttpError(String message) {
        super(message);
    }

    public HttpError(String message, int code) {
        super(message, code);
    }

    public HttpError(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpError(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public HttpError(Throwable cause) {
        super(cause);
    }
}
