package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class ServerException extends HttpError {
    public ServerException() {
        super();
    }
    public ServerException(String message) {
        super(message);
    }
}
