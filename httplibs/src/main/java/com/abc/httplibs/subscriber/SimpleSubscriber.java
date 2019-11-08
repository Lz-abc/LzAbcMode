package com.abc.httplibs.subscriber;

import com.abc.httplibs.base.IHttpCall;
import com.abc.httplibs.bean.BaseResultData;
import com.abc.httplibs.exception.ApiException;
import com.orhanobut.logger.Logger;

import io.reactivex.observers.DisposableObserver;

/**
 * 不带baseData的订阅
 * @name lz
 * @time 2019/8/26 16:01
 * @param <T> 数据对象 既返回的数据对象
 */
public abstract class SimpleSubscriber<T> extends DisposableObserver<T> implements IHttpCall<T> {
    @Override
    public void onError(Throwable e) {
        new ApiException(e);
        if (e != null) {
            onFail(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onFail(BaseResultData<T> t) {
    }

    @Override
    public void onFail(String msg) {
        Logger.e("error message=" + msg);
    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            onError(null);
            return;
        }
        onSuccess(t);
    }

}
