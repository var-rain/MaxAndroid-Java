package net.lingin.max.android.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import net.lingin.max.android.MainApplication;

/**
 * Created by: var_rain.
 * Created date: 2019/2/24.
 * Description: 版本相关工具类
 */
public class VersionUtils {

    /**
     * 获取应用程序版本号
     *
     * @return 返回应用程序的当前版本号字符串
     */
    public static String appVersionName() {
        String version = null;
        try {
            PackageManager pm = MainApplication.instance().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(MainApplication.instance().getPackageName(), 0);
            version = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
