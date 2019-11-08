package com.abc.httplibs.subscriber;

import com.abc.httplibs.base.IHttpCall;
import com.abc.httplibs.bean.BaseResultData;
import com.orhanobut.logger.Logger;

/**
 * 带BaseResultData 的订阅
 * @name lz
 * @time 2019/8/26 16:01
 * @param <T> 数据对象 既返回的数据对象
 */
public abstract  class SimpleBaseSubscriber<T> extends BaseSubscriber<BaseResultData<T>> implements IHttpCall<T> {

    @Override
    public void onFail(BaseResultData<T> t) {
        Logger.e("error code="+t.getStatusCode()+" error message="+t.getMessage());
        onFail(t.getMessage());
    }

    @Override
    public void onFail(String msg) {
        Logger.e("error message="+msg);
    }


    @Override
    public void onNext(BaseResultData<T> t) {
        if (t == null) {
            onError(null);
            return;
        }
        if ( t.getStatusCode()== 200) {
            onSuccess(t.getResult());
            return;
        }
        onFail(t);
    }

}
