package com.pistachio.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author: Pengsy
 * @date: 2023/08/01 16:45
 * @description: json 工具
 */
public class GsonUtil {

    public static String toJSONString(Object obj) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(obj);
    }

    public static <T> T parseObject(String json, Class<T> classOfT) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(json, classOfT);
    }
}
