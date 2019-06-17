package net.lingin.max.android.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import net.lingin.max.android.R;
import net.lingin.max.android.ui.listener.OnAnimationListener;
import net.lingin.max.android.utils.InjectViewUtils;

/**
 * Created by: var_rain.
 * Created date: 2018/10/23.
 * Description: 通知提示
 */
public class NoticeWidget {

    /* 静态实例 */
    @SuppressLint("StaticFieldLeak")
    private static NoticeWidget instance;
    /* 通知显示 */
    private View notice;
    /* 动画 */
    private AnimatorSet anim;
    /* 通知内容 */
    private TextView text;

    /**
     * 构造方法
     */
    private NoticeWidget() {
        anim = makeAnim();
    }

    /**
     * 获取静态实例
     *
     * @return 返回一个 {@link NoticeWidget} 对象
     */
    public static NoticeWidget instance() {
        if (NoticeWidget.instance == null) {
            NoticeWidget.instance = new NoticeWidget();
        }
        return NoticeWidget.instance;
    }

    /**
     * 显示通知
     *
     * @param content 通知内容
     */
    @SuppressLint("InflateParams")
    public void showNotice(String content) {
        if (notice == null) {
            notice = InjectViewUtils.instance().make(R.layout.widget_notice);
            text = notice.findViewById(R.id.notice_text);
        }
        text.setText(content);
        if (anim.isRunning()) {
            anim.end();
        }
        InjectViewUtils.instance().inject(notice);
        anim.start();
    }

    /**
     * 创建动画
     *
     * @return 返回一个 {@link AnimatorSet} 对象
     */
    private AnimatorSet makeAnim() {
        ValueAnimator down = makeDownAnim();
        ValueAnimator up = makeUpAnim();
        AnimatorSet set = new AnimatorSet();
        set.play(up).after(down).after(2000);
        set.addListener(new OnAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                InjectViewUtils.instance().clean(notice);
            }
        });
        return set;
    }

    /**
     * 创建下滑动画
     *
     * @return 返回 {@link ValueAnimator} 对象
     */
    private ValueAnimator makeDownAnim() {
        ValueAnimator anim = ValueAnimator.ofFloat(-dp2px(), 0);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(300);
        anim.addUpdateListener(animation -> {
            if (notice != null) {
                notice.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        return anim;
    }

    /**
     * 创建上滑动画
     *
     * @return 返回 {@link ValueAnimator} 对象
     */
    private ValueAnimator makeUpAnim() {
        ValueAnimator anim = ValueAnimator.ofFloat(0, -dp2px());
        anim.setInterpolator(new AccelerateInterpolator());
        anim.setDuration(300);
        anim.addUpdateListener(animation -> {
            if (notice != null) {
                notice.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        return anim;
    }

    /**
     * 从dp单位转换为px
     *
     * @return 返回转换后的px值
     */
    private int dp2px() {
        return (int) (100 * Resources.getSystem().getDisplayMetrics().density);
    }
}
