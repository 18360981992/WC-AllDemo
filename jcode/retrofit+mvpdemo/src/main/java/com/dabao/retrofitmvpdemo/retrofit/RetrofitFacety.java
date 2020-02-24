package com.dabao.retrofitmvpdemo.retrofit;

import android.support.annotation.NonNull;

import com.dabao.retrofitmvpdemo.api.APIs;
import com.dabao.retrofitmvpdemo.appliction.DashApplication;
import com.dabao.retrofitmvpdemo.util.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mypc on 2018/1/5.
 *
 * http://www.yulin520.com/
 */

public class RetrofitFacety {

    //使全局就一个OKHttpClient对象
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new ApplictionInterceptor())  // 添加公共参数拦截器
            .addInterceptor(DashApplication.loggingInterceptor)  // 添加retrofit日志拦截器
            .cookieJar(new CookieManger(DashApplication.getAppContext()))  //传递cookie  保持后台的sessionid
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build();

    //使全局就一个Retrofit对象,设置基础Url
    public static SearchApi apiService = new Retrofit.Builder()
            .baseUrl(APIs.debugApi)
            //使我们能高度自定义转化器
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            //把 以前的 call 转化成 Observable,这是Retrofit与RxJava结合使用的关键
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SearchApi.class);


    /**
     *  retrofit的get请求
     * @param url
     * @return
     */
    public static Observable<String> get(final String url) {

        return apiService.get(url)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
//                        LogUtils.i("wc",url+"===get===这是处理缓存本地操作==="+s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *  json格式入参 retrofit的post请求
     * @param url
     * @param map
     * @return
     *
     *  取消请求
     */
    public static Observable<String> postFlyRoute(final String url, Map<String,Object> map){

        String route = new Gson().toJson(map);
        //3、将json对象转化为json字符串
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);

        return apiService.postFlyRoute(url,body)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
//                        LogUtils.i("wc",url+"==post===这是个什么==="+s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * retrofit的普通post请求
     * @param url
     * @param map
     * @return
     *
     *  取消请求
     */
    public static Observable<String> post(final String url, Map<String,Object> map){

        return apiService.post(url,map)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
//                        LogUtils.i("wc",url+"==post===这是个什么==="+s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 获取图形验证码
     * @param url
     * @return
     */
    public static Observable<ResponseBody> get_img(final String url){

        return apiService.downloadPicFromNet_Get(url)
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody s) throws Exception {
//                        LogUtils.i("wc",url+"==get===获取图形验证码==="+s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //参数拦截器
    public static class ApplictionInterceptor implements Interceptor {

     @Override
     public Response intercept(Chain chain) throws IOException {
         //获取到请求
         Request original = chain.request();
         //获取请求的方式
         String method = original.method();
         //获取请求的路径
         String oldUrl = original.url().toString();
         LogUtils.i("jba","拦截器==="+original.url()+"---"+original.method()+"--"+original.header("User-agent"));

         if ("POST".equals(method)){
             Request build = original.newBuilder()
                     .header("token","wx_login_24_f8695dca3874362662824a6f41d33c2bba84d3025a910a4c54bd8cdeeabd5c5f")
                     .header("os", "Android")
                     .header("platform", "App")
                     .header("version", "2.0.0")
                     .header("device","0716f5e91daad18b")
                     .method(method, original.body())
                     .url(oldUrl)
                     .build();

             Response response = chain.proceed(build);

             return response;
         }

         return null;
     }
 }

}
