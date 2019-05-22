package net.lingin.max.android.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.lingin.max.android.MainApplication;
import net.lingin.max.android.R;
import net.lingin.max.android.ui.listener.OnAnimationListener;
import net.lingin.max.android.ui.listener.OnDialogListener;

/**
 * Created by: var_rain.
 * Created date: 2018/11/7.
 * Description: 对话框显示
 */
public class DialogWidget extends Widget {

    /* 静态实例 */
    @SuppressLint("StaticFieldLeak")
    private static DialogWidget INSTANCE;
    /* 主视图 */
    private LinearLayout root;
    /* 内容 */
    private TextView content;
    /* 对话框点击回调接口 */
    private OnDialogListener listener;
    /* 显示动画 */
    private ValueAnimator in;
    /* 隐藏动画 */
    private ValueAnimator out;

    /**
     * 构造方法
     */
    private DialogWidget() {
        super();
    }

    /**
     * 获取静态实例
     *
     * @return {@link DialogWidget}
     */
    public static DialogWidget instance() {
        if (DialogWidget.INSTANCE == null) {
            DialogWidget.INSTANCE = new DialogWidget();
        }
        return INSTANCE;
    }

    @Override
    protected int onInitLayoutRes() {
        return R.layout.widget_dialog;
    }

    @Override
    protected void onInitView() {
        root = getView(R.id.dialog_root);
        content = getView(R.id.dialog_content);
        getView(R.id.dialog_ok).setOnClickListener(v -> click(true));
        getView(R.id.dialog_cancel).setOnClickListener(v -> click(false));
        initAnimation();
    }

    /**
     * 显示对话框
     *
     * @param text 显示内容
     */
    public void show(String text) {
        show(text, null);
    }

    /**
     * 显示对话框
     *
     * @param listener 回调接口
     */
    public void show(OnDialogListener listener) {
        show(null, listener);
    }

    /**
     * 显示对话框
     *
     * @param id       文字资源ID
     * @param listener 回调接口
     */
    public void show(int id, OnDialogListener listener) {
        show(MainApplication.instance().getString(id), listener);
    }

    /**
     * 显示对话框
     *
     * @param text     显示内容
     * @param listener 回调接口
     */
    public void show(String text, OnDialogListener listener) {
        this.listener = listener;
        if (text != null) {
            content.setText(text);
        }
        in.start();
    }

    /**
     * 对话框点击处理
     *
     * @param status 点击的按钮 true: 确定 false: 取消
     */
    private void click(boolean status) {
        if (this.listener != null) {
            listener.onClick(status);
        }
        out.start();
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        in = ValueAnimator.ofFloat(0.0f, 1.0f);
        in.setDuration(200);
        in.setInterpolator(new AccelerateInterpolator());
        in.addUpdateListener(animation -> root.setAlpha((Float) animation.getAnimatedValue()));
        in.addListener(new OnAnimationListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                showView();
            }
        });

        out = ValueAnimator.ofFloat(1.0f, 0.0f);
        out.setDuration(200);
        out.setInterpolator(new DecelerateInterpolator());
        out.addUpdateListener(animation -> root.setAlpha((Float) animation.getAnimatedValue()));
        out.addListener(new OnAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hideView();
            }
        });
    }
}
