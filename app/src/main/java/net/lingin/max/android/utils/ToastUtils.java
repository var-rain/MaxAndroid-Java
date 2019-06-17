package net.lingin.max.android.utils;

import android.view.Gravity;

import androidx.annotation.StringRes;

import net.lingin.max.android.MainApplication;
import net.lingin.max.android.widget.ToastWidget;

/**
 * Created by: var_rain.
 * Created time: 2019/5/22.
 * Description: 统一Toast接口
 */
public class ToastUtils {

    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    /**
     * 显示Toast
     *
     * @param id 字符串资源ID
     */
    public static void show(@StringRes int id) {
        ToastWidget.INSTANCE.showToast(MainApplication.instance().getString(id), Gravity.CENTER, ToastUtils.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param content 提示信息
     */
    public static void show(String content) {
        ToastWidget.INSTANCE.showToast(content, Gravity.CENTER, ToastUtils.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param content 提示信息
     * @param gravity 显示位置
     */
    public static void show(String content, int gravity) {
        ToastWidget.INSTANCE.showToast(content, gravity, ToastUtils.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param content  提示信息
     * @param gravity  显示位置
     * @param duration 显示时长
     */
    public static void show(String content, int gravity, int duration) {
        ToastWidget.INSTANCE.showToast(content, gravity, duration);
    }
}
