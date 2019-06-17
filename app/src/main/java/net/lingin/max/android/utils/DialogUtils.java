package net.lingin.max.android.utils;

import net.lingin.max.android.ui.listener.OnDialogListener;
import net.lingin.max.android.widget.DialogWidget;

/**
 * Created by: var_rain.
 * Created time: 2019/5/22.
 * Description: 对话框统一接口
 */
public class DialogUtils {

    /**
     * 显示对话框
     *
     * @param text 显示内容
     */
    public static void show(String text) {
        DialogWidget.instance().show(text);
    }

    /**
     * 显示对话框
     *
     * @param listener 回调接口
     */
    public static void show(OnDialogListener listener) {
        DialogWidget.instance().show(listener);
    }

    /**
     * 显示对话框
     *
     * @param id       文字资源ID
     * @param listener 回调接口
     */
    public static void show(int id, OnDialogListener listener) {
        DialogWidget.instance().show(id, listener);
    }

    /**
     * 显示对话框
     *
     * @param text     显示内容
     * @param listener 回调接口
     */
    public static void show(String text, OnDialogListener listener) {
        DialogWidget.instance().show(text, listener);
    }
}
