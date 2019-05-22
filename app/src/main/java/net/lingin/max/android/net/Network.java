package net.lingin.max.android.net;

import net.lingin.max.android.net.map.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 网络访问工具类
 */
public class Network {

    /* 网络接口 */
    private static ApiService api;

    /**
     * 初始化方法,需要在{@link android.app.Application} 中初始化
     */
    public static void init() {
        OkHttpClient client = Network.initHttpClient();
        Network.api = Network.initApiService(client);
    }

    /**
     * 初始化 {@link OkHttpClient}
     *
     * @return {@link OkHttpClient}
     */
    private static OkHttpClient initHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .connectTimeout(ApiService.TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(ApiService.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(ApiService.TIME_OUT, TimeUnit.SECONDS).build();
    }

    /**
     * 初始化网络请求接口
     *
     * @param client {@link OkHttpClient}
     * @return {@link ApiService}
     */
    private static ApiService initApiService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    /**
     * 获取网路接口
     *
     * @return 返回一个ApiService接口对象
     */
    public static ApiService api() {
        return Network.api;
    }
}
