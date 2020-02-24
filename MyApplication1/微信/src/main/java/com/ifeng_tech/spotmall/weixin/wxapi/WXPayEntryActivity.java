package com.ifeng_tech.spotmall.weixin.wxapi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.ifeng_tech.spotmall.weixin.MainActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	private IWXAPI api;
	private View inflate;
	private Dialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, "wx86f662ab6c60696c");
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.e("===================", "onPayFinish, errCode = " + resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if(resp.errCode==0){
//				ToastUtil.showShort(WXPayEntryActivity.this,"支付成功");
//				Intent in = new Intent(WXPayEntryActivity.this, ShopOrderActivity.class);
//				startActivity(in);
				Log.i("jiba","支付成功");
				getJump();
				WXPayEntryActivity.this.finish();
			}else{
				Log.i("jiba","支付失败");
//				ToastUtil.showShort(WXPayEntryActivity.this,"支付失败，请重试");
			}
			finish();
			/*AlertDialog.Builder builder = new AlertDialog.Builder(WXPayEntryActivity.this);
			builder.setTitle("tip");
			builder.setMessage(String.valueOf(resp.errCode));
			builder.show();*/
		}
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	private void getJump() {
		SharedPreferences sp = getSharedPreferences("jump", Context.MODE_PRIVATE);
		int ckey = sp.getInt("jump", 1);
		//提交订单微信成功之后的跳转
		if(ckey==1){
//			Intent record = new Intent(WXPayEntryActivity.this, WxBackActivity.class);
//			startActivity(record);
		}
		//预存款充值微信成功之后的跳转
		if(ckey==2){
//			Intent record = new Intent(WXPayEntryActivity.this,NewYCKDetailActivity.class);
//			startActivity(record);
		}
		//商城订单微信成功之后的跳转
		if(ckey==3){

		}
		//购买会员微信成功之后的跳转
		if(ckey==4){
			Intent in = new Intent(WXPayEntryActivity.this, MainActivity.class);
			in.putExtra("goods_detail", 2);
			startActivity(in);
			finish();
		}
		//购买会员微信成功之后的跳转
		if(ckey==5){
			Intent in = new Intent(WXPayEntryActivity.this, MainActivity.class);
			in.putExtra("goods_detail", 2);
			startActivity(in);
			finish();
		}
	}
}