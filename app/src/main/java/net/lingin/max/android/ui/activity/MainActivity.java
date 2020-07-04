package net.lingin.max.android.ui.activity;

import android.annotation.SuppressLint;

import net.lingin.max.android.R;
import net.lingin.max.android.databinding.ActivityMainBinding;
import net.lingin.max.android.ui.base.BaseActivity;
import net.lingin.max.android.ui.base.config.SystemUIVisibility;

/**
 * Created by: var_rain.
 * Created date: 2018/10/16.
 * Description: 主界面
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onObject() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onView() {
        binding.tvDes.setText("MAX ANDROID是一个帮助Android开发者快速开发APP的简单框架" +
                ",(严格的说来,他并不算一个框架,仅仅是封装了BaseActivity和BaseFragment而已)" +
                "在V3.0.0版本中使用MVVM模式并启用DataBinding来加快开发速度," +
                "针对一些常用的东西进行简单封装,简化代码量,您可以在Github上" +
                "找到他的完整代码及其使用示例");
    }

    @Override
    protected void onData() {

    }
}
