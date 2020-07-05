package max.android.template.repository;

import max.android.template.BuildConfig;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 服务器接口映射
 */
public interface ApiService {

    // 服务器地址
    String BASE_URL = BuildConfig.SERVER_BASE;
    // 超时 (秒)
    int TIME_OUT = 15;
}
