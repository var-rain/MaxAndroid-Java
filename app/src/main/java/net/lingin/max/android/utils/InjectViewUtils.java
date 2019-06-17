package net.lingin.max.android.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import net.lingin.max.android.MainApplication;

/**
 * Created by: var_rain.
 * Created date: 2018/11/7.
 * Description: 视图注入工具类,注入View对象到当前显示的Activity中
 */
public class InjectViewUtils {

    /* 静态实例 */
    @SuppressLint("StaticFieldLeak")
    private static InjectViewUtils instance;

    /**
     * 构造方法
     */
    private InjectViewUtils() {
    }

    /**
     * 获取当前对象的静态实例
     *
     * @return 返回 {@link InjectViewUtils} 对象
     */
    public static InjectViewUtils instance() {
        if (InjectViewUtils.instance == null) {
            InjectViewUtils.instance = new InjectViewUtils();
        }
        return InjectViewUtils.instance;
    }

    /**
     * 根据资源创建视图对象
     *
     * @param res 布局资源ID
     * @return 返回通过布局资源创建的 {@link View} 对象
     */
    public View make(@LayoutRes int res) {
        Activity activity = MainApplication.instance().activity();
        return LayoutInflater.from(activity).inflate(res, null);
    }

    /**
     * 需要注入的View对象
     *
     * @param view 视图 {@link View} 对象
     */
    public void inject(View view) {
        ViewGroup group = decorView();
        if (view != null) {
            clean(view);
            group.addView(view);
        }
    }

    /**
     * 从夫布局中删除View对象
     *
     * @param view 视图 {@link View} 对象
     */
    public void clean(View view) {
        ViewGroup group = decorView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            if (group.indexOfChild(view) != -1) {
                group.removeView(view);
            }
        }
    }

    /**
     * 获取Activity的根布局
     *
     * @return 返回 {@link ViewGroup} 对象
     */
    private ViewGroup decorView() {
        return (ViewGroup) MainApplication.instance().activity().getWindow().getDecorView();
    }
}
