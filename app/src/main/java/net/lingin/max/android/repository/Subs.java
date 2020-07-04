package net.lingin.max.android.repository;

import com.google.gson.JsonParseException;
import net.lingin.max.android.R;
import net.lingin.max.android.repository.entity.result.Result;
import net.lingin.max.android.utils.Loading;
import net.lingin.max.android.utils.Toast;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 观察着对象封装
 */
@SuppressWarnings("all")
public abstract class Subs<T> implements Observer<Result<T>> {

    // 全部显示
    public final static int MODE_SHOW_ALL = 0;
    // 只显示加载框
    public final static int MODE_SHOW_LOADING = 1;
    // 只显示提示信息
    public final static int MODE_SHOW_MSG = 2;
    // 什么都不显示
    public final static int MODE_SHOW_NOTHING = 3;

    // 提示信息模式
    private int mode;

    /**
     * 构造方法,默认显示对话框,提示信息
     */
    protected Subs() {
        this.mode = Subs.MODE_SHOW_ALL;
    }

    /**
     * 构造方法
     *
     * @param mode 提示信息模式 {@link Subs#MODE_SHOW_ALL,Subs#MODE_SHOW_LOADING,Subs#MODE_SHOW_MSG,Subs#MODE_SHOW_NOTHING}
     */
    protected Subs(int mode) {
        this.mode = mode;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!d.isDisposed() && (this.mode == Subs.MODE_SHOW_ALL || this.mode == Subs.MODE_SHOW_LOADING)) {
            Loading.show();
        }
    }

    @Override
    public void onNext(Result<T> data) {
        if (this.mode == Subs.MODE_SHOW_ALL || this.mode == Subs.MODE_SHOW_LOADING) {
            Loading.hide();
        }
        if (data.getCode() == 200) {
            this.onSuccess(data.getData());
        } else {
            if (this.mode == Subs.MODE_SHOW_ALL || this.mode == Subs.MODE_SHOW_MSG) {
                Toast.show(data.getMsg());
            }
            this.onFail();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (this.mode == Subs.MODE_SHOW_ALL || this.mode == Subs.MODE_SHOW_LOADING) {
            Loading.hide();
        }
        if (this.mode == Subs.MODE_SHOW_ALL || this.mode == Subs.MODE_SHOW_MSG) {
            if (e instanceof HttpException) {
                Toast.show(R.string.toast_error_http);
            } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
                Toast.show(R.string.toast_error_connect);
            } else if (e instanceof InterruptedIOException) {
                Toast.show(R.string.toast_error_time_out);
            } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
                Toast.show(R.string.toast_error_data);
            } else {
                Toast.show(R.string.toast_error_unknown);
            }
        }
        this.onFail();
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功
     *
     * @param data 返回数据
     */
    public abstract void onSuccess(T data);

    /**
     * 请求失败,需要时重写
     */
    public void onFail() {

    }
}
