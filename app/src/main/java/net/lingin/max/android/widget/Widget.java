package net.lingin.max.android.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import net.lingin.max.android.R;
import net.lingin.max.android.ui.listener.OnAnimationListener;
import net.lingin.max.android.utils.InjectViewUtils;

/**
 * Created by: var_rain.
 * Created time: 2019/5/22.
 * Description: 自定义控件父类,包括Toast,DialogUtils,Loading等一切显示于应用内的View
 */
public abstract class Widget {

    /* 主View */
    private View view;
    /* 背景视图 */
    private View background;
    /* 背景显示动画 */
    private ValueAnimator showBackground;
    /* 背景隐藏动画 */
    private ValueAnimator hideBackground;

    /**
     * 构造方法
     */
    Widget() {
        this.view = InjectViewUtils.instance().make(onInitLayoutRes());
        if (this.isShowBackground()) {
            this.background = InjectViewUtils.instance().make(R.layout.widget_background);
            this.background.setBackgroundColor(backgroundColor());
            this.background.setOnClickListener(v -> onClickOut());
            this.initAnimation();
        }
        this.onInitView();
    }

    /**
     * 初始化背景动画
     */
    private void initAnimation() {
        showBackground = ValueAnimator.ofFloat(0.0f, 1.0f);
        showBackground.setDuration(200);
        showBackground.addUpdateListener(animation -> this.background.setAlpha((Float) animation.getAnimatedValue()));
        showBackground.addListener(new OnAnimationListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                InjectViewUtils.instance().inject(background);
                InjectViewUtils.instance().inject(view);
            }
        });

        hideBackground = ValueAnimator.ofFloat(1.0f, 0.0f);
        hideBackground.setDuration(200);
        hideBackground.addUpdateListener(animation -> this.background.setAlpha((Float) animation.getAnimatedValue()));
        hideBackground.addListener(new OnAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                InjectViewUtils.instance().clean(view);
                InjectViewUtils.instance().clean(background);
            }
        });
    }

    /**
     * 创建View
     *
     * @return 返回 layout 资源ID
     */
    protected abstract @LayoutRes
    int onInitLayoutRes();

    /**
     * 初始化View,数据设置,事件绑定等
     */
    protected abstract void onInitView();

    /**
     * 是否显示半透背景
     *
     * @return true: 显示 false: 不显示
     */
    protected boolean isShowBackground() {
        return true;
    }

    /**
     * 设置背景颜色,默认黑色半透明效果,切记带上透明度
     * <p>
     * 调用 {@link android.content.res.Resources#getColor(int, Resources.Theme)} 获取到的颜色值
     * 或 {@link android.graphics.Color#parseColor(String)} 创建的颜色值
     * 或 0xffff0000 之类的颜色值
     *
     * @return 返回颜色的int值, 非颜色资源的int值
     */
    protected @ColorInt
    int backgroundColor() {
        return 0x40000000;
    }

    /**
     * 通过id获取 {@link View}
     *
     * @param id  View的ID
     * @param <T> 泛型对象
     * @return 返回获取的 {@link View} 对象,为获取到则返回null
     */
    protected <T extends View> T getView(@IdRes int id) {
        if (view != null) {
            return view.findViewById(id);
        }
        return null;
    }

    /**
     * 用户点击外部时的回调
     */
    protected void onClickOut() {

    }

    /**
     * 显示 {@link View}
     */
    protected void showView() {
        if (showBackground != null) {
            showBackground.start();
        } else {
            InjectViewUtils.instance().inject(view);
        }
    }

    /**
     * 隐藏 {@link View}
     */
    protected void hideView() {
        if (hideBackground != null) {
            hideBackground.start();
        } else {
            InjectViewUtils.instance().clean(view);
        }
    }
}
