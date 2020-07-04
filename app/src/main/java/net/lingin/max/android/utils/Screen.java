package net.lingin.max.android.utils;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by: var_rain.
 * Created date: 2020/7/4.
 * Description: 屏幕分辨率工具类
 */
@SuppressWarnings("unused")
public class Screen {

    /**
     * 获取屏幕大小信息
     *
     * @param real 是否真实数据(包含状态栏高度)
     * @return {@link DisplayMetrics}
     */
    public static DisplayMetrics metrics(boolean real) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (net.lingin.max.android.Max.ins().activity() != null) {
            WindowManager windowManager = net.lingin.max.android.Max.ins().activity().getWindowManager();
            if (windowManager != null) {
                Display display = windowManager.getDefaultDisplay();
                if (display != null) {
                    if (real) {
                        display.getRealMetrics(metrics);
                    } else {
                        display.getMetrics(metrics);
                    }
                }
            }
        }
        return metrics;
    }
}
