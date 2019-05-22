package net.lingin.max.android.exceptions;

import net.lingin.max.android.MainApplication;
import net.lingin.max.android.R;

/**
 * Created by: var_rain.
 * Created date: 2018/11/7.
 * Description: 未登录异常定义
 */
public class NotSignException extends RuntimeException {

    /**
     * 构造方法
     */
    public NotSignException() {
        super(MainApplication.instance().getString(R.string.app_exception_not_sign));
    }
}
