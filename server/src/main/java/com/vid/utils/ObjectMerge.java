package com.vid.utils;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by song on 17-2-12.
 * <p>
 * 对象合并
 * 将两个对象的属性进行合并，以新对象属性为主
 */
public class ObjectMerge {

    /**
     * 合并对象属性
     *
     * @param object 基对象
     * @param json   json格式的对象，含有基对象的部分属性
     */
    public static void merge(Object object, String json) throws JSONException, IllegalAccessException {
        Field fields[] = object.getClass().getDeclaredFields();
        String fieldName[] = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fieldName[i] = fields[i].getName();
            fields[i].setAccessible(true);
        }

        JSONObject jsonObject = new JSONObject(json);

        for (int i = 0; i < fieldName.length; i++) {
            if (jsonObject.has(fieldName[i])) {
                fields[i].set(object, jsonObject.get(fieldName[i]));
            }
        }
    }
}
