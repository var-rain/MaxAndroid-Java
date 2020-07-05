package max.android.template;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import max.android.template.crash.GlobalCrashCapture;
import max.android.template.repository.Network;
import max.android.template.worker.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: var_rain.
 * Created date: 2020/1/3.
 * Description: 入口
 */
public class Max extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    // 静态实例
    @SuppressLint("StaticFieldLeak")
    private static Max INS;
    // 当前所在的Activity
    private Activity activity;
    // Activity管理集合
    private List<Activity> activities;

    @Override
    public void onCreate() {
        super.onCreate();
        Worker.init(Worker.newCachedThreadPool());
        GlobalCrashCapture.ins().init(this);
        this.activities = new ArrayList<>();
        this.registerActivityLifecycleCallbacks(this);
        Max.INS = this;
        Network.init();
    }

    /**
     * 获取静态实例
     *
     * @return 返回一个 {@link Max} 对象
     */
    public static Max ins() {
        return INS;
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
    @SuppressWarnings("unused")
    public void exit() {
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
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        this.activities.add(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        this.activities.remove(activity);
    }
}
