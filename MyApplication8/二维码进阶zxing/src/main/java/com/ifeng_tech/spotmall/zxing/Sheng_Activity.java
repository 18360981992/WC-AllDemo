package com.ifeng_tech.spotmall.zxing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ifeng_tech.spotmall.zxing.utils.ImageUtils;
import com.jwsd.libzxing.QRCodeManager;

import java.io.File;

public class Sheng_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button putong;
    private Button daitu;
    private ImageView sheng_img;
    private Button sheng_bao;

    // 字符串可是任意的字符串  一般是url 或者是 json串 当然都是加密过的 这里就不写加密了
    // https://blog.csdn.net/u013252110/article/details/54089705 这是加密的一些心得  可有ios 或 web端通用交互
    String text="二维码练习";
    public Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        img = QRCodeManager.getInstance().createQRCode(text, 300, 300);

        sheng_img.setImageBitmap(img);
    }

    private void initView() {
        putong = (Button) findViewById(R.id.putong);
        daitu = (Button) findViewById(R.id.daitu);
        sheng_img = (ImageView) findViewById(R.id.sheng_img);
        sheng_bao = (Button) findViewById(R.id.sheng_bao);

        putong.setOnClickListener(this);
        daitu.setOnClickListener(this);
        sheng_bao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.putong:  // 不带图的二维码
                img = QRCodeManager.getInstance().createQRCode(text, 300, 300);

                sheng_img.setImageBitmap(img);
                break;
            case R.id.daitu: // 带图的二维码
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cc);

                // 这里的300 只是单纯的设置二维码生成后的图片宽高，并且该方法已经对图片进行过二次采样了，可以直接装入控件
                img = QRCodeManager.getInstance().createQRCode(text, 300, 300, bitmap);

                sheng_img.setImageBitmap(img);
                break;
            case R.id.sheng_bao: // 保存本地  需要读写权限  一定要做权限适配
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Sheng_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 这里的100是权限请求协议码，可以任意更换
                    ActivityCompat.requestPermissions(Sheng_Activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                }else{
                    File photos = ImageUtils.getPhotos(img);
                    Toast.makeText(Sheng_Activity.this,"图片以保存至"+photos.toString(),Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    // 权限请求的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){ // 一定要和权限请求码一致
            if(grantResults[0]!=-1){
                File photos = ImageUtils.getPhotos(img);
                Toast.makeText(Sheng_Activity.this,"图片以保存至"+photos.toString(),Toast.LENGTH_SHORT).show();
            }else{
                return;
            }
        }
    }
}
