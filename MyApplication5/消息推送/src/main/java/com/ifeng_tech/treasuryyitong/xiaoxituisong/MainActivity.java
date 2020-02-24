package com.ifeng_tech.treasuryyitong.xiaoxituisong;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.xiaoxituisong.utils.MyUtil;
import com.leethink.badger.BadgeUtil;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    public static boolean isForeground;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    // 从Receiver接受自定义消息
    private MessageReceiver mMessageReceiver;
    private EditText messageTv;
    private Button btn;
    private EditText tv_count;
    private Button bt_set;
    private Button bt_clear;
    public static final int NOTIFY_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyUtil.initCustomPushNotificationBuilder(this, 1,
                R.layout.customer_notitfication_layout_one,
                R.drawable.r7, R.drawable.r8,
                Notification.FLAG_AUTO_CANCEL, Notification.DEFAULT_SOUND,
                Notification.DEFAULT_LIGHTS);// 自定义编号1的通知栏

        btn = (Button) findViewById(R.id.btn);
        messageTv = (EditText) findViewById(R.id.ed);

        tv_count = (EditText) findViewById(R.id.tv_count);

        bt_set = (Button) findViewById(R.id.bt_set);
        bt_clear = (Button) findViewById(R.id.bt_clear);


//        registerMessageReceiver();// 初始化从Receiver接受自定义消息

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyUtil.showLocalNotification(getApplicationContext(), 1, "您有一条新的消息",
                        messageTv.getText().toString(), 111111,
                        java.lang.System.currentTimeMillis() + 10);
            }
        });

        bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final int count = Integer.parseInt(tv_count.getText().toString());
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(getApplicationInfo().icon)
                            .setWhen(System.currentTimeMillis())
                            .setAutoCancel(true);

                    mBuilder.setContentTitle("test");
                    mBuilder.setTicker("test");
                    mBuilder.setContentText("test");

                    //点击set 后，app退到桌面等待3s看效果（有的launcher当app在前台设置未读数量无效）
                    final Notification notification = mBuilder.build();
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BadgeUtil.sendBadgeNotification(notification, NOTIFY_ID, getApplicationContext(), count, count);
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                        }
                    }, 3 * 1000);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //XIAO_MI 当进入app后就自动清除了未读数量.有些需要主动清除。可以所有的都清除，没什么影响
                BadgeUtil.resetBadgeCount(getApplicationContext());
            }
        });

    }

    /**
     * 重写onResume()和onPause()用于统计分析 API
     */
    // /////////////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
        JPushInterface.onPause(this);
    }

    // //////////////////////////////////////////////////
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mMessageReceiver);
    }


    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!MyUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }


    private void setCostomMsg(String msg) {
        if (null != messageTv) {
            messageTv.setText(msg);
            messageTv.setVisibility(View.VISIBLE);
        }
    }

}
