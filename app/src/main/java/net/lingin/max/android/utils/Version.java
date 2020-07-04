package net.lingin.max.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: var_rain.
 * Created date: 2019/2/24.
 * Description: 版本相关工具类
 */
@SuppressWarnings("all")
public class Version {

    /**
     * 获取应用程序版本号
     *
     * @return 返回应用程序的当前版本号字符串
     */
    public static String appVersionName() {
        String version = null;
        try {
            PackageManager pm = net.lingin.max.android.Max.ins().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(net.lingin.max.android.Max.ins().getPackageName(), 0);
            version = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 格式化版本号
     *
     * @param version 版本号
     * @return 返回版本号 x.y.z
     */
    private static String getVersion(String version) {
        Pattern compile = Pattern.compile("[^0-9.]");
        Matcher matcher = compile.matcher(version);
        return matcher.replaceAll("");
    }

    /**
     * 是否需要升级
     *
     * @param targetVersion 目标版本号
     * @return true:需要 false:不需要
     */
    public static boolean needUpdate(String targetVersion) {
        String locate = getVersion(appVersionName());
        String target = getVersion(targetVersion);
        String[] lv = locate.split("\\.");
        String[] tv = target.split("\\.");
        int min = Math.min(lv.length, tv.length);
        for (int i = 0; i < min; i++) {
            if (Integer.parseInt(tv[i]) > Integer.parseInt(lv[i])) {
                return true;
            }
        }
        if (lv.length != tv.length) {
            return lv.length < tv.length;
        }
        return false;
    }

    /**
     * 跳转安装
     *
     * @param activity 上下文参数
     * @param apk      下载文件
     */
    public static void installNewVersion(@NonNull Activity activity, @NonNull File apk) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            String authority = "net.lingin.max.android.FileProvider";
            Uri fileUri = FileProvider.getUriForFile(activity, authority, apk);
            intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        activity.startActivity(intent);
    }
}
