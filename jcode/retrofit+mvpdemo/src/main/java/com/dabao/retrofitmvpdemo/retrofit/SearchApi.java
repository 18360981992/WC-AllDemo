package com.dabao.retrofitmvpdemo.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 *
 * Created by liqy on 2018/1/28.
 *
 */

public interface SearchApi {

    /**
     *  普通get请求
     * @param url
     * @return
     */
    @GET
    Observable<String> get(@Url String url);

    /**
     *  普通post请求
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> map);

    /**
     *  retrofit+Rxjava的图形获取与转换  get获取图片
     * @param fileUrl
     * @return
     */
    @GET
    Observable<ResponseBody> downloadPicFromNet_Get(@Url String fileUrl);


    /**
     * retrofit以json形式入参 的 post请求
     * @param url
     * @param route
     * @return
     */
//    这里可以用注解的形式添加请求头
//    @Headers({
//            "Content-Type:application/x-www-form-urlencoded",
//            "Referer:http://e.waimai.meituan.com/v2/epassport/entry?BSID=wFLZPV9qAS9Gwzx6RnfB3QzIMKb-kQ0sN0uc0OtTTuoszWlnO6FF6_Y7Q8GBpd9lyJx1lqFu6QVurCjTCemOZA",
//            "X-Requested-With:XMLHttpRequest",
//            "User-Agent:MeituanWaimai/ai/3.0.1.0/32 Win Windows/6.3",
//    })
    @POST
    Observable<String> postFlyRoute(@Url String url,@Body RequestBody route);//传入的参数为RequestBody


    /**
     * 多文件上传
     * @param photos
     * @return
     *
     * @Part() 可以添加普通参数 没有就不加
     */
    @Multipart
    @POST("qinglong/shop/picture/approve")
    Observable<String> uploadFile(@Part() List<MultipartBody.Part> photos);

}
