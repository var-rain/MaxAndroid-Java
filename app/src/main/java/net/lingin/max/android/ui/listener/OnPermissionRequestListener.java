package net.lingin.max.android.ui.listener;

import androidx.annotation.NonNull;

import java.util.List;

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
     * @param permissions 未授权权限列表
     */
    void onPermissionRequest(boolean authorize, @NonNull List<String> permissions);
}
