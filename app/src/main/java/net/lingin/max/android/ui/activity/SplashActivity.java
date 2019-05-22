package net.lingin.max.android.ui.activity;

import android.view.KeyEvent;
import android.widget.TextView;

import net.lingin.max.android.R;
import net.lingin.max.android.ui.base.BaseActivity;
import net.lingin.max.android.utils.Version;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by: var_rain.
 * Created date: 2018/10/16.
 * Description: 启动页
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_jump_but)
    TextView splashJumpBut;
    @BindView(R.id.splash_app_version)
    TextView splashAppVersion;
    /* 倒计时总时间(单位:秒) */
    private final static int TIME = 5;
    /* 倒计时一次(单位:秒) */
    private final static int SECONDS = 1;
    private Disposable subscribe;

    @Override
    protected int onLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onObject() {
        subscribe = Observable.interval(0, SECONDS, TimeUnit.SECONDS)
                .map(aLong -> TIME - aLong)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (aLong < 0) {
                        startMainActivity();
                    } else {
                        splashJumpBut.setText(getString(R.string.app_finish_splash, aLong));
                    }
                });
    }

    @Override
    protected void onView() {

    }

    @Override
    protected void onData() {
        splashAppVersion.setText(getString(R.string.app_version, Version.appVersionName()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 跳过
     */
    @OnClick(R.id.splash_jump_but)
    public void onViewClicked() {
        startMainActivity();
    }

    /**
     * 启动主界面
     */
    private void startMainActivity() {
        if (!subscribe.isDisposed()) {
            subscribe.dispose();
        }
        // TODO: 2019/5/19 跳转到主界面
        finish();
    }
}
