package net.lingin.max.android.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import net.lingin.max.android.R;
import net.lingin.max.android.databinding.ViewLoadingBinding;

/**
 * Created by: var_rain.
 * Created date: 2020/1/15.
 * Description: 加载框
 */
@SuppressWarnings("unused")
public class Loading {

    private static AlertDialog dialog;

    /**
     * 显示加载动画
     */
    public static void show() {
        if (Loading.dialog == null) {
            ViewLoadingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(net.lingin.max.android.Max.ins().activity()), R.layout.view_loading, null, false);
            AlertDialog.Builder build = new AlertDialog.Builder(net.lingin.max.android.Max.ins().activity(), R.style.AppTheme_Loading);
            build.setView(binding.getRoot());
            build.setCancelable(false);
            Loading.dialog = build.create();
            Loading.setDialogStyle();
        }
        Loading.dialog.show();
    }

    /**
     * 设置加载框显示样式
     */
    @SuppressLint("ObsoleteSdkInt")
    private static void setDialogStyle() {
        Window window = Loading.dialog.getWindow();
        if (window != null) {
            // 不弹出虚拟按键
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            View view = window.getDecorView();
            // 隐藏虚拟导航按键
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            view.setOnSystemUiVisibilityChangeListener(visibility -> {
                int options = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    options |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                } else {
                    options |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                // 系统样式改变也隐藏虚拟导航按键
                view.setSystemUiVisibility(options);
            });
        }
    }

    /**
     * 隐藏加载动画
     */
    public static void hide() {
        if (Loading.dialog != null) {
            Loading.dialog.hide();
        }
    }
}
