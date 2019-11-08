package com.abc.httplibs;

import android.content.Context;

import androidx.annotation.Nullable;

import com.abc.httplibs.base.BaseApiService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.lang.ref.WeakReference;

/**
 * http  属性配置 使用前必须配置init
 * 建议在 Application 中进行配置
 * @name lz
 * @time 2019/7/10 15:37
 */
public class HttpConfig {

    public static boolean sIsShowLog = true;

    private static WeakReference<Context> mContext;

    private static HttpConfig sConfig=new HttpConfig();

    private static Class<?> sApiService;

    public static String sHost="http://zhiboapitest.safetree.com.cn/bestong/";

    private HttpConfig(){
        initLog();
    }

    public static HttpConfig init(Context context,String host,boolean isShowHttpLog,Class<? extends BaseApiService> apiService){
        mContext=new WeakReference(context);
        sApiService=apiService;
        sHost=host;
        sIsShowLog=isShowHttpLog;
        return sConfig;
    }

    /**
     * 初始化Log输出配置
     */
    private void initLog() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("PTask")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return HttpConfig.isShowLog();
            }
        });
    }

    public static Context getContext(){
        return mContext.get();
    }

    public static Class<?> getApiService() {
        return sApiService;
    }

    public static boolean isShowLog() {
        return sIsShowLog;
    }
}
