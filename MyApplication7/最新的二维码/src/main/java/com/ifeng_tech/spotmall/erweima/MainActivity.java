package com.ifeng_tech.spotmall.erweima;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jwsd.libzxing.OnQRCodeListener;
import com.jwsd.libzxing.QRCodeManager;

public class MainActivity extends AppCompatActivity {
    private ImageView qrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrCode = (ImageView) findViewById(R.id.iv_qrcode);
    }

    public void onCreateQR(View view) {
        createQRCode(false);
    }


    private void createQRCode(boolean isLogo) {
//        String content = "xg/13Hgs+93teLxDcIR0qweE8AumblRxK/8M3IBaomq+WfofKVq24mcmpJ59wHmbcxgf6DG+IPs=";
        String content="xg/13Hgs+93teLxDcIR0qweE8AumblRxW0PlMehCGbSyzoc+puAUkZkXQWVl30nRD7QpqkCNaRA7\n" +
                "                                                                     h8p5SgAX+LYSJGEtXyOr/1xykKGmkpsl+4QlGtN6mtFAtcrx+w+4LQmYZoPcTNZRjEmObWDnGZai\n" +
                "                                                                     zm9lSDBpOGBlhMviHwhZ7k+MqR87+g2ZKlcvNsKe/mIb7oO1Z+UsQQNdKo7X3Id/Uy1h5Ug0";
        if (TextUtils.isEmpty(content)) {
            content = "http://www.gwell.cc/";
        }

        /**
         * bitmap（即最后一个参数）设置为空时表示不添加logo
         */
        Bitmap qrCodeBit;
        if (isLogo) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            qrCodeBit = QRCodeManager.getInstance().createQRCode(content, 300, 300, bitmap);
        } else {
            qrCodeBit = QRCodeManager.getInstance().createQRCode(content, 300, 300);
        }
        qrCode.setImageBitmap(qrCodeBit);
    }

    public void onScanQR(View view) {
        QRCodeManager.getInstance()
                .with(this)
                .setReqeustType(0)
//                .setRequestCode(1001)
                .scanningQRCode(new OnQRCodeListener() {
                    @Override
                    public void onCompleted(String result) {
                        Toast.makeText(MainActivity.this,""+result,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable errorMsg) {
                        Toast.makeText(MainActivity.this,""+errorMsg,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this,"(取消)扫描任务取消了",Toast.LENGTH_SHORT).show();
                    }

                    /**
                     * 当点击手动添加时回调
                     *
                     * @param requestCode
                     * @param resultCode
                     * @param data
                     */
                    @Override
                    public void onManual(int requestCode, int resultCode, Intent data) {
                        Log.i("jiba","点击了手动添加了");
                    }


                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //注册onActivityResult
        QRCodeManager.getInstance().with(this).onActivityResult(requestCode, resultCode, data);
    }
}
