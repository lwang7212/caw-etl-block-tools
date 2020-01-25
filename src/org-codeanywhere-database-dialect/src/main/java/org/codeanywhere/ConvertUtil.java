package org.codeanywhere;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 对象转换工具
 *
 * @author wanglei
 */
public class ConvertUtil {

    /**
     * 转换成字符
     *
     * @param p_value
     * @return
     */
    public static String toString(Object p_value) {
        return p_value == null ? "" : p_value.toString();
    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static String toString(Map<String, Object> mp, String key) {
        if (mp == null) {
            return null;
        }
        if (mp.containsKey(key)) {
            return toString(mp.get(key));
        }
        else {
            return null;
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static String toString(Map<String, Object> mp, String key, String defValue) {
        if (mp == null) {
            return defValue;
        }
        if (mp.containsKey(key)) {
            return toString(mp.get(key));
        }
        else {
            return defValue;
        }

    }

    /**
     * @param p_value
     * @param fmt
     * @return
     */
    public static String DateToString(Date p_value, String fmt) {
        if (p_value == null) {
            return "";
        }
        return new SimpleDateFormat(fmt).format(p_value);

    }

    /**
     * @param p_value
     * @return
     */
    public static String DateToString(Date p_value) {
        return DateToString(p_value, "yyyy-MM-dd HH:mm:ss");

    }

    /**
     * 废除，使用objToString代替
     * 转换字符串，如果为空，采用默认值代替
     *
     * @param p_value
     * @param def
     * @return
     */
    @Deprecated
    public static String toString(Object p_value, String def) {
        return objToString(p_value, def);
    }

    /**
     * Obj转换为字符串，如果为空，用默认值
     *
     * @param p_value
     * @param def
     * @return
     */
    public static String objToString(Object p_value, String def) {
        String rtn = (p_value == null ? def : p_value.toString().trim());
        if (StringUtils.isEmpty(rtn)) {
            return def;
        }
        else {
            return rtn;
        }
    }

    /**
     * 转换为boolean类型
     *
     * @param str
     * @return
     */
    public static boolean toBoolean(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("1") || str.equalsIgnoreCase("是")
                || str.equalsIgnoreCase("on");
    }

    /**
     * 转换为boolean类型
     *
     * @param obj
     * @return
     */
    public static boolean objToBoolean(Object obj) {
        if (obj == null) {
            return false;
        }
        return toBoolean(obj.toString());
    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static boolean objToBoolean(Map<String, Object> mp, String key) {

        if (mp == null) {
            return false;
        }
        if (mp.containsKey(key)) {
            return objToBoolean(mp.get(key));
        }
        else {
            return false;
        }

    }

    /**
     * 转换为日期格式
     *
     * @param p_value
     * @return
     */
    public static Date objToDate(Object p_value) {
        if (p_value == null) {
            return null;
        }
        if (p_value instanceof Date) {
            return (Date) p_value;
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(p_value.toString());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static Date toDate(Map<String, Object> mp, String key) {
        if (mp == null) {
            return null;
        }
        if (mp.containsKey(key)) {
            return objToDate(mp.get(key));
        }
        else {
            return null;
        }


    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static String toDateString(Map<String, Object> mp, String key) {
        if (mp == null) {
            return null;
        }
        if (mp.containsKey(key)) {
            return DateToString(objToDate(mp.get(key)), "yyyy-MM-dd");
        }
        else {
            return null;
        }


    }

    /**
     * 按"yyyy-MM-dd"转换为日期格式
     *
     * @param strDate
     * @return
     */
    public static Object toDate(String strDate) {

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param strDate
     * @param fmt
     * @return
     */
    public static Object toDate(String strDate, String fmt) {

        try {
            return new SimpleDateFormat(fmt).parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 按"yyyy-MM-dd"转换为日期格式
     *
     * @param strDate
     * @return
     */
    public static String toDateTime(String strDate, String fmt) {

        try {
            Date dt = new SimpleDateFormat(fmt).parse(strDate);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static Integer toInt(Map<String, Object> mp, String key) {

        if (mp == null) {
            return 0;
        }
        if (mp.containsKey(key)) {
            return toInt(mp.get(key));
        }
        else {
            return 0;
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static Float toFloat(Map<String, Object> mp, String key) {

        if (mp == null) {
            return 0f;
        }
        if (mp.containsKey(key)) {
            return toFloat(mp.get(key));
        }
        else {
            return 0f;
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param obj
     * @return
     */
    public static Float toFloat(Object obj) {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            return 0f;
        }
        else {
            return Float.parseFloat(obj.toString());
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static Double toDouble(Map<String, Object> mp, String key) {

        if (mp == null) {
            return 0d;
        }
        if (mp.containsKey(key)) {
            return toDouble(mp.get(key));
        }
        else {
            return 0d;
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param obj
     * @return
     */
    public static Double toDouble(Object obj) {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            return 0d;
        }
        else {
            return Double.parseDouble(obj.toString());
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param obj
     * @return
     */
    public static Integer toInt(Object obj) {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            return 0;
        }
        else {
            return Integer.parseInt(obj.toString());
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param mp
     * @param key
     * @return
     */
    public static Long toLong(Map<String, Object> mp, String key) {

        if (mp == null) {
            return 0L;
        }
        if (mp.containsKey(key)) {
            return toLong(mp.get(key));
        }
        else {
            return 0L;
        }

    }

    /**
     * 将Map中指定key对象转换指定对象
     *
     * @param obj
     * @return
     */
    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        else {
            return Long.parseLong(obj.toString());
        }

    }

    /**
     * Clob转换String
     *
     * @param clob
     * @return
     */
    public static String clob2Text(Clob clob) {
        String content = "";
        try {
            Reader is = clob.getCharacterStream();
            BufferedReader buff = new BufferedReader(is);// 得到流
            String line = buff.readLine();
            StringBuffer sb = new StringBuffer();
            while (line != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
                sb.append(line);
                line = buff.readLine();
            }
            content = sb.toString();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return content;
    }

/*    *//**
     * Map 转化为KV对数组
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     *//*
    public static <K, V> KeyValuePair<K, V>[] mapToArray(Map<K, V> map) {
        KeyValuePair<K, V>[] lst = new KeyValuePair[map.size()];
        int i = 0;
        for (Map.Entry<K, V> kv : map.entrySet()) {
            lst[i++] = new KeyValuePair().setKey(kv.getKey()).setValue(kv.getValue());
        }
        return lst;
    }*/




}
