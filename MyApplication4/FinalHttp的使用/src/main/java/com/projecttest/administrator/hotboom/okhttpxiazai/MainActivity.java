package com.projecttest.administrator.hotboom.okhttpxiazai;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;

import me.ele.download.FinalHttp;
import me.ele.download.http.AjaxCallBack;
import me.ele.download.http.AjaxParams;
import me.ele.download.http.HttpHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button xia;

    private HttpHandler httpHandler;

    /** 升级接口地址 */
    private String upUrl = "http://wh.zhuaihu.com/mobile/apk/app_az.apk";
    public static final String APK_PATH = Environment.getExternalStorageDirectory() + "/Download/"+"lianxi_apk";

    // 上传头像接口
    private String chuanUrl = "https://www.zhaoapi.cn/file/upload";
    private String uid = "14366";  // uid   废物的id
    private String token = "C0D3765359FA88B0AEA916AFEE019D0D";  // 用户令牌

    File f2 = Environment.getExternalStorageDirectory();
    File coverFile = new File(f2, "nv.png");
    File f1 = Environment.getExternalStorageDirectory();
    File videoFile = new File(f1, "baby.mp4");   // 视屏资源

    private String latitude="39.95";   // 纬度
    private String longitude="116.30";   // 经度

    FinalHttp fh = new FinalHttp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        xia = (Button) findViewById(R.id.xia);

        xia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xia:
                downLoadApk(upUrl);
                break;
        }
    }
    /**
     * 下载apk文件
     */
    protected void downLoadApk(String apkUrl){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("正在下载");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(true);// 设置是否可以通过点击Back键取消
        pd.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        httpHandler.stop();
                        pd.dismiss();
                        finish();
                    }
                });
        pd.show();

        // 调用download方法开始下载
        final File dirPath=new File(APK_PATH);

        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
        File mUpApkFile=new File(dirPath, "app_az.apk");
        // 如果文件存在 先删除 避免报错
        if(mUpApkFile.isFile()){
            mUpApkFile.delete(); // 删除所有文件
        }

        //true:断点续传 false:不断点续传（全新下载）
        httpHandler=fh.download(apkUrl, mUpApkFile.getAbsolutePath(),true,new AjaxCallBack<File>()
        {
            @Override
            public void onSuccess(File t){
                installAPK(pd, t);
                super.onSuccess(t);
            }

            @Override
            public void onLoading(long count, long current)
            {
                Log.i("jiba", "onLoading: 走了。。。");
                double cou = count / 100;
                double curr = current / cou;

                pd.setMax(100);
                pd.setProgress((int) (curr));
                super.onLoading(100, (long) curr);
            }

        });
    }


    //安装apk
    private void installAPK(final ProgressDialog pd, File t) {
        pd.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(t), "application/vnd.android.package-archive");
        startActivity(intent);
        finish();
    }


    // 单文件上传
    public void chuan(View view) {
        AjaxParams params = new AjaxParams();
        try {
            params.put("uid", uid);
            params.put("token", token);
            params.put("file", coverFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fh.post(chuanUrl, params, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Log.i("jiba","onSuccess===="+t);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Log.i("jiba","onFailure===="+strMsg);
            }
        });



    }

    // 多文件上传
    public void duochuan(View view) {

        String duoUrl ="https://www.zhaoapi.cn/quarter/publishVideo?source=android&appVersion=101";
        AjaxParams params = new AjaxParams();
        try {
            params.put("uid", uid);
            params.put("token", token);
            params.put("videoFile", videoFile);
            params.put("coverFile", coverFile);
            params.put("videoDesc", "这个框架太牛逼。。。");
            params.put("latitude", latitude);
            params.put("longitude", longitude);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fh.post(duoUrl, params, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Log.i("jiba","duochuan==onSuccess=="+s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Log.i("jiba","duochuan==onFailure=="+strMsg);
            }
        });


    }
}
