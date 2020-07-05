package max.android.template.repository;

import java.io.File;

/**
 * Created by: var_rain.
 * Created date: 2020/7/4.
 * Description: 下载管理器
 */
public interface DownloadListener {
    /**
     * 下载成功
     *
     * @param file 文件保存绝对位置
     */
    void success(File file);

    /**
     * 下载中
     *
     * @param size  文件总大小 单位:kb
     * @param value 下载进度(0-100)
     */
    void downloading(int size, int value);

    /**
     * 下载出错
     *
     * @param e 错误信息
     */
    void error(Exception e);
}
