package cn.cloudself.components.annotation;

import java.lang.annotation.*;

/**
 * <h1>检查参数 是否为 null, empty等
 * <p>
 * <p>default check is not null checking of params[0]
 * <p>
 * <p>all the checking include not null checking
 * <p>
 * <p>当同时使用多种检查方式时，
 * <p>如需指定not null 检查， 不能使用 value 作为 null checking 的标志，应当使用 not null
 * <p>
 * <p>其实，有了这个，我们就可以轻松加愉快地玩耍了，
 * <p>再也不用写那些烦人地参数检查
 * <p>
 * <p>妈的，你说性能差？
 * <p>你不说，我不说，谁知道呢 O(∩_∩)O
 *
 * @author HerbLuo
 * @version 1.0.1.d
 *
 * change logs:
 * 2016-xx-xx HerbLuo 首次创建
 * 2017-03-14 HerbLuo 添加lessThan检查
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamChecker {

    /**
     * 仅在单一检查方式下使用<br/>
     * Not Null Checking
     *
     * @return int[] 检查第几个参数，从0开始记
     */
    int[] value() default 0;

    /**
     * 非空检查
     */
    int[] notNull() default -1;

    /**
     * Not Equal "" or [] Checking<br/>
     * <p>
     * 参数必须是String 或 Object[] 或 Collection, 允许混搭
     *
     * @return int[] 检查第几个参数，从0开始记
     */
    int[] notEmpty() default -1;

    /**
     * 大于0
     */
    int[] greaterThan0() default -1;

    /**
     * 小于， 例如，第3个参数小于10
     * （小于共 2个参数，
     * 分别是：检查的序号 3
     * 小于的值 10）
     *
     * @return 从0开始计
     * @see #lessThan()
     */
    int[] lessThanIndex() default -1;

    /**
     * 小于， 例如，第3个参数小于10
     * （小于共 2个参数，
     * 分别是：检查的序号 3
     * 小于的值 10）
     *
     * @return 从0开始计
     * @see #lessThan()
     */
    int[] lessThan() default -1;

    /**
     * 大于等于0
     */
    int[] greaterOrEqual0() default -1;

    /**
     * <h1>利用正则表达式检验</h1>
     * 使用时与value[]配合使用，value表示需检验参数的index值，regex表示检验法则
     *
     * @return 正则表达式数组， 如只有单个值，则表示所有参数均用该检验法则
     * 否则，该数组长度需和value[]长度相同
     */
    String[] regexs() default "";

}
