package com.abc.httplibs.base;



import com.abc.httplibs.bean.BaseResultData;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface BaseApiService {
    @Multipart
    @POST("observe/file/upload")
    Observable<BaseResultData<String>> up(
            @Part() MultipartBody.Part parts);

    @GET
    Observable<BaseResultData<String>> down(@Url String url);

    // 公开配置   2.0   分享链接升级
    @GET("api2/v1/other/publicsetting")
    Observable<BaseResultData> getPublicSetting();
}
