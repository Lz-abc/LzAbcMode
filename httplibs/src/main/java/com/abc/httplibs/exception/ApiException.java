package com.abc.httplibs.exception;

import com.abc.httplibs.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

/**
 * @name lz
 * @time 2019/8/26 17:20
 */
public class ApiException {

    public ApiException(Throwable e){
        if (e != null) {
            //统一处理HttpException
            if (e instanceof HttpException) {
                try {
                    switch (((HttpException) e).code()) {
                        case 404:
                            ToastUtils.showToast("您访问资源不存在，请稍后再尝试。");
                            break;
                        case 500:
                            ToastUtils.showToast("服务器开小差了，请稍后再尝试。");
                            break;
                        default:
                            ToastUtils.showToast("未知错误，请尝试更新APP后再尝试。");
                            break;
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else if (e instanceof TimeoutException) {
                ToastUtils.showToast("网络连接超时，请稍后再尝试。");
            } else {
                //其他异常
                e.printStackTrace();
            }
        } else {
            Logger.e("服务器返回数据为null");
        }
    }
}
