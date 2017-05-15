package cn.cloudself.components.voter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * spring security voter
 * 检查url, url 中所指示用户如果和 token 所指示用户不同，拒绝该访问
 *
 * @author HerbLuo
 * @version 1.0.0.d
 */
public class UserVoter implements AccessDecisionVoter<Object> {

    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    @Autowired
    private Logger logger;


    /**
     * 表明该投票器是否启用
     * Indicates whether this {@code AccessDecisionVoter} is able to vote on the passed {@code ConfigAttribute}.
     * <p>
     * This allows the {@code AbstractSecurityInterceptor} to check every configuration attribute can be consumed by
     * the configured {@code AccessDecisionManager} and/or {@code RunAsManager} and/or {@code AfterInvocationManager}.
     *
     * @param attribute a configuration attribute that has been configured against the
     *                  {@code AbstractSecurityInterceptor}
     * @return true if this {@code AccessDecisionVoter} can support the passed configuration attribute
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * Indicates whether the {@code AccessDecisionVoter} implementation is able to provide access control
     * votes for the indicated secured object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        if (FilterInvocation.class != clazz) {
            logger.error("无法到达的代码");
            return false;
        }
        return true;
    }

    /**
     * 指示是否授予权限
     * Indicates whether or not access is granted.
     * <p>
     * 返回值必须是ACCESS_GRANTED 或 ACCESS_DENIED 或 ACCESS_ABSTAIN（弃权）
     * The decision must be affirmative ({@code ACCESS_GRANTED}), negative ({@code ACCESS_DENIED})
     * or the {@code AccessDecisionVoter} can abstain ({@code ACCESS_ABSTAIN}) from voting.
     * 任何情况下都不许返回其它值，除非 AccessDecisionManager 是自定义的
     * Under no circumstances should implementing classes return any other value. If a weighting of results is desired,
     * this should be handled in a custom {@link AccessDecisionManager} instead.
     * <p>
     * Unless an {@code AccessDecisionVoter} is specifically intended to vote on an access control
     * decision due to a passed method invocation or configuration attribute parameter, it must return
     * {@code ACCESS_ABSTAIN}. This prevents the coordinating {@code AccessDecisionManager} from counting
     * votes from those {@code AccessDecisionVoter}s without a legitimate interest in the access control
     * decision.
     * <p>
     * Whilst the secured object (such as a {@code MethodInvocation}) is passed as a parameter to maximise flexibility
     * in making access control decisions, implementing classes should not modify it or cause the represented invocation
     * to take place (for example, by calling {@code MethodInvocation.proceed()}).
     *
     * @param authentication the caller making the invocation
     * @param object         the secured object being invoked
     * @param attributes     the configuration attributes associated with the secured object
     * @return either {@link #ACCESS_GRANTED}, {@link #ACCESS_ABSTAIN} or {@link #ACCESS_DENIED}
     * <p>
     * 匹配url中的user 和authentication中user 是否一致
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        /*
         * 强制转换
         * 获取当前请求的url
         */
        Assert.isAssignable(FilterInvocation.class, object.getClass());
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();

        Object obj = authentication.getPrincipal();
        User user = obj instanceof User ? (User) obj : null;
        String username = user == null ? "undefined" : user.getUsername();

        logger.debug(url);
        logger.debug(user);

        /*
         * 用户认证，
         * 当url存在username，则必须与authentication中user一致
         */
        if (voteTemplate(url, "/user/", username) == ACCESS_DENIED) {
            return ACCESS_DENIED;
        }

        /*
         * 商户认证
         * 当url存在seller，则必须与authentication一致
         */
        if (voteTemplate(url, "/seller/", username) == ACCESS_DENIED) {
            return ACCESS_DENIED;
        }

        /*
         * 可选的用户认证，
         * 允许optionalUser值为 "undefined"
         */
        System.out.println(user);
        if (voteTemplate(url, "/optionalUser/", username) == ACCESS_DENIED) {
            return ACCESS_DENIED;
        }

        return ACCESS_GRANTED;
    }

    /**
     * 认证模板
     * @param url .
     * @param key restful 规范url中的 键
     * @param username authentication中的username，将与url中key对应的value比较，一致则认证成功
     * @return ACCESS_GRANTED or ACCESS_DENIED
     */
    private int voteTemplate(String url, String key, String username) {
        int indexOfUserStart = url.indexOf(key);
        if (indexOfUserStart >= 0) {

            int indexOfUserEnd = url.indexOf('/', indexOfUserStart + key.length());
            if (indexOfUserEnd < 0) {
                logger.warn("url中存在key但不存在key对应的value，url不符合restful规范, 其中key为" + key);
                return ACCESS_GRANTED;
            }

            String userInUrl = url.substring(indexOfUserStart + key.length(), indexOfUserEnd);
            if (!userInUrl.equals(username)) {
                logger.debug("认证失败");
                return ACCESS_DENIED;
            }

            logger.debug(userInUrl);
            return ACCESS_GRANTED;

        }
        return ACCESS_GRANTED;
    }
}
