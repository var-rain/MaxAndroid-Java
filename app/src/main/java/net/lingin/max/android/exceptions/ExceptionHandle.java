package net.lingin.max.android.exceptions;

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
        // TODO: 2019/6/17 处理你的自定义异常信息
        return false;
    }
}
