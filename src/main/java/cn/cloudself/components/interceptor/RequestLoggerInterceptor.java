package cn.cloudself.components.interceptor;

import cn.cloudself.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ghosted on 2016/5/31.
 * 日志记录（基于url拦截器）(对每一次用户请求进行记录)
 * 记录的信息有：
 * ip，
 * uri（重写后），
 * 访问时间（得到请求到产生最终页面）
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class RequestLoggerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Logger logger;

    /*
     * 进入controller前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("startTime", new Date().getTime());
        logger.debug("The remote IP is: " + request.getRemoteAddr());
        logger.debug("The request URI is: " + request.getRequestURI());

        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap.containsKey("password")) { //参数包含密码的情况

            Map<String, String[]> newMap = new HashMap<>(request.getParameterMap());
            newMap.remove("password");
            logger.debug("The request params are: " + StringUtils.toString(newMap));
            if (checkPassword(paramMap.get("password"))) {
                logger.debug("The safety checking of password-check is pass.");
            } else {
                logger.debug("The password is " + Arrays.toString(paramMap.get("password")));
            }

        } else {
            logger.debug("The request params are: " + StringUtils.toString(paramMap));
        }

        return true;
    }

    /*
     * 解析返回结果后
     * 对于forward返回结果
     * 此方法会输出两次
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        long timeMillis = new Date().getTime() - (long)request.getAttribute("startTime");

        logger.debug("This request case " + timeMillis + " ms\n");

    }

    /**
     * 检查32位的md5密码
     * 检测通过，返回true，否则false
     */
    private boolean checkPassword(String[] passwords) {
        //noinspection SimplifiableIfStatement
        if (passwords == null || passwords.length != 1) {
            return false;
        }
        return passwords[0].matches("^[0-9a-zA-Z]{64}$");
    }

}
