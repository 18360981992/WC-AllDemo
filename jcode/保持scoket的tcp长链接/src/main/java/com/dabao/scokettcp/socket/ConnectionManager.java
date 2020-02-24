package com.dabao.scokettcp.socket;

import android.content.Context;

import com.dabao.scokettcp.LogUtils;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;

public class ConnectionManager {
    public static final String TAG = "SocketService";
    private ConnectionConfig mConfig;
    private WeakReference<Context> mContext;
    public NioSocketConnector mConnection;
    private IoSession mSession;
    private InetSocketAddress mAddress;

    public ConnectionManager(ConnectionConfig config) {

        this.mConfig = config;
        this.mContext = new WeakReference<Context>(config.getContext());
        init();
    }

    private void init() {
        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        mConnection = new NioSocketConnector();
        mConnection.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
//        mConnection.setConnectTimeoutMillis(mConfig.getConnectionTimeout());
        //设置多长时间没有进行读写操作进入空闲状态，会调用sessionIdle方法，单位（秒）
        mConnection.getSessionConfig().setReaderIdleTime(60 * 5);
        mConnection.getSessionConfig().setWriterIdleTime(60 * 5);
        mConnection.getSessionConfig().setBothIdleTime(60 * 5);
        mConnection.getFilterChain().addFirst("reconnection", new MyIoFilterAdapter());
        //自定义编解码器
        mConnection.getFilterChain().addLast("mycoder", new ProtocolCodecFilter(new MyCodecFactory()));
//        mConnection.getFilterChain().addLast("codec",
//                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
//                        LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
        //添加消息处理器
        SocketMessageHandler socketMessageHandler = SocketMessageHandler.getInstance();
        mConnection.setHandler(socketMessageHandler);
        mConnection.setDefaultRemoteAddress(mAddress);
    }

    /**
     * 与服务器连接
     *
     * @return true连接成功，false连接失败
     */
    public boolean connect() {
        try {
            if (mConnection == null){
                return false;
            }
            ConnectFuture future = mConnection.connect();
            future.awaitUninterruptibly();
            mSession = future.getSession();
            if (mSession != null && mSession.isConnected()) {
                SessionManager.getInstance().setSession(mSession);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 断开连接
     */
    public void disConnect() {
        LogUtils.i("SocketService","断开连接");
        if(mConnection != null){
            mConnection.dispose();
        }
        mConnection = null;
        mSession = null;
        mAddress = null;
        mContext = null;
    }

    private class MyIoFilterAdapter extends IoFilterAdapter {
        @Override
        public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
            LogUtils.i(TAG, "连接关闭，每隔5秒进行重新连接");
            for (; ; ) {
                LogUtils.i(TAG, "重新连接中...");
                if (mConnection == null) {
                    break;
                }
                if (ConnectionManager.this.connect()) {
                    LogUtils.i(TAG, "断线重连[" + mConnection.getDefaultRemoteAddress().getHostName() + ":" +
                            mConnection.getDefaultRemoteAddress().getPort() + "]成功");
                    break;
                }
                Thread.sleep(5000);
            }
        }

    }
}
