package cn.cloudself.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <h1>字符串工具</h1>
 * <p>目前主要功能为将其他对象转化为字符串</p>
 *
 * @author HerbLuo
 * @version 1.0.4.d
 *          <p>
 *          <p>
 *          change logs:
 *          <p>
 *          2017 04 05 HerbLuo 部分字符串拼接改用StringBuilder 完成 未测试
 */
@SuppressWarnings("unused")
public class StringUtils {

    /**
     * 将错误的堆栈信息转化为字符串
     */
    public static String toString(Exception e) {
        // 此处使用StringBuilder意义不大
        return "\t" + e.toString() + "\r\n"
                + Arrays.stream(e.getStackTrace()).map(se -> "\t\t\t\t\tat " + se + "\r\n").collect(Collectors.toList()).toString();
    }

    /**
     * 将包含数组对象的map转化为字符串(仅限单层, 不允许出现 map类型的数组且map中仍包含数组对象)<br/>
     * <p>
     * 如map里的key和value为普通对象，请直接使用map.toString
     *
     * @param map map中包含数组对象
     */
    public static String toString(Map map) {
        return StringUtils.toString(map, ": ", ", ");
    }


    /**
     * 将包含数组对象的map转化为字符串(仅限单层, 不允许出现 map类型的数组且map中仍包含数组对象)Map&lt;K, Map&lt;KK[], VV[]>><br/>
     * <p>
     * 如map里的key和value为普通对象，请直接使用map.toString
     *
     * @param map           map中包含数组对象
     * @param keyAndValue   转化的字符串中用来分隔key和value的标记（如": "）
     * @param entryAndEntry 转化的字符串中用来每个map实体的标记（如", "）
     */
    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public static String toString(Map map, String keyAndValue, String entryAndEntry) {
        if (entryAndEntry == null) {
            throw new IllegalArgumentException("EntryAndEntry must be non-null");
        }
        if (map.size() == 0)
            return "";

        final StringBuilder[] result = {new StringBuilder("")};
        map.forEach((k, v) ->
                result[0]
                        .append(k.getClass().isArray() ? Arrays.toString((Object[]) k) : k)
                        .append(keyAndValue)
                        .append(v.getClass().isArray() ? Arrays.toString((Object[]) v) : v)
                        .append(entryAndEntry)
        );
        return result[0].substring(0, result[0].length() - entryAndEntry.length());
    }

    /**
     * obj args to string
     *
     * @param caseType a enum type in this object
     * @param objs     object args
     */
    public static String toString(CASETYPE caseType, Object... objs) {
        return toString(", ", caseType, objs);
    }

    /**
     * obj args to string
     *
     * @param splitSymbol 分割符, notnull, optional(默认为 ", "), 用于分割产生的字符串中的obj的符号，如 ", " ": " 等
     * @param caseType    a enum type in this object
     * @param objs        object args
     */
    @SuppressWarnings("WeakerAccess")
    public static String toString(String splitSymbol, CASETYPE caseType, Object... objs) {
        if (splitSymbol == null) {
            throw new IllegalArgumentException("splitSymbol must be non-null");
        }
        if (objs == null) {
            return "null";
        }

        return objArgs2StringTemplate(splitSymbol, (obj) -> {
            switch (caseType) {

                case HASHCODE:
                    return obj.hashCode() + "";

                //noinspection deprecation
                case RESOLVE:
                    if (obj instanceof Object[]) {
                        return Arrays.toString((Object[]) obj);
                    }

                case TOSTRING:
                default:
                    return obj.toString();
            }
        }, objs);
    }

    /**
     * obj args to string
     * <p>
     * 指定obj args的解析方式
     * 包含多种解析方式
     */
    @SuppressWarnings({"WeakerAccess", "DeprecatedIsStillUsed"})
    public enum CASETYPE {

        /**
         * 使用hashcode产生字符串
         */
        HASHCODE,

        /**
         * 使用toString产生字符串
         */
        TOSTRING,

        /**
         * 解析 针对Map，Array等类型，
         * 尽可能向存放的真正数据类型解析
         *
         * @deprecated 方法未完善, 仅实现了Object[]的解析
         */
        RESOLVE

    }

    //私有接口
    private interface ObjectResolver extends Function<Object, String> {
    }

    /**
     * obj args to string
     * <p>
     * 将String转化为Object的模板
     *
     * @param objectResolver 该接口会传入一个Object，要求使用者将其解析为String
     */
    private static String objArgs2StringTemplate(String splitSymbol, ObjectResolver objectResolver, Object... objects) {
        assert objects != null : "object must be not null";
        assert splitSymbol != null : "splitSymbol must be not null";

        StringBuilder result = new StringBuilder("");
        for (Object obj : objects) {
            result.append(obj == null
                    ? "null"
                    : objectResolver.apply(obj));
            result.append(splitSymbol);
        }
        return result.substring(0, result.length() - splitSymbol.length());
    }

}
