package net.lingin.max.android.ui.base;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import net.lingin.max.android.ui.base.listener.OnPermissionRequestListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by: var_rain.
 * Created date: 2018/10/20.
 * Description: Fragment父类
 */
@SuppressWarnings("all")
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private OnPermissionRequestListener callback;
    private final int REQUEST_PERMISSION_CODE = 998;
    protected T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, onLayout(), container, false);
        binding.setLifecycleOwner(this);
        onObject();
        onView();
        onData();
        return binding.getRoot();
    }

    /**
     * 请求权限
     *
     * @param permissions 权限数组
     * @param callback    请求回调
     */
    protected void requestSelfPermission(@NonNull String[] permissions, OnPermissionRequestListener callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                int self = ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), permission);
                if (self == PackageManager.PERMISSION_DENIED) {
                    this.callback = callback;
                    this.requestPermissions(permissions, REQUEST_PERMISSION_CODE);
                    return;
                }
            }
            if (callback != null) {
                callback.onPermissionRequest(true, new ArrayList<>());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (callback != null) {
                boolean authorize = true;
                List<String> perm = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        authorize = false;
                        perm.add(permissions[i]);
                    }
                }
                callback.onPermissionRequest(authorize, perm);
                callback = null;
            }
        }
    }

    /**
     * 初始化布局
     *
     * @return 返回布局资源id
     */
    protected abstract @LayoutRes
    int onLayout();

    /**
     * 初始化对象
     */
    protected abstract void onObject();

    /**
     * 初始化视图
     */
    protected abstract void onView();

    /**
     * 初始化数据
     */
    protected abstract void onData();

    @Override
    public void onDestroyView() {
        if (binding != null) {
            binding.unbind();
        }
        super.onDestroyView();
    }
}
