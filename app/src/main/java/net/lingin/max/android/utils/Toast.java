package net.lingin.max.android.utils;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;

import net.lingin.max.android.R;
import net.lingin.max.android.databinding.ViewToastBinding;

/**
 * Created by: var_rain.
 * Created date: 2020/1/3.
 * Description: Toast工具
 */
@SuppressWarnings("all")
public class Toast {

    // 长时间显示
    public final static int TOAST_DURATION_LONG = 1;
    // 短时间显示
    public final static int TOAST_DURATION_SHORT = 0;

    /**
     * 显示Toast
     *
     * @param text 字符串
     */
    public static void show(String text) {
        ToastView.INS.showToast(text, Gravity.CENTER, Toast.TOAST_DURATION_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param id 字符串资源ID
     */
    public static void show(@StringRes int id) {
        ToastView.INS.showToast(net.lingin.max.android.Max.ins().getString(id), Gravity.CENTER, TOAST_DURATION_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param id   字符串资源ID
     * @param args 格式化参数
     */
    public static void show(@StringRes int id, Object... args) {
        ToastView.INS.showToast(net.lingin.max.android.Max.ins().getString(id, args), Gravity.CENTER, TOAST_DURATION_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param text     字符串
     * @param gravity  显示位置,参考 {@link Gravity}
     * @param duration 显示时长,接受参数 {@link Toast#TOAST_DURATION_SHORT,Toast#TOAST_DURATION_LONG}
     */
    public static void show(String text, int gravity, int duration) {
        ToastView.INS.showToast(text, gravity, duration);
    }

    /**
     * 子线程中显示Toast
     *
     * @param text 字符串
     */
    public static void showSync(String text) {
        net.lingin.max.android.Max.ins().activity().runOnUiThread(() -> Toast.show(text));
    }

    /**
     * 子线程中显示Toast
     *
     * @param id 字符串资源ID
     */
    public static void showSync(@StringRes int id) {
        net.lingin.max.android.Max.ins().activity().runOnUiThread(() -> Toast.show(id));
    }

    /**
     * 子线程中显示Toast
     *
     * @param id   字符串资源ID
     * @param args 格式化参数
     */
    public static void showSync(@StringRes int id, Object... args) {
        net.lingin.max.android.Max.ins().activity().runOnUiThread(() -> Toast.show(id, args));
    }

    /**
     * 子线程中显示Toast
     *
     * @param text     字符串
     * @param gravity  显示位置,参考 {@link Gravity}
     * @param duration 显示时长,接受参数 {@link Toast#TOAST_DURATION_SHORT,Toast#TOAST_DURATION_LONG}
     */
    public static void showSync(String text, int gravity, int duration) {
        net.lingin.max.android.Max.ins().activity().runOnUiThread(() -> Toast.show(text, gravity, duration));
    }

    /**
     * Created by: var_rain.
     * Created time: 2020/1/3.
     * Description: Toast提示框
     */
    public enum ToastView {
        @SuppressLint("StaticFieldLeak") INS;

        /**
         * 显示Toast
         *
         * @param content  显示内容
         * @param gravity  显示位置
         * @param duration 显示时间
         */
        public void showToast(String content, int gravity, int duration) {
            android.widget.Toast mToast = new android.widget.Toast(net.lingin.max.android.Max.ins().activity());
            mToast.setDuration(duration);
            mToast.setGravity(gravity, 0, 0);
            ViewToastBinding binding = DataBindingUtil.inflate(LayoutInflater.from(net.lingin.max.android.Max.ins().activity()), R.layout.view_toast, null, false);
            TextView mTextView = binding.tvToastText;
            mToast.setView(binding.getRoot());
            mTextView.setText(content);
            mToast.show();
        }
    }
}
