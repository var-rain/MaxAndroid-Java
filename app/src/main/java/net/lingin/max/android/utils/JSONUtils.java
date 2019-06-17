package net.lingin.max.android.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by: var_rain.
 * Created date: 2018/10/6.
 * Description: JSON序列化与反序列化工具类
 */
public class JSONUtils {

    private static Gson gson = new Gson();

    /**
     * Object序列化为JSON
     *
     * @param object 对象
     * @return 返回序列化后的JSON字符串
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * JSON反序列化为Object
     *
     * @param json  JSON字符串
     * @param clazz 反序列化类型
     * @param <T>   泛型
     * @return 返回反序列化后的泛型对象, 反序列化异常则返回null
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        T object = null;
        try {
            object = gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return object;
    }
}
