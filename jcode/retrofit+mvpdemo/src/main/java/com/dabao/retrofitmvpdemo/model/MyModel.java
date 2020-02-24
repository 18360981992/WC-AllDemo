package com.dabao.retrofitmvpdemo.model;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dabao.retrofitmvpdemo.retrofit.BaseServer;
import com.dabao.retrofitmvpdemo.retrofit.MyInterfaces;
import com.dabao.retrofitmvpdemo.retrofit.MyJieKou;
import com.dabao.retrofitmvpdemo.retrofit.RetrofitFacety;

import java.io.InputStream;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by mypc on 2018/1/10.
 *
 * model层里，我做了retrofit+RxJava的封装处理，这里就不再展示代码了，向看代码可以到我的博客去看！
 *
 * 地址：http://blog.csdn.net/weixin_40430041/article/details/78988137
 */

public class MyModel {


    /**
     * model中的普通get请求方式
     * @param url
     * @param myInterfaces
     */
    public void getModContent(final String url, final MyInterfaces myInterfaces){
        RetrofitFacety.get(url)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        myInterfaces.chenggong(json);
                    }
                    @Override
                    public void onErroy(String ss) {
                        myInterfaces.shibai(ss);
                    }
                });
    }

    /**
     * model中的普通post请求
     * @param url
     * @param map
     * @param myInterfaces
     */
    public void postModContent(final String url, final Map<String,Object> map, final MyInterfaces myInterfaces){

        RetrofitFacety.post(url,map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        myInterfaces.chenggong(json);
                    }

                    @Override
                    public void onErroy(String ss) {
                        myInterfaces.shibai(ss);
                    }
                });
    }


    /**
     * model中的postFlyRoute请求 添加头参数 且已json格式入参
     * @param url
     * @param map
     * @param myInterfaces
     */
    public void postFlyRouteModContent(final String url, final Map<String,Object> map, final MyInterfaces myInterfaces){

        RetrofitFacety.postFlyRoute(url,map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        myInterfaces.chenggong(json);
                    }

                    @Override
                    public void onErroy(String ss) {
                        myInterfaces.shibai(ss);
                    }
                });
    }


    /**
     *  M 层的获取图形验证码
     * @param url
     * @param myJieKou
     */
    public void getMod_TuXingYanZheng(final String url,final MyJieKou myJieKou){
        RetrofitFacety.get_img(url)
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        myJieKou.chenggong(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        myJieKou.shibai("请求失败！");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
