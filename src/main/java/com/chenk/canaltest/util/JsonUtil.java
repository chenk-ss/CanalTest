package com.chenk.canaltest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: chenke
 * @since: 2021/7/20
 */
public class JsonUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 以下几段代码用于转化JSON数据中的key为驼峰格式
     *
     * @param object
     * @return
     */
    private static JSONObject covertObject(JSONObject object) {
        if (object == null) {
            return null;
        }
        JSONObject newObject = new JSONObject();
        Set<String> set = object.keySet();
        for (String key : set) {
            Object value = object.get(key);
            if (value instanceof JSONArray) {
                //数组
                value = covertArray(object.getJSONArray(key));
            } else if (value instanceof JSONObject) {
                //对象
                value = covertObject(object.getJSONObject(key));
            }
            //这个方法自己写的改成驼峰，也可以改成大写小写
            key = lineToHump(key);
            newObject.put(key, value);
        }
        return newObject;
    }

    private static JSONArray covertArray(JSONArray array) {
        if (array == null) {
            return null;
        }
        JSONArray newArray = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                //数组
                value = covertArray(array.getJSONArray(i));
            } else if (value instanceof JSONObject) {
                //对象
                value = covertObject(array.getJSONObject(i));
            }
            newArray.add(value);
        }
        return newArray;
    }

    public static String convertJSONKeyReturnString(String jsonStr) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONObject jsonResult = covertObject(jsonObject);
        return jsonResult.toJSONString();
    }

    /**
     * 下划线转驼峰,正常输出
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
