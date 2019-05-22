package net.lingin.max.android.ui.listener;

import android.support.annotation.NonNull;

/**
 * Created by: var_rain.
 * Created time: 2019/5/19.
 * Description: 权限请求回调
 */
public interface OnPermissionRequestListener {

    /**
     * 权限请求回调
     *
     * @param authorize   是否全部成功授权
     * @param permissions 权限列表
     * @param granted     授权结果
     */
    void onPermissionRequest(boolean authorize, @NonNull String[] permissions, @NonNull int[] granted);
}
