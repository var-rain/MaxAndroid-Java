package net.lingin.max.android.ui.listener;

/**
 * Created by: var_rain.
 * Created date: 2018/11/7.
 * Description: 对话框点击监听回调接口
 */
public interface OnDialogListener {

    /**
     * 按钮事件回调
     *
     * @param isOK 按钮 true:确定 false 取消
     */
    default void onClick(boolean isOK) {
    }
}
