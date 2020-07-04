package net.lingin.max.android.repository;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 线程调度封装
 */
@SuppressWarnings("unused")
public class Scheduler<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 应用线程调度并绑定生命周期管理
     *
     * @param <T> 泛型
     * @return 返回转换操作
     */
    public static <T> ObservableTransformer<T, T> apply() {
        return new Scheduler<>();
    }
}
