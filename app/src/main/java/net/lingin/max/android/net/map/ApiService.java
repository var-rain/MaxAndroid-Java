package net.lingin.max.android.net.map;

import net.lingin.max.android.BuildConfig;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 服务器接口映射
 */
public interface ApiService {

    /* 服务器地址, 在 build.gradle 中配置 */
    String BASE_URL = BuildConfig.SERVER_BASE;
    /* 超时 */
    int TIME_OUT = 15;
}
