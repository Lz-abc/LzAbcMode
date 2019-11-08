package com.abc.httplibs.subscriber;


import com.abc.httplibs.exception.ApiException;

import io.reactivex.observers.DisposableObserver;


/**
 * @name lz
 * @time 2019/8/26 16:01
 */
public abstract   class BaseSubscriber<BaseRequestData>  extends DisposableObserver<BaseRequestData> {
    @Override
    public void onError(Throwable e) {
        new ApiException(e);
    }

    @Override
    public void onComplete() {

    }
}
