package com.dabao.scokettcp.socket;

import android.text.TextUtils;
import android.util.Log;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.text.DecimalFormat;

public class SessionManager {
    private static SessionManager mInstance = null;

    private IoSession mSession;

    public static SessionManager getInstance() {
        if (mInstance == null) {
            synchronized (SessionManager.class) {
                if (mInstance == null) {
                    mInstance = new SessionManager();
                }
            }
        }
        return mInstance;
    }

    private SessionManager() {
    }

    public void setSession(IoSession session) {
        this.mSession = session;
    }

    public boolean writeToServer(String message) {
        if (mSession != null && !TextUtils.isEmpty(message)) {
            DecimalFormat format = new DecimalFormat("000000");
            StringBuilder sb = new StringBuilder();
            String body = message;
            byte[] bodyBytes = body.getBytes();
            int allLegth = bodyBytes.length + 10;
            String bodylengthStr = format.format(allLegth);
            StringBuilder append = sb.append("&@").append(bodylengthStr).append(body).append("&#");//拼接信息
            Log.i("Socket", "向Socket发送信息:  " + append.toString());
            //创建一个缓冲，
            IoBuffer buffer = IoBuffer.allocate(allLegth);
            buffer.put(append.toString().getBytes());

            buffer.flip();
            mSession.write(buffer);
            return true;
        }
        return false;
    }

    public void closeSession() {
        if (mSession != null) {
            mSession.closeOnFlush();
        }
    }

    public void removeSession() {
        this.mSession = null;
    }
}
