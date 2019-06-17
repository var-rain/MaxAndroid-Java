package net.lingin.max.android.ui.base.config;

/**
 * Created by: var_rain.
 * Created time: 2019/5/20.
 * Description: 系统UI显示配置项
 */
public class SystemUIVisibility {

    /* 是否使用暗色状态栏图标 (Android 6.0+ 有效) */
    private boolean isDarkStatusBar;
    /* 是否隐藏状态栏  (Android 5.0+ 有效) */
    private boolean isHideStatusBar;
    /* 是否隐藏导航栏虚拟按键  (Android 5.0+ 有效) */
    private boolean isHideNavigationBar;
    /* 是否透明状态栏  (Android 4.4+ 有效) */
    private boolean isTranslucentStatusBar;
    /* 是否透明导航栏虚拟按键  (Android 4.4+ 有效) */
    private boolean isTranslucentNavigationBar;

    public boolean isDarkStatusBar() {
        return isDarkStatusBar;
    }

    public void setDarkStatusBar(boolean darkStatusBar) {
        isDarkStatusBar = darkStatusBar;
    }

    public boolean isHideStatusBar() {
        return isHideStatusBar;
    }

    public void setHideStatusBar(boolean hideStatusBar) {
        isHideStatusBar = hideStatusBar;
    }

    public boolean isHideNavigationBar() {
        return isHideNavigationBar;
    }

    public void setHideNavigationBar(boolean hideNavigationBar) {
        isHideNavigationBar = hideNavigationBar;
    }

    public boolean isTranslucentStatusBar() {
        return isTranslucentStatusBar;
    }

    public void setTranslucentStatusBar(boolean translucentStatusBar) {
        isTranslucentStatusBar = translucentStatusBar;
    }

    public boolean isTranslucentNavigationBar() {
        return isTranslucentNavigationBar;
    }

    public void setTranslucentNavigationBar(boolean translucentNavigationBar) {
        isTranslucentNavigationBar = translucentNavigationBar;
    }
}