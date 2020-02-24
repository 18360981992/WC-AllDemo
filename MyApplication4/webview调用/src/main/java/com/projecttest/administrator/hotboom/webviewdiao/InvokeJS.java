package com.projecttest.administrator.hotboom.webviewdiao;

import com.simplejsjavabridge.lib.IJavaCallback2JS;
import com.simplejsjavabridge.lib.annotation.InvokeJSInterface;
import com.simplejsjavabridge.lib.annotation.Param;
import com.simplejsjavabridge.lib.annotation.ParamCallback;

/**
 * Created by zzt on 2018/4/26.
 */

public interface InvokeJS {

    public class City{
        @Param("cityName")
        String cityName;

        @Param("cityProvince")
        String cityProvince;

        public City() {
        }

        public City(String cityName, String cityProvince) {
            this.cityName = cityName;
            this.cityProvince = cityProvince;
        }
    }

    /**
     *
     * @param massge  普通参数
     * @param iJavaCallback2JS
     */
    @InvokeJSInterface("exam")   // java向js传递普通的参数
    void exam(@Param("test") String massge,@ParamCallback IJavaCallback2JS iJavaCallback2JS );

    /**
     *
     * @param city  对象
     * @param iJavaCallback2JS
     */
    @InvokeJSInterface("exam1")  // java向js传递对象
    void exam1(@Param City city,@ParamCallback IJavaCallback2JS iJavaCallback2JS );

    /**
     *
     * @param city  对象
     * @param contry  普通参数
     * @param iJavaCallback2JS
     */
    @InvokeJSInterface("exam2")  // java向js传递对象 并另外携带参数
    void exam2(@Param City city,@Param("contry") String contry,@ParamCallback IJavaCallback2JS iJavaCallback2JS);

    @InvokeJSInterface("exam3")  // java向js传递参数 返回默认值
    void exam3(@Param City city,@Param("contry") String contry,@ParamCallback IJavaCallback2JS iJavaCallback2JS);

    @InvokeJSInterface("exam4")  // java调用js的无参方法  直接返回状态信息
    void exam4(@ParamCallback IJavaCallback2JS iJavaCallback2JS);

    @InvokeJSInterface("exam5")  // java向js传递多个对象
    void exam5(@Param(value = "city1") City city1,@Param(value = "city2") City city2,@ParamCallback IJavaCallback2JS iJavaCallback2JS);
}
