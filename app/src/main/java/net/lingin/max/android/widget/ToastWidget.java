package net.lingin.max.android.widget;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.lingin.max.android.MainApplication;
import net.lingin.max.android.R;

/**
 * Created by: var_rain.
 * Created time: 2019/5/22.
 * Description: Toast提示框
 */
public enum ToastWidget {
    @SuppressLint("StaticFieldLeak") INSTANCE;
    private Toast mToast;
    private TextView mTextView;

    /**
     * 显示Toast
     *
     * @param content  显示内容
     * @param gravity  显示位置
     * @param duration 显示时间
     */
    public void showToast(String content, int gravity, int duration) {
        if (mToast == null) {
            mToast = new Toast(MainApplication.instance().activity());
            mToast.setDuration(duration);
            mToast.setGravity(gravity, 0, 0);
            @SuppressLint("InflateParams")
            View view = LayoutInflater.from(MainApplication.instance().activity()).inflate(R.layout.widget_toast, null);
            mTextView = view.findViewById(R.id.view_toast_tv);
            mToast.setView(view);
        }
        mTextView.setText(content);
        mToast.show();
    }
}
