package net.lingin.max.android.exceptions;

import net.lingin.max.android.R;
import net.lingin.max.android.ui.listener.OnDialogListener;
import net.lingin.max.android.utils.Dialog;

/**
 * Created by: var_rain.
 * Created time: 2019/5/19.
 * Description: 自定义运行时异常处理
 */
public class ExceptionHandle {

    /* 静态实例 */
    private static ExceptionHandle INSTANCE;

    /**
     * 获取静态实例
     *
     * @return {@link ExceptionHandle}
     */
    public static ExceptionHandle instance() {
        if (ExceptionHandle.INSTANCE == null) {
            ExceptionHandle.INSTANCE = new ExceptionHandle();
        }
        return ExceptionHandle.INSTANCE;
    }

    /**
     * 处理自定义异常信息
     *
     * @param throwable 异常信息
     * @return true: 已处理 false: 未处理
     */
    public boolean handleException(Throwable throwable) {
        if (throwable instanceof NotSignException) {
            handleNotSignException();
            return true;
        }
        return false;
    }

    /**
     * 处理未登录异常信息
     */
    private void handleNotSignException() {
        Dialog.show(R.string.app_dialog_no_sign, new OnDialogListener() {
            @Override
            public void onClick(boolean isOK) {

            }
        });
    }
}
