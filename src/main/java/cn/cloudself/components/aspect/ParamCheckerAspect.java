package cn.cloudself.components.aspect;

import cn.cloudself.components.annotation.ParamChecker;
import cn.cloudself.exception.http.RequestBadException;
import cn.cloudself.exception.http.ServerException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * <h1>检查传入参数是否正确</h1>
 * <p>
 * 对应的annotation为{@link ParamChecker}<br/>
 * <p>
 * 检查的方式有（null checking， empty checking(including null check)， check with regex(have no test)）<br/>
 * 该实现允许同时存在多个检查条件<br/>
 * <p>
 * 如未符合，直接抛出 cn.cloudself.exception.http.* 异常
 *
 * @author HerbLuo
 * @version 1.0.4.d
 *          <p>
 *          <p>
 *          change logs:
 *          <p>
 *          2016 xx xx HerbLuo 首次创建
 *          2016 12 19 HerbLuo 代码重构，优化了代码结构，但影响部分性能
 *          2017 03 14 HerbLuo 添加lessThan检查 完成 未测试
 *          <p>
 *          TODO 正则式检查有待重构，应将检查序号单独抽离，应加入预编译，全局缓存
 */
@Aspect
@Component
public class ParamCheckerAspect {

    private final Logger log;

    @Autowired
    public ParamCheckerAspect(Logger log) {
        this.log = log;
    }

    @Pointcut("@annotation(cn.cloudself.components.annotation.ParamChecker)")
    public void paramCheck() {
    }

    @Before("paramCheck()")
    public void paramChecker(JoinPoint jp) throws Exception {
        log.debug("Success enter ParamCheck Aspect");
        log.debug("The method name is {}", jp.getSignature());

        /*
         * 获取注解内容
         */
        MethodSignature ms = (MethodSignature) jp.getSignature();
        Method method = ms.getMethod();
        ParamChecker paramChecker = method.getAnnotation(ParamChecker.class);
        int[] values = paramChecker.value();
        int[] notNull = paramChecker.notNull();
        int[] notEmpty = paramChecker.notEmpty();
        int[] greaterThan0 = paramChecker.greaterThan0();
        int[] greaterOrEqual0 = paramChecker.greaterOrEqual0();
        int[] lessThanIndex = paramChecker.lessThanIndex();
        int[] lessThan = paramChecker.lessThan();
        //noinspection deprecation
        String[] regexs = paramChecker.regexs();

        /*
         * 逻辑业务
         */
        Object[] params = jp.getArgs(); //需检查的参数

        /*
         * 正则检查太麻烦，很难将其抽象出来
         */
        if (!"".equals(regexs[0])) { //指定了正则表达式(check with regex)
            checkByRegex(regexs, params, values);
        }

        /*
         * 对于常规的检查，将其抽象成模板
         */
        paramsCheckTemplate( // 非空empty
                notEmpty[0] != -1,
                o -> !ObjectUtils.isEmpty(o),
                params, notEmpty
        );

        paramsCheckTemplate( // 大于0
                greaterThan0[0] != -1,
                o -> isNum(o) && ((Number) o).doubleValue() > 0,
                params, greaterThan0
        );

        paramsCheckTemplate( // 大于等于0
                greaterOrEqual0[0] != -1,
                o -> isNum(o) && ((Number) o).doubleValue() >= 0,
                params, greaterOrEqual0
        );

        paramsCheckTemplate( // 小于 x
                lessThanIndex[0] != -1,
                (o, i) -> isNum(o) && ((Number) o).doubleValue() < lessThan[i],
                params, lessThanIndex
        );

        paramsCheckTemplate( // 非空 null
                "".equals(regexs[0]) && notEmpty[0] == -1 && greaterThan0[0] == -1 && greaterOrEqual0[0] == -1 /* 默认情况 */
                        || notNull[0] != -1, /* 指定了notNull检查 */
                Objects::nonNull,
                params, notNull[0] == -1 ? values : notNull
        );

        log.debug("Params Checking pass, now proceed running");
    }

    /**
     * 参数检查模板
     *
     * @param condition 是否检查， false跳过该方法
     * @param check     检查器（接口），需实现 boolean test(Object) 方法，返回为true表示检查通过
     * @param params    需检查的对象（数组）
     * @param indexs    需检查的数组对象的下标
     * @throws Exception 检查失败直接以Exception形式抛出
     */
    private void paramsCheckTemplate(boolean condition, Predicate<Object> check,
                                     Object[] params, int[] indexs) throws Exception {

        paramsCheckTemplate(condition, (o, i) -> check.test(o), params, indexs);
    }

    /**
     * 参数检查模板
     *
     * @param condition      是否检查， false跳过该方法
     * @param withIndexCheck 检查器（接口），需实现 boolean test(Object, index) 方法，返回为true表示检查通过
     * @param params         需检查的对象（数组）
     * @param indexs         需检查的数组对象的下标
     * @throws Exception 检查失败直接以Exception形式抛出
     */
    private void paramsCheckTemplate(boolean condition, BiPredicate<Object, Integer> withIndexCheck,
                                     Object[] params, int[] indexs) throws Exception {
        if (!condition) {
            return;
        }

        log.debug("The index of params need chechking are {}", Arrays.toString(indexs));
        Assert.notNull(withIndexCheck);
        if (params.length - 1 < indexs[indexs.length - 1]) {
            throw new ServerException("错误的注解参数， 下标越界");
        }

        for (int i = 0; i < indexs.length; i++) {
            if (!withIndexCheck.test(params[indexs[i]], i)) {
                log.warn("The param is not allowed: {{}: {}}", i, params[indexs[i]]);
                throw new RequestBadException("错误的参数： " + params[indexs[i]]);
            }
        }
    }

    /**
     * 正则方式检查
     * <p>
     * TODO 待改进： 未预编译
     *
     * @param regexs 正则式，String
     * @param params 需要检查的参数
     * @param indexs 需要检查的参数下标
     * @throws Exception .
     */
    private void checkByRegex(String[] regexs, Object[] params, int[] indexs) throws Exception {

        log.debug("The index of params need chechking are {}", Arrays.toString(indexs));
        log.debug("The regex of each params is {}", Arrays.toString(regexs));

        /*
         * 正则表达式允许统一指定, 也可以逐一指定
         */
        boolean isForEach = regexs.length != 1; //是否逐一指定正则表达式
        if (isForEach && regexs.length != indexs.length /* 长度与需检查的参数列表不同 */) {
            log.error("Wrong annotation, the length of params not allowed");
            throw new ServerException("错误的注解参数，正则表达式数组的长度需和检查参数数组长度相同");
        }

        for (int index : indexs) {
            if (!isString(params[index])) {
                log.error("Wrong annotation, the index({}) of params not instanceof String", index);
                throw new ServerException("错误的注解ParamChecker, 非String不可使用正则检验");
            }
            String param = (String) params[index]; //需检验的参数的值
            String regex = isForEach ? regexs[index] : regexs[0]; //对应的正则表达式
            if (!param.matches(regex)) {
                log.warn("The param is not allowed: {}", params[index]);
                throw new RequestBadException("错误的参数： " + params[index]);
            }
        }
    }


    private boolean isNum(Object o) {
        return o instanceof Number;
    }

    private boolean isString(Object o) {
        return o instanceof String;
    }

}
