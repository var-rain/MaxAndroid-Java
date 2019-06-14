package net.lingin.max.android.logger;

import net.lingin.max.android.BuildConfig;
import net.lingin.max.android.MainApplication;
import net.lingin.max.android.R;

import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;

/**
 * Created by: var_rain.
 * Created date: 2019/6/10.
 * Description: 日志打印
 */
public class Log {

    // 标签
    private final static String TAG = MainApplication.instance().getString(R.string.app_name);
    // 分割线
    private final static String LINE = "----------------------------------------------------------------------";

    /**
     * 错误
     *
     * @param message 消息
     */
    public static void e(String message) {
        Log.print(ERROR, TAG, message, null);
    }

    /**
     * 错误
     *
     * @param throwable 异常
     * @param message   消息
     */
    public static void e(String message, Throwable throwable) {
        Log.print(ERROR, TAG, message, throwable);
    }

    /**
     * 调试
     *
     * @param any 对象
     */
    public static void d(Object any) {
        Log.print(DEBUG, TAG, any.toString(), null);
    }

    /**
     * 调试
     *
     * @param message 消息
     */
    public static void d(String message) {
        Log.print(DEBUG, TAG, message, null);
    }

    /**
     * 提示
     *
     * @param message 消息
     */
    public static void i(String message) {
        Log.print(INFO, TAG, message, null);
    }

    /**
     * 格式化并打印JSON
     *
     * @param json JSON字符串
     */
    public static void json(String json) {
        Log.print(VERBOSE, TAG, json, null);
    }


    /**
     * 日志
     *
     * @param priority  级别
     * @param tag       标签
     * @param message   消息
     * @param throwable 异常
     */
    public static void log(int priority, String tag, String message, Throwable throwable) {
        Log.print(priority, tag, message, throwable);
    }

    /**
     * 日志
     *
     * @param priority  级别
     * @param tag       标签
     * @param message   消息
     * @param throwable 异常
     */
    private static void print(int priority, String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            android.util.Log.println(priority, tag, LINE);
            if (throwable != null) {
                android.util.Log.println(priority, tag, String.format("%s\n Exception: %s", message, throwable.getLocalizedMessage()));
            } else {
                android.util.Log.println(priority, tag, message);
            }
            android.util.Log.println(priority, tag, LINE);
        }
    }

    /**
     * 普通
     *
     * @param message 消息
     */
    public static void v(String message) {
        Log.print(VERBOSE, TAG, message, null);
    }

    /**
     * 警告
     *
     * @param message 消息
     */
    public static void w(String message) {
        Log.print(WARN, TAG, message, null);
    }

    /**
     * 格式化并打印XML
     *
     * @param xml XML字符串
     */
    public static void xml(String xml) {
        Log.print(VERBOSE, TAG, xml, null);
    }
}
