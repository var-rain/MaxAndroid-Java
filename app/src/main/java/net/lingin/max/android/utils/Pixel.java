package net.lingin.max.android.utils;

import android.content.res.Resources;
import android.view.View;

/**
 * Created by: var_rain.
 * Created date: 2018/12/22.
 * Description: 像素单位转换工具类
 */
@SuppressWarnings("unused")
public class Pixel {

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

    /**
     * 测量控件高度
     *
     * @param view 控件
     * @return 返回高度
     */
    public static float measureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        view.getMeasuredWidth();
        return view.getMeasuredHeight();
    }

    /**
     * 测量控件宽度
     *
     * @param view 控件
     * @return 返回宽度
     */
    public static float measureWidth(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        view.getMeasuredWidth();
        return view.getMeasuredWidth();
    }

    /**
     * 弧度转角度
     *
     * @param radius 弧度
     * @return 返回转换的角度
     */
    public static float toAngle(float radius) {
        return (float) (radius * (180.0f / Math.PI));
    }

    /**
     * 角度转弧度
     *
     * @param angle 角度
     * @return 转换后的弧度
     */
    public static float toRadius(float angle) {
        return (float) (angle * (Math.PI / 180.0f));
    }
}
