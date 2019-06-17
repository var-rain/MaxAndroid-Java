package net.lingin.max.android.ui.base;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import net.lingin.max.android.R;
import net.lingin.max.android.ui.base.config.SystemUIVisibility;
import net.lingin.max.android.ui.listener.OnPermissionRequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by: var_rain.
 * Created date: 2018/10/16.
 * Description: Activity父类
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder unbinder;
    private OnPermissionRequestListener callback;
    private final int REQUEST_PERMISSION_CODE = 999;
    private int visibility = View.SYSTEM_UI_FLAG_VISIBLE;
    private SystemUIVisibility config = new SystemUIVisibility();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onSystemUIVisibility(config);
        setSystemUIVisibility();
        setContentView(onLayout());
        unbinder = ButterKnife.bind(this);
        onObject();
        onView();
        onData();
    }

    /**
     * 设置状态栏图标颜色(Light|Dark),Android6.0+ 有效
     */
    private void statusIconColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            int vis = decorView.getSystemUiVisibility();
            if (config.isDarkStatusBar()) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(vis);
        }
    }

    /**
     * 设置状态栏图标颜色(Android 6.0+ 有效)
     *
     * @param isDark true: Dark, false: Light
     */
    protected void setDarkStatusBar(boolean isDark) {
        config.setDarkStatusBar(isDark);
        statusIconColor();
    }

    /**
     * 设置系统UI显示模式
     */
    private void setSystemUIVisibility() {
        if (config.isHideStatusBar()) {
            hideStatusBarStyle();
        }
        if (config.isHideNavigationBar()) {
            hideNavigationBarStyle();
        }
        if (config.isTranslucentStatusBar()) {
            translucentStatusBarStyle();
        }
        if (config.isTranslucentNavigationBar()) {
            translucentNavigationBarStyle();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(visibility);
        }
        statusIconColor();
    }

    /**
     * 隐藏状态栏 (Android 5.0+ 有效)
     */
    private void hideStatusBarStyle() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            visibility = visibility | View.SYSTEM_UI_FLAG_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(visibility);
        }
    }

    /**
     * 隐藏导航栏 (Android 5.0+ 有效)
     */
    private void hideNavigationBarStyle() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            visibility = visibility
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            window.getDecorView().setSystemUiVisibility(visibility);
        }
    }

    /**
     * 透明状态栏 (Android 4.4+ 有效)
     */
    private void translucentStatusBarStyle() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            visibility = visibility
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(visibility);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.translucent));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 透明导航栏 (Android 4.4+ 有效)
     */
    private void translucentNavigationBarStyle() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            visibility = visibility
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            window.getDecorView().setSystemUiVisibility(visibility);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(getResources().getColor(R.color.translucent));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 请求权限(Android 6.0+ 有效)
     *
     * @param permissions 权限数组
     * @param callback    请求回调
     */
    protected void requestSelfPermission(@NonNull String[] permissions, OnPermissionRequestListener callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                int self = ActivityCompat.checkSelfPermission(this, permission);
                if (self == PackageManager.PERMISSION_DENIED) {
                    this.callback = callback;
                    ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_CODE);
                    return;
                }
            }
            if (callback != null) {
                callback.onPermissionRequest(true, new ArrayList<>());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (callback != null) {
                boolean authorize = true;
                List<String> perm = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        authorize = false;
                        perm.add(permissions[i]);
                    }
                }
                callback.onPermissionRequest(authorize, perm);
                callback = null;
            }
        }
    }

    /**
     * 设置系统UI显示属性
     *
     * @param config 属性配置
     */
    protected void onSystemUIVisibility(SystemUIVisibility config) {
        /* 默认高亮状态栏图标 */
        config.setDarkStatusBar(false);
        /* 默认显示状态栏 */
        config.setHideStatusBar(false);
        /* 默认隐藏导航栏 */
        config.setHideNavigationBar(true);
        /* 默认透明状态栏 */
        config.setTranslucentStatusBar(true);
        /* 默认透明导航栏 */
        config.setTranslucentNavigationBar(true);
    }

    /**
     * 初始化布局
     *
     * @return 返回布局资源id
     */
    protected abstract @LayoutRes
    int onLayout();

    /**
     * 初始化对象
     */
    protected abstract void onObject();

    /**
     * 初始化视图
     */
    protected abstract void onView();

    /**
     * 初始化数据
     */
    protected abstract void onData();

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroy();
    }
}
