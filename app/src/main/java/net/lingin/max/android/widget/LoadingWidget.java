package net.lingin.max.android.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.lingin.max.android.R;
import net.lingin.max.android.ui.listener.OnAnimationListener;

/**
 * Created by: var_rain.
 * Created time: 2019/5/22.
 * Description: 加载对话框
 */
public class LoadingWidget extends Widget {

    /* 静态实例 */
    @SuppressLint("StaticFieldLeak")
    private static LoadingWidget INSTANCE;
    /* 加载动画 */
    private LinearLayout loadLayout;
    /* 加载文本 */
    private TextView loadText;
    /* 显示动画 */
    private ValueAnimator in;
    /* 隐藏动画 */
    private ValueAnimator out;

    /**
     * 构造方法
     */
    private LoadingWidget() {
        super();
    }

    /**
     * 获取静态实例
     *
     * @return {@link LoadingWidget}
     */
    public static LoadingWidget instance() {
        if (LoadingWidget.INSTANCE == null) {
            LoadingWidget.INSTANCE = new LoadingWidget();
        }
        return INSTANCE;
    }

    @Override
    protected int onInitLayoutRes() {
        return R.layout.widget_loading;
    }

    @Override
    protected void onInitView() {
        loadText = getView(R.id.loading_text);
        loadLayout = getView(R.id.loading_view);
        initAnim();
    }

    /**
     * 显示加载框
     */
    public void show() {
        show(null);
    }

    /**
     * 显示加载框
     *
     * @param text 提示信息
     */
    public void show(String text) {
        if (text != null) {
            loadText.setText(text);
        }
        in.start();
    }

    /**
     * 隐藏加载框
     */
    public void hide() {
        out.start();
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        /* 初始化显示翻转动画 */
        in = ValueAnimator.ofFloat(-90.0f, 0.0f);
        in.setDuration(200);
        in.addUpdateListener(animation -> {
            if (loadLayout != null) {
                loadLayout.setRotationY((Float) animation.getAnimatedValue());
            }
        });
        in.addListener(new OnAnimationListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                showView();
            }
        });

        /* 初始化隐藏翻转动画 */
        out = ValueAnimator.ofFloat(0.0f, 90.0f);
        out.setDuration(200);
        out.addUpdateListener(animation -> {
            if (loadLayout != null) {
                loadLayout.setRotationY((Float) animation.getAnimatedValue());
            }
        });
        out.addListener(new OnAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hideView();
            }
        });
    }
}
