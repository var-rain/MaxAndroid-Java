package max.android.template.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;

import max.android.template.R;
import max.android.template.databinding.ActivitySplashBinding;
import max.android.template.ui.base.BaseActivity;
import max.android.template.utils.Version;

/**
 * Created by: var_rain.
 * Created date: 2018/10/16.
 * Description: 启动页
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected int onLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onObject() {

    }

    @Override
    protected void onView() {
        // 显示APP版本号
        binding.tvAppVersion.setText(String.format("V%s", Version.appVersionName()));
    }

    @Override
    protected void onData() {
        // 等待3秒后跳转到主界面
        binding.getRoot().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 3000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
