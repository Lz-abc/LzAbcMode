package com.abc.httplibs.utils;



import com.abc.httplibs.HttpConfig;
import com.abc.httplibs.base.BaseApiService;
import com.abc.httplibs.gson.GsonAdapter;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @name lz
 * @time 2019/3/13 14:28
 */
public class HttpUtils<R> {

    private final long cacheLength = 1024 * 1024 * 100;
    private final long CONNECT_TIMEOUT=60;
    private final long WRITE_TIMEOUT=60*5;
    private final long READ_TIMEOUT=60*5;
    public Retrofit retrofit;
    public R apiService;
    public static HttpUtils api;

    private HttpUtils(){
        //日志log
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        File cacheFile = new File(HttpConfig.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, cacheLength); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)//增加日志Log打印
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()))
                .baseUrl(HttpConfig.sHost)
                .build();

        apiService = (R) retrofit.create(HttpConfig.getApiService());
    }

    /**
     * 获取ApiService
     * @return
     */
    public static<R extends BaseApiService> R getDefault(){
        if(api == null)
            api = new HttpUtils();
        return (R) api.apiService;
    }


    public static <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
