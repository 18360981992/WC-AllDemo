package com.dabao.retrofitmvpdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dabao.retrofitmvpdemo.api.APIs;
import com.dabao.retrofitmvpdemo.appliction.DashApplication;
import com.dabao.retrofitmvpdemo.base.BaseMVPActivity;
import com.dabao.retrofitmvpdemo.presenter.MyPresenter;
import com.dabao.retrofitmvpdemo.retrofit.BaseServer;
import com.dabao.retrofitmvpdemo.retrofit.MyInterfaces;
import com.dabao.retrofitmvpdemo.util.ImageUtils;
import com.dabao.retrofitmvpdemo.util.LogUtils;
import com.dabao.retrofitmvpdemo.util.MyUtils;
import com.dabao.retrofitmvpdemo.view.ForbidClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.dabao.retrofitmvpdemo.retrofit.RetrofitFacety.apiService;
import static com.dabao.retrofitmvpdemo.util.ImageUtils.getPhotos;

public class MainActivity extends BaseMVPActivity<MainActivity, MyPresenter<MainActivity>>  {

    private Button btn;
    private TextView tv;
    private Button btup;

    @Override
    public MyPresenter<MainActivity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    List<MultipartBody.Part> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        // 自定义点击事件  防止用户狂点  二逼测试的暴力测试
//        btn.setOnClickListener(new ForbidClickListener() {
//            @Override
//            public void forbidClick(View v) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("BSID", "wFLZPV9qAS9Gwzx6RnfB3QzIMKb-kQ0sN0uc0OtTTuoszWlnO6FF6_Y7Q8GBpd9lyJx1lqFu6QVurCjTCemOZA");
//                myPresenter.postPreContent(APIs.MeiTuan, map, new MyInterfaces() {
//                    @Override
//                    public void chenggong(String json) {
//                        tv.setText("json===" + json);
//                    }
//
//                    @Override
//                    public void shibai(String ss) {
//                        MyUtils.setToast(ss);
//                    }
//                });
//            }
//        });

        btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                myPresenter.getPreContent(APIs.wxnew, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        tv.setText("json===" + json);
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast(ss);
                    }
                });
            }
        });

        // 上传多文件
        btup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, DashApplication.WRITE_EXTERNAL_STORAGE);
                }else{
                    // 启动相册
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 1000);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==DashApplication.WRITE_EXTERNAL_STORAGE){
            if(grantResults[0]!=-1){
                // 启动相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1000);
            }else{
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 正面照的请求结果  相册
        if(requestCode==1000&resultCode==RESULT_OK){
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
            // 将相册中获取到的照片重新选择路径存储 这里是用于适配机型的
            File photos1 = getPhotos(fileUri);
            LogUtils.i("jba","photos=="+ photos1.toString());
            tv.setText(photos1.toString());

            photos.clear();
            setPhotos(photos1,"identityFrontPic");
            setPhotos(photos1,"identityBackPic");
            setPhotos(photos1,"headerPic");
            setPhotos(photos1,"businessPic");
            Observable<String> responseBodyCall = apiService.uploadFile(photos);
            responseBodyCall.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseServer() {
                        @Override
                        public void onSuccess(String json) {
                            tv.setText(json);
                        }

                        @Override
                        public void onErroy(String ss) {
                            tv.setText(ss);
                        }
                    });
        }
    }

    // 设置file类型
    private void setPhotos(File file,String key) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        photos.add(body);
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btup = (Button) findViewById(R.id.btup);
        tv = (TextView) findViewById(R.id.tv);
    }





}
