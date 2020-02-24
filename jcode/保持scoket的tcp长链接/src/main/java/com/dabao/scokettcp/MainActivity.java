package com.dabao.scokettcp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dabao.scokettcp.socket.ConnectionConfig;
import com.dabao.scokettcp.socket.ConnectionManager;
import com.dabao.scokettcp.socket.SocketMessageHandler;

import static com.dabao.scokettcp.MyAppliacation.getContext;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SocketMessageHandler.OnMessageReceivedListener {

    private TextView datetxt;
    private Button connectBtn;
    private Button sendBtn;
    public ConnectionManager mManager;
    public Thread mConnectionThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        datetxt = (TextView) findViewById(R.id.datetxt);
        connectBtn = (Button) findViewById(R.id.connectBtn);
        sendBtn = (Button) findViewById(R.id.sendBtn);

        connectBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connectBtn:
                connectionSocket();

//                launchAppDetail("com.ifeng_tech.treasuryyitong","com.tencent.android.qqdownloader");
                break;
            case R.id.sendBtn:
                mManager.disConnect();
                mConnectionThread = null;
                break;
        }
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mManager.disConnect();
        mConnectionThread = null;
    }


    private void connectionSocket() {
        //长连接请求
        ConnectionConfig config = new ConnectionConfig.Builder(getContext())
                .setIp("www.baidu.com")//连接的IP地址
                .setPort(4200)//连接的端口号
                .setReadBufferSize(1024 * 10)
                .setConnectionTimeout(10)
                .builder();
        mManager = new ConnectionManager(config);

        mConnectionThread = new Thread(new Runnable() {

            boolean isConnection;

            @Override
            public void run() {
                for (; ; ) {
                    isConnection = mManager.connect();
                    if (isConnection) {
                        LogUtils.i("Socket", "连接成功跳出循环");
//                        refreshChildFragment();
                        loadData();
                        break;
                    }
                    Log.d("Socket", "尝试重新连接");
                    if (mConnectionThread == null) {
                        break;
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mConnectionThread.start();
    }

    @Override
    public void messageReceived(String message) {
        LogUtils.i("Socket", "消息回调jsonStr: " + message);
        if (!TextUtils.isEmpty(message)) {
            String jsonStr = message.substring(10, message.length() - 2);
            LogUtils.i("Socket", "消息回调jsonStr: " + jsonStr);
        }
    }

    /**
     * 联网请求
     */
    private void loadData() {
        //长连接请求
//        String body = encodeUrl();
//        String message = mProtocolNo + body;
//        boolean writeSuccess = SessionManager.getInstance().writeToServer(message);
//        if (writeSuccess) {
//
//        }

        SocketMessageHandler.getInstance().setOnMessageReceivedListener("192.168.1.134:4200", new SocketMessageHandler.OnMessageReceivedListener() {
            @Override
            public void messageReceived(String message) {
                LogUtils.i("Socket", "当前行情数据:  " + message);
            }
        });
    }
}
