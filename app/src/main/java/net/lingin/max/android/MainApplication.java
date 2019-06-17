package net.lingin.max.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;

import net.lingin.max.android.exceptions.crash.GlobalCrashCapture;
import net.lingin.max.android.net.Network;
import net.lingin.max.android.threads.Worker;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by: var_rain.
 * Created date: 2018/10/16.
 * Description: 程序入口点
 */
public class MainApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    /* 静态实例 */
    @SuppressLint("StaticFieldLeak")
    private static MainApplication instance;
    /* 当前所在的界面 */
    private Activity activity;
    /* Activity管理集合 */
    private List<Activity> activities;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 初始化全局异常捕获
        GlobalCrashCapture.instance().init(this);
        // 创建Activity储存管理
        activities = new ArrayList<>();
        // 注册Activity生命周期回调
        registerActivityLifecycleCallbacks(this);
        // 初始化线程池
        Worker.init(Worker.newCachedThreadPool());
        // 初始化网络请求
        Network.init();
        // 初始化Realm数据库
        Realm.init(this);
    }

    /**
     * 获取静态实例
     *
     * @return 返回一个MassApplication对象
     */
    public static MainApplication instance() {
        return instance;
    }

    /**
     * 获取当前显示的Activity
     *
     * @return 返回一个 {@link Activity} 对象
     */
    public Activity activity() {
        return this.activity;
    }

    /**
     * 退出APP
     */
    public void quit() {
        for (Activity act : activities) {
            if (act != null && !act.isFinishing()) {
                act.finish();
            }
        }
        activities.clear();
        Worker.destroy();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
        this.activity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
    }
}
