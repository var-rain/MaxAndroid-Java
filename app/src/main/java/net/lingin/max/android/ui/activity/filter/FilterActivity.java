package net.lingin.max.android.ui.activity.filter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by: var_rain.
 * Created date: 2018/10/16.
 * Description: 外部启动拦截器
 */
public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2019/5/19 拦截通过scheme协议跳转到app的启动请求
        finish();
    }
}
