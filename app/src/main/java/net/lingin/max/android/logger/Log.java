package net.lingin.max.android.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import net.lingin.max.android.BuildConfig;
import net.lingin.max.android.MainApplication;
import net.lingin.max.android.R;

/**
 * Created by: var_rain.
 * Created date: 2019/3/1.
 * Description: 日志打印
 */
public class Log {

    static {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(7)
                .tag(MainApplication.instance().getString(R.string.app_name))
                .build();
        Logger.addLogAdapter(new DiskLogAdapter());
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    /**
     * 错误
     *
     * @param message 消息
     * @param args    参数
     */
    public static void e(@NonNull String message, @Nullable Object... args) {
        Logger.e(message, args);
    }

    /**
     * 错误
     *
     * @param throwable 异常
     * @param message   消息
     * @param args      参数
     */
    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        Logger.e(throwable, message, args);
    }

    /**
     * 调试
     *
     * @param object 对象
     */
    public static void d(@Nullable Object object) {
        Logger.d(object);
    }

    /**
     * 调试
     *
     * @param message 消息
     * @param args    参数
     */
    public static void d(@NonNull String message, @Nullable Object... args) {
        Logger.d(message, args);
    }

    /**
     * 提示
     *
     * @param message 消息
     * @param args    参数
     */
    public static void i(@NonNull String message, @Nullable Object... args) {
        Logger.i(message, args);
    }

    /**
     * 格式化并打印JSON
     *
     * @param json JSON字符串
     */
    public static void json(@Nullable String json) {
        Logger.json(json);
    }

    /**
     * 日志
     *
     * @param priority  级别
     * @param tag       标签
     * @param message   消息
     * @param throwable 异常
     */
    public static void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
        Logger.log(priority, tag, message, throwable);
    }

    /**
     * 普通
     *
     * @param message 消息
     * @param args    参数
     */
    public static void v(@NonNull String message, @Nullable Object... args) {
        Logger.v(message, args);
    }

    /**
     * 警告
     *
     * @param message 消息
     * @param args    参数
     */
    public static void w(@NonNull String message, @Nullable Object... args) {
        Logger.w(message, args);
    }

    /**
     * 记录意外错误
     *
     * @param message 消息
     * @param args    参数
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        Logger.wtf(message, args);
    }

    /**
     * 格式化并打印XML
     *
     * @param xml XML字符串
     */
    public static void xml(@Nullable String xml) {
        Logger.xml(xml);
    }
}
