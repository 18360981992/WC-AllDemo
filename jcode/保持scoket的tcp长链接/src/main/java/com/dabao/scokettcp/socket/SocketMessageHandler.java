package com.dabao.scokettcp.socket;


import com.dabao.scokettcp.LogUtils;
import com.dabao.scokettcp.MyAppliacation;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Administrator on 2017/11/22.
 */

public class SocketMessageHandler extends IoHandlerAdapter {

    private static SocketMessageHandler mSocketMessageHandler;

    public static SocketMessageHandler getInstance() {
        if (mSocketMessageHandler == null) {
            synchronized (SocketMessageHandler.class) {
                if (mSocketMessageHandler == null) {
                    mSocketMessageHandler = new SocketMessageHandler();
                }
            }
        }
        return mSocketMessageHandler;
    }

    private SocketMessageHandler() {
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        LogUtils.i("SocketService","连接打开");
    }

    @Override
    public void messageReceived(IoSession session, Object object) throws Exception {
        byte[] bytes = (byte[]) object;
        String message = new String(bytes, "UTF-8");
        LogUtils.i("Socket", "收到数据: " + message);

        String endStr = message.substring(message.length() - 2);
        String protocolStr = message.substring(8, 10);

        LogUtils.i("Socket", "消息回调协议号: " + protocolStr + " ; 请求协议号:  " + mProtocolNo);

        if ("&#".equals(endStr)) {
            LogUtils.i("Socket", "消息最后两位{  " + endStr + " };消息正确;message.length():" +
                    "  " + message.length() + " ;message.getByte().length:  " + message.getBytes().length);
        } else {
            LogUtils.i("Socket", "消息最后两位{  " + endStr + " }消息最后两位不是$#,消息不完整;message.length():" +
                    "  " + message.length() + " ;message.getByte().length:  " + message.getBytes().length);
            return;
        }
        if (mMessageReceivedListener != null) {
            if (protocolStr.equals(mProtocolNo)) {
                final String jsonStr = message.substring(10, message.length() - 2);//截取body
                MyAppliacation.getMainThreadHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        mMessageReceivedListener.messageReceived(jsonStr);
                    }
                });

            }
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        LogUtils.i("SocketService","消息发送:" + message + " sessionbyte:  " + session.getReadMessages());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        LogUtils.i("SocketService","-客户端与服务端连接空闲");
        //进入空闲状态我们把会话关闭，接着会调用MyIoFilterAdapter的sessionClosed方法，进行重新连接
        if (session != null) {
            session.closeOnFlush();
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        LogUtils.i("SocketService","客户端异常 cause: " + cause.toString());
    }

    public interface OnMessageReceivedListener {
        void messageReceived(String message);
    }

    private OnMessageReceivedListener mMessageReceivedListener;
    private String mProtocolNo;

    public void setOnMessageReceivedListener(String protocolNo, OnMessageReceivedListener mMessageReceivedListener) {
        this.mMessageReceivedListener = mMessageReceivedListener;
        this.mProtocolNo = protocolNo;
    }
}