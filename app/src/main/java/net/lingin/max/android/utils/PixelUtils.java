package net.lingin.max.android.utils;

import android.content.res.Resources;

/**
 * Created by: var_rain.
 * Created date: 2018/12/22.
 * Description: 像素单位转换工具类
 */
public class PixelUtils {

    /**
     * dp转px
     *
     * @param dp dp单位值
     * @return px单位值
     */
    public static int dp2px(float dp) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param px px单位值
     * @return dp单位值
     */
    public static int px2dp(float px) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param px px单位值
     * @return sp单位值
     */
    public static int px2sp(float px) {
        float scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param sp sp单位值
     * @return px单位值
     */
    public static int sp2px(float sp) {
        float scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }
}
