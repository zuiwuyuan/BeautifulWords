package com.otb.designerassist.http.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 名人名句
 */
public interface AllarticleService {

/*
    // 名人名句-电影台词
    @GET("jingdiantaici")
    Call<ResponseBody> loadAllarticle(@Query("page") String page);

    // 名人名句-小说摘抄
    @GET("zhaichao")
    Call<ResponseBody> loadZhaichao(@Query("page") String page);

    // 名人名句-散文美句
    @GET("sanwen")
    Call<ResponseBody> loadSanwen(@Query("page") String page);

    // 名人名句-散文美句
    @GET("dongmantaici")
    Call<ResponseBody> loadDongmantaici(@Query("page") String page);

    // 名人名句-连续剧台词
    @GET("guwen")
    Call<ResponseBody> loadgGuwen(@Query("page") String page);
*/

    @GET("{type}")
    Call<ResponseBody> loadAllarticle(@Path("type") String type, @Query("page") String page);

    @GET("{type}")
    Call<ResponseBody> loadOrignal(@Path("type") String type, @Query("page") String page);


    @GET("{type}")
    Call<ResponseBody> loadAlbums(@Path("type") String type, @Query("page") String page);

    // 美图美句子
    @GET
    Call<ResponseBody> loadMeiju(@Url String url);

    // 手写美句子
    @GET("{type}")
    Call<ResponseBody> loadMeiju(@Path("type") String type, @Query("page") String page);
}
