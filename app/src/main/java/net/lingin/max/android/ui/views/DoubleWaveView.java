package net.lingin.max.android.ui.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import net.lingin.max.android.R;

/**
 * Created by: var_rain.
 * Created date: 2018/10/28.
 * Description: 双波浪View
 */
public class DoubleWaveView extends View {

    /* 宽度 */
    private int width;
    /* 高度 */
    private int height;
    /* 画笔 */
    private Paint paint;
    /* 基线 */
    private int base;
    /* 浪高 */
    private int level;
    /* 第一个波浪偏移 */
    private int after;
    /* 第二个波浪偏移 */
    private int before;
    /* 背景色 */
    private int bgColor;
    /* 波浪颜色 */
    private int waveColor;
    /* 波浪透明度 */
    private int alpha;
    /* 波浪速度 */
    private int speed;
    /* 第一个波浪动画 */
    private ValueAnimator afterAnim;
    /* 第二个波浪动画 */
    private ValueAnimator beforeAnim;
    /* 波浪 */
    private Bitmap bitmap;
    /* 默认混合模式 */
    private PorterDuffXfermode srcMode;
    /* 裁剪混合模式 */
    private PorterDuffXfermode decMode;
    /* 圆形遮罩 */
    private boolean circle;
    /* 最大延时 */
    private final static int maxDuration = 2000;

    public DoubleWaveView(Context context) {
        super(context);
    }

    public DoubleWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DoubleWaveView);
        base = array.getInt(R.styleable.DoubleWaveView_dw_water_level, 50);
        level = array.getDimensionPixelSize(R.styleable.DoubleWaveView_dw_wave_height, 10);
        bgColor = array.getColor(R.styleable.DoubleWaveView_dw_bg_color, 0xff666666);
        waveColor = array.getColor(R.styleable.DoubleWaveView_dw_wave_color, 0xffffffff);
        alpha = (int) (255 * array.getFloat(R.styleable.DoubleWaveView_dw_wave_alpha, 0.5f));
        speed = (int) (maxDuration - (maxDuration * array.getFloat(R.styleable.DoubleWaveView_dw_velocity, 0.8f))) + (maxDuration / 10);
        circle = array.getBoolean(R.styleable.DoubleWaveView_dw_circle, true);
        array.recycle();
        init();
    }

    public DoubleWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(bgColor);
        if (circle) {
            srcMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
            decMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = getWidth();
        height = getHeight();
        if (afterAnim == null || beforeAnim == null) {
            initAnim();
        }
        if (bitmap == null) {
            makeWave();
        }

        /* 绘制形状 */
        if (circle) {
            paint.setXfermode(decMode);
            paint.setAlpha(255);
            canvas.drawCircle(width >> 1, height >> 1, width >> 1, paint);
            paint.setXfermode(srcMode);
        }

        /* 绘制第一个波浪 */
        paint.setAlpha(alpha);
        /* 我也不知道为什么要加上(level*2),总之,这么干就对了 */
        int baseLine = height - ((height / 100 * base) + (level * 2));
        canvas.drawBitmap(bitmap, after, baseLine, paint);
        canvas.drawBitmap(bitmap, (-width + after), baseLine, paint);

        /* 绘制第二个波浪 */
        paint.setAlpha((int) (alpha * 1.5f));
        canvas.drawBitmap(bitmap, (before + (width / 3f)), baseLine, paint);
        canvas.drawBitmap(bitmap, (-width + before + (width / 3)), baseLine, paint);
        canvas.drawBitmap(bitmap, (-(width << 1) + before + (width / 3)), baseLine, paint);

        if (circle) {
            /* 绘制混合遮罩 */
            paint.setAlpha(255);
            canvas.drawRect(0, 0, width, height, paint);
        }

        super.onDraw(canvas);
        invalidate();

        /* 解决Android 8.0+ 锁屏解锁后动画休眠的问题 */
        if (!afterAnim.isRunning()) {
            afterAnim.start();
        }
        if (!beforeAnim.isRunning()) {
            beforeAnim.start();
        }
    }

    /**
     * 创建波浪效果
     */
    private void makeWave() {
        Paint paint = new Paint();
        paint.setColor(waveColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.moveTo(0, level);
        path.quadTo(width >> 2, 0, width >> 1, level);
        path.quadTo(width - (width >> 2), level << 1, width, level);
        path.close();
        bitmap = Bitmap.createBitmap(width, height + (height >> 1), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        canvas.drawRect(0, level, width, height + (height >> 1), paint);
        canvas.save();
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        beforeAnim = ValueAnimator.ofInt(0, width);
        beforeAnim.addUpdateListener(animation -> before = (int) animation.getAnimatedValue());
        beforeAnim.setRepeatMode(ValueAnimator.RESTART);
        beforeAnim.setRepeatCount(-1);
        beforeAnim.setInterpolator(new LinearInterpolator());
        beforeAnim.setDuration(speed);
        beforeAnim.start();

        afterAnim = ValueAnimator.ofInt(width, 0);
        afterAnim.addUpdateListener(animation -> after = (int) animation.getAnimatedValue());
        afterAnim.setRepeatMode(ValueAnimator.RESTART);
        afterAnim.setRepeatCount(-1);
        afterAnim.setInterpolator(new LinearInterpolator());
        afterAnim.setDuration((long) (speed * 1.5f));
        afterAnim.start();
    }
}
