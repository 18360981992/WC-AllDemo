package com.dash.a09_alipay;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.dash.a09_alipay.alipay.AlipayUtil;
import com.dash.a09_alipay.alipay.PayResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * -----------------https://www.cnblogs.com/whoislcj/p/5885006.html
 *
 *
 *
 * 1.组装符合支付宝规则的订单信息
 *      1)拼接原始的订单信息
 *      2)使用私钥进行签名
 *      3)utf-8编码
 *      4)符合支付宝参数规范的订单信息
 *
 * 2.异步调用支付宝支付接口:PayTask alipay = new PayTask(MainActivity.this);
                         // 调用支付接口，获取支付结果
                         String result = alipay.pay(payInfo, true);

 * 3.同步返回的结果:在我们这个代码中 认为9000代表支付成功
 *      实际上需要把返回的结果传给公司服务器,验证签名等操作,,,,再给客户端进行返回支付的结果
 *
 */
public class MainActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    //使用返回的支付结果字符串构建一个支付结果对象
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&docType=1) 建议商户依赖异步通知
                     */
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    //----------app端拿到支付宝同步返回的结果,需要发送给服务器端,服务器端经过验证签名和解析支付结果,然后给app端返回最终支付的结果

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {

                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。



                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(MainActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }

                    /**
                     * 不管订单支付成功或者失败 订单是已经生成了 如果成功 跳转订单列表的已支付,,,如果失败跳转的是订单列表的待支付
                     */

                    break;
                }
                default:
                    break;
            }
        }

    };


    /**
     * 真是开发过程中 这些参数是都不能出现在代码中的 尤其是私钥 放在服务器端
     *
     *
     */
    private final String PARTNER = "2088221871911835"; //Pid
    private final String SELLER = "zhuangshiyigou@163.com";// 商户收款账号
    // 商户私钥，pkcs8格式
    private final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALmhW0c" +
            "+ZO7sB3utNmkQu5hkpqnw+nAPLCdBoPEw+E+7qcOZBdXxwZsG05yr2BYp5j0MTE0FpKRu" +
            "+uNTVTAX/MzhcInAColtnWkF1R8rsIdlLdleRoMcM3Lo4XesctdsyxLeummrFLKQfASMqS" +
            "64kkTUoEFGb3tlYZs7iSFgpM1pAgMBAAECgYAGm6zhK2JycuqNR4xBTzwuX57jO9" +
            "XeeVvMBfURwPmF9RtFAESJ6jJHL4YG9MMbfuBYWgC5WTMUO3Mo9oV40dHI9dPwANL5a" +
            "PeKEIUawoQyuCyY/js84fOY3+TbEnXym8G6+Zxm+bGKQn3ZZqlSgR3CHk5f/CceoXvPWDLwl//" +
            "RtQJBANzDaQTeckTUEtxVyZ9vM26Sv+TKULvqf8OHdrGm7WOWY6/CbChrxMKHxkdrNwZPNIhoz" +
            "NfTaOmnJaPqeMKo8SMCQQDXQmEPHj0h4Qjn3D6n6WiNOvUsNbzVpLP/TgwHFYkRLcz+GPRkbXXdvk" +
            "SUKxNo7vwZr8vwTIquYA+K3CFTpr4DAkEAu3Ox2NCJdqgc27p8WUSzB1DUYBDqPKYBlqWPw4laSRWJz" +
            "9Pmwuu/Ru7DDiGbt1/J24ohZaG9k6i57VVK9P8+wQJBAIgGtFrfWvY7xGrwbM+i2aTVqvTDCI9hQzWEV" +
            "mlrnHA0pyOzFU0ZNrBneeK/zcYzry90PcWeOMy0e13eeVjpN40CQQCMBjVBeTdQ9afgGnBR6glIWrCtq" +
            "TBAsUIr3gvNZYWdaznr0FmG2pjLwDLUsx0SUCpcrTQxhWu16HDEyQuCm7Ar";
    private TextView timetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTime(); // 识别时间
    }

    private void setTime() {
        timetext = (TextView) findViewById(R.id.time);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);

        format="2018-04-01 15:23:20";
        if(DataUtils.isToday(format)){
            Date date1 = DataUtils.toDate(format);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
            String format1 = simpleDateFormat1.format(date1);
            timetext.setText(format1);

        }else{
            if(DataUtils.IsYesterday(format)){
                timetext.setText("昨天");
            }else{
                timetext.setText(format);
            }
        }
    }

    public void pay(View view) {

        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }
        //-------------------------------------------
        //这里需要自己根据实际去传值
        String orderInfo = AlipayUtil.getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01",PARTNER,SELLER);


        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！...实际sign签名的字符串是通过与公司服务器签名得到的
         */
        String sign = AlipayUtil.sign(orderInfo,RSA_PRIVATE);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + AlipayUtil.getSignType();

        //-------------------------------------都可以放到服务器端,,,,,,PARTNER,SELLER,RSA_PRIVATE放在服务器端,,,商品的名字,价钱,描述需要传给服务器,然后是调用接口返回的是符合支付宝规范的完整订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(MainActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Log.e("jiba",result);

                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();


    }


    /**
     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     *
     * orderInfo的获取必须来自服务端；
     */

}
