package com.ifeng_tech.spotmall.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

public class MainActivity extends AppCompatActivity {
    final UMWeb web = new UMWeb("https://mobile.umeng.com/"); //切记切记 这里分享的链接必须是http开头
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new ShareAction(MainActivity.this)
//                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
//                .setShareboardclickCallback(new ShareBoardlistener() {
//                    @Override
//                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                        if (share_media == SHARE_MEDIA.QQ) {
//                            Log.i("jba","点击QQ");
//                            new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
//                            Log.i("jba","点击微信");
//                            new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        } else if (share_media == SHARE_MEDIA.QZONE) {
//                            new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QZONE)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
//                            new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        }
//                    }
//                }).open();
    }

    // qq 分享链接
    public void qq(View view) {
        new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    // 微信 分享链接
    public void weiXin(View view) {

        new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    // 朋友圈  分享链接
    public void weixinCircle(View view) {
        new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    // 微信收藏
    public void weixinShouCang(View view){

        new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    // 新浪
    public void sina(View view) {
        new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.SINA)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    // qq空间
    public void Qzone(View view) {
        new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
