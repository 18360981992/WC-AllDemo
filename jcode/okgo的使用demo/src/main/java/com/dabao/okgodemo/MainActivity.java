package com.dabao.okgodemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import java.io.File;

import static com.dabao.okgodemo.ImageUtils.getPhotos;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void btn(View view) {

        // 6.0权限适配
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        } else {
            // 启动相册
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults[0] != -1) {
                // 启动相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1000);
            } else {
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 正面照的请求结果  相册
        if (requestCode == 1000 & resultCode == RESULT_OK) {
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
            // 将相册中获取到的照片重新选择路径存储 这里是用于适配机型的
            File photos1 = getPhotos(fileUri);
            tv.setText(photos1.toString());

            PostRequest pr = OkGo.post("http://e.waimai.meituan.com/v2/epassport/logon");
            pr.headers("token", "wx_login_24_f8695dca3874362662824a6f41d33c2bba84d3025a910a4c54bd8cdeeabd5c5f");
            pr.headers("os", "Android");
            pr.headers("platform", "App");
            pr.headers("version", "2.0.0");
            pr.headers("device", "0716f5e91daad18b");
            pr.params("identityFrontPic", photos1);
            pr.params("identityBackPic", photos1);
            pr.params("headerPic", photos1);
            pr.params("businessPic", photos1);

            pr.execute(new Callback() {
                @Override
                public void onStart(Request request) {

                }

                @Override
                public void onSuccess(Response response) {
                    String body = (String) response.body();
                    Log.i("jba", "response==" + body);
                    tv.setText(body);
                }

                @Override
                public void onCacheSuccess(Response response) {

                }

                @Override
                public void onError(Response response) {
                    String body = (String) response.body();
                    tv.setText(body);
                }

                @Override
                public void onFinish() {

                }

                @Override
                public void uploadProgress(Progress progress) {

                }

                @Override
                public void downloadProgress(Progress progress) {

                }

                @Override
                public Object convertResponse(okhttp3.Response response) throws Throwable {
                    return null;
                }
            });
        }
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }
}
