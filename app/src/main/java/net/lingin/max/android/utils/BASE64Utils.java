package net.lingin.max.android.utils;

import android.util.Base64;

/**
 * Created by: var_rain.
 * Created date: 2018/10/18.
 * Description: BASE64加解密工具类
 */
public class BASE64Utils {

    /**
     * BASE64加密方法
     *
     * @param data 字符串数据
     * @return 返回加密后的BASE64字符串
     */
    public static String encrypt(String data) {
        return TextUtils.clean(Base64.encodeToString(data.getBytes(), Base64.DEFAULT));
    }

    /**
     * BASE64解密方法
     *
     * @param data BASE64加密字符串
     * @return 返回解密后的字符串
     */
    public static String decrypt(String data) {
        return new String(Base64.decode(data, Base64.DEFAULT));
    }
}
