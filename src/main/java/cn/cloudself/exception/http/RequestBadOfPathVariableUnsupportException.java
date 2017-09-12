package cn.cloudself.exception.http;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 * <p>
 * change logs:
 * 2017/8/11 HerbLuo 首次创建
 */
public class RequestBadOfPathVariableUnsupportException extends RequestBadException {

    public RequestBadOfPathVariableUnsupportException() {
        super();
    }

    public RequestBadOfPathVariableUnsupportException(String path) {
        super("错误的参数：" + path);
    }

}
