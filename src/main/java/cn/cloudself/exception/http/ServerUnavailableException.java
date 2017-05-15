package cn.cloudself.exception.http;

/**
 * 服务不可用 （资源耗尽）
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class ServerUnavailableException extends HttpWarm {
    public ServerUnavailableException() {
        super();
    }
    public ServerUnavailableException(String message) {
        super(message);
    }
}
