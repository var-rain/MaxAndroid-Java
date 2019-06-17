package net.lingin.max.android.utils;

import net.lingin.max.android.logger.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by: var_rain.
 * Created date: 2018/10/17.
 * Description: MD5工具类
 */
public class MD5Utils {

    /**
     * 计算字符串的MD5
     *
     * @param text      字符串
     * @param length    MD5值的长度
     * @param upperCase 是否大写输出 true: 大写 false: 小写
     * @return 返回字符串的MD5值
     */
    public static String encrypt(String text, int length, boolean upperCase) {
        return encode(text.getBytes(), length, upperCase);
    }

    /**
     * 计算byte数组的MD5
     *
     * @param data      数据源
     * @param length    MD5值的长度
     * @param upperCase 是否大写输出 true: 大写 false: 小写
     * @return 返回byte数组的MD5值
     */
    public static String encrypt(byte[] data, int length, boolean upperCase) {
        return encode(data, length, upperCase);
    }

    /**
     * 计算byte数组的MD5
     *
     * @param data      数据源
     * @param length    MD5值的长度
     * @param upperCase 是否大写输出 true: 大写 false: 小写
     * @return 返回byte数组的MD5值
     */
    private static String encode(byte[] data, int length, boolean upperCase) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e("Not found MD5 encode algorithm ! encode data [" + Arrays.toString(data) + "]");
        }
        if (digest == null) {
            Log.e("MessageDigest object is null ! encode data [" + Arrays.toString(data) + "]");
            return "";
        }
        String md = new BigInteger(1, digest.digest(data)).toString(length);
        return upperCase ? md.toUpperCase() : md.toLowerCase();
    }
}
