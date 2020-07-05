package max.android.template.repository;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by: var_rain.
 * Created date: 2019/9/23.
 * Description: 下载管理器
 */
class Download {

    // 下载目录
    private final String dir;
    // 保存文件名
    private final String name;
    // 下载地址
    private final String url;
    // 网络请求
    private final OkHttpClient client;
    // 下载监听
    private final DownloadListener listener;
    // 下载文件总大小 单位:bit
    private long contentSize;
    // 已下载大小 单位:bit
    private long downloadSize;
    // 下载文件对象
    private File file;

    /**
     * 构造方法
     *
     * @param dir      下载文件保存位置
     * @param name     下载文件名(为空则默认取url指向的文件名)
     * @param url      下载地址
     * @param listener 下载监听
     */
    Download(@NonNull String dir, @NonNull String name, @NonNull String url, @NonNull OkHttpClient client, @NonNull DownloadListener listener) {
        this.dir = dir;
        this.name = name;
        this.url = url;
        this.listener = listener;
        this.client = client;
    }

    /**
     * 开始下载
     */
    void start() {
        try {
            this.contentSize = 0;
            this.downloadSize = 0;
            this.check();
            this.download();
        } catch (Exception e) {
            this.listener.error(e);
        }
    }

    /**
     * 检查参数
     *
     * @throws Exception 文件夹或文件删除/创建失败时会抛出异常
     */
    private void check() throws Exception {
        // 检查文件夹,不存在则创建
        File dir = new File(this.dir);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("make directory fail");
            }
        }
        // 检查文件是否存在,存在则删除
        this.file = new File(dir, name);
        if (this.file.exists()) {
            if (!this.file.delete()) {
                throw new IOException("old file delete fail");
            }
        }
        // 创建新文件
        if (!this.file.createNewFile()) {
            throw new IOException("new file create fail");
        }
    }

    /**
     * 下载文件
     */
    private void download() {
        Request request = new Request.Builder().url(url).get().build();
        this.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.error(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    contentSize = body.contentLength();
                    writeStreamToFile(body.byteStream());
                } else {
                    listener.error(new Exception("ResponseBody is empty"));
                }
            }
        });
    }

    /**
     * 保存到文件
     *
     * @param in 输入流
     */
    private void writeStreamToFile(InputStream in) throws IOException {
        OutputStream out = new FileOutputStream(this.file, false);
        byte[] bytes = new byte[2048];
        int len;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
            this.downloadSize += len;
            this.listener.downloading(((int) (this.contentSize / 1024)), (int) (this.downloadSize * 1.0f / this.contentSize * 100));
        }
        out.flush();
        out.close();
        in.close();
        // 检查文件完整性
        if (this.file.exists() && this.contentSize == this.downloadSize) {
            this.listener.success(this.file);
        } else {
            this.listener.error(new Exception("file write fail"));
        }
    }
}

