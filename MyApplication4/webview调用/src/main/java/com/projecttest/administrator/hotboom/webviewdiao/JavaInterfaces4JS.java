package com.projecttest.administrator.hotboom.webviewdiao;

import com.simplejsjavabridge.lib.annotation.JavaInterface4JS;
import com.simplejsjavabridge.lib.annotation.Param;
import com.simplejsjavabridge.lib.annotation.ParamCallback;
import com.simplejsjavabridge.lib.annotation.ParamResponseStatus;

/**
 * Created by zzt on 2018/4/26.
 */

public class JavaInterfaces4JS {

    public class User{
        @Param("name")
        String name;
        @Param("age")
        String age;

        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public User() {
        }
    }


    public interface IResponseStatusCallback{
        void callbackResponse(@ParamResponseStatus ResponseStatus responseStatus);
    }

    public interface TestCallback extends IResponseStatusCallback{
        void callbackResponse(@ParamResponseStatus ResponseStatus responseStatus,@Param("test") String ss);
    }

    public interface Test2Callback extends IResponseStatusCallback{
        void callbackResponse(@ParamResponseStatus ResponseStatus responseStatus,@Param User user);
    }



    @JavaInterface4JS("test")
    public void test(@Param("test")String ss,@ParamCallback TestCallback TestCallback){
        MainActivity.mResultView.setText("这是点击了test1  ss="+ss);
        TestCallback.callbackResponse(ResponseStatus.FAILED,"这是什么");
    }

    @JavaInterface4JS("test1")
    public void test1(@ParamCallback Test2Callback test2Callback,@Param User user){
        MainActivity.mResultView.setText("native的test1接口被调用");
        test2Callback.callbackResponse(ResponseStatus.OK,new User("王闯","25"));
    }


    @JavaInterface4JS("test2")
    public void test2(@ParamCallback Test2Callback test2Callback,@Param("needConvert = true") User user){
        if (user != null) {
            MainActivity.mResultView.setText("native的test2接口被调用，js传递数据: " + "name=" + user.name + " age=" + user.age);
        }

        MainActivity.setToast("点击了test2");

        MainActivity.setDialog();


        test2Callback.callbackResponse(ResponseStatus.OK, new User("王闯", "25"));

    }

    @JavaInterface4JS("test3")
    public void test3(@ParamCallback Test2Callback test2Callback,@Param("needConvert = true") User user,@Param("test")String ss){
        MainActivity.mResultView.setText("native的test3接口被调用");

        test2Callback.callbackResponse(ResponseStatus.OK,new User("王闯","25"));

    }


    @JavaInterface4JS("test4")
    public void test4(@ParamCallback IResponseStatusCallback iResponseStatusCallback){
        MainActivity.mResultView.setText("这是点击了test4");
        MainActivity.setToast("点击了第四个");
        iResponseStatusCallback.callbackResponse(ResponseStatus.OK);
    }



}
