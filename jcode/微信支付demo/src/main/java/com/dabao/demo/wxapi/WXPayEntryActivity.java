package com.dabao.demo.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.dabao.demo.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    // APP_ID 替换为你的应用从官方网站申请到的合法appId   wx531348a74bfcb573
    public static final String APP_ID = "wx531348a74bfcb573";
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

//        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID);

        // 将该app注册到微信
        api.registerApp(APP_ID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);

        //如果分享的时候，该已经开启，那么微信开始这个activity时，会调用onNewIntent，所以这里要处理微信的返回结果
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        int result = 0;

        Log.i("jba","resp.openId=="+resp.openId+",resp.transaction=="+resp.transaction+",resp.errStr=="+resp.errStr);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
            builder.show();

            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    result = R.string.errcode_success; // 成功
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    result = R.string.errcode_cancel;  // 取消
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    result = R.string.errcode_deny;  //发送被拒绝
                    break;

                default:
                    result = R.string.errcode_unknown;  //发送返回
                    break;
            }
        }



        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        finish();
    }
}
