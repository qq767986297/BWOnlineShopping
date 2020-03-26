package com.bawei.bwonlineshopping.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Time: 2020/3/25
 * Author: 王冠华
 * Description:
 */
public class RetroiftManger {
   private static final String  BASEPATH="http://mobile.bwstudent.com/small/";
    private OkHttpClient build;
    private Apis apis;

    private RetroiftManger() {
        initRetrofit();
    }
    //单例模式
    private static class SingleInstance{
        private static final RetroiftManger INSTANCE=new RetroiftManger();
    }
    public static RetroiftManger getInstance(){
        return SingleInstance.INSTANCE;
    }
    //拦截器
    public void initRetrofit(){
        //创建日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);
        build = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEPATH)
                .client(this.build)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis = retrofit.create(Apis.class);
    }
    public Apis getApis(){
        return apis;
    }

}
