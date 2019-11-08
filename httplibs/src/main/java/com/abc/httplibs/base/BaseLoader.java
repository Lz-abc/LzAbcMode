package com.abc.httplibs.base;

import com.abc.httplibs.utils.HttpUtils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @name lz
 * @time 2019/8/28 9:34
 */
public class BaseLoader<T extends BaseApiService> implements IBaseLoader {

    public T mApiService;

    public BaseLoader() {
        mApiService = HttpUtils.getDefault();
    }

    protected CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    public void disposed() {
        if (mDisposables != null && mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

}
