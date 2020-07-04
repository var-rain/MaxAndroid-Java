package net.lingin.max.android.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Created by: var_rain.
 * Created date: 2018/12/21.
 * Description: 自定义标题栏
 */
public class StatusBar extends LinearLayout {

    public StatusBar(Context context) {
        super(context);
    }

    public StatusBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTopSize();
    }

    public StatusBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置默认上边距为状态栏高度
     */
    private void setTopSize() {
        setPadding(0, statusBarHeight(), 0, 0);
    }

    /**
     * 获取状态栏高度
     *
     * @return 返回状态栏高度的px单位
     */
    private int statusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
