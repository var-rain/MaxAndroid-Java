package net.lingin.max.android.utils;

import net.lingin.max.android.MainApplication;
import net.lingin.max.android.widget.NoticeWidget;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 消息提示统一接口
 */
public class NoticeUtils {

    /**
     * 显示消息提示
     *
     * @param text 文本
     */
    public static void show(String text) {
        NoticeWidget.instance().showNotice(text);
    }

    /**
     * 显示消息提示
     *
     * @param res 字符串资源ID
     */
    public static void show(int res) {
        NoticeWidget.instance().showNotice(MainApplication.instance().getString(res));
    }
}
