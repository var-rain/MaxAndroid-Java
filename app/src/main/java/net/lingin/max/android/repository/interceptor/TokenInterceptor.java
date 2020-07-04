package net.lingin.max.android.repository.interceptor;

import net.lingin.max.android.setting.Settings;
import net.lingin.max.android.utils.Prefer;
import net.lingin.max.android.utils.Text;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by: var_rain.
 * Created date: 2020/1/16.
 * Description: Token拦截器
 */
public class TokenInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String token = Prefer.get(Settings.APP_USER_TOKEN, null);
        if (Text.empty(token)) {
            return chain.proceed(chain.request());
        } else {
            // 标准JWT鉴权
            Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build();
            return chain.proceed(request);
        }
    }
}
