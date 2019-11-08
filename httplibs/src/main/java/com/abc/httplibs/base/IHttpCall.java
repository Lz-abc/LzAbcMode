package com.abc.httplibs.base;

import com.abc.httplibs.bean.BaseResultData;

/**
 * @name lz
 * @time 2019/8/26 16:04
 */
public interface IHttpCall<T> {
    void onFail(String msg);
    void onFail(BaseResultData<T> t);
    void onSuccess(T t);
}
