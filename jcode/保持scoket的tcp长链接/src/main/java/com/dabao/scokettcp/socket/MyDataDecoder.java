package com.dabao.scokettcp.socket;


import com.dabao.scokettcp.LogUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.util.Arrays;

public class MyDataDecoder extends CumulativeProtocolDecoder {
    /**
     * 返回值含义:
     * 1、当内容刚好时，返回false，告知父类接收下一批内容
     * 2、内容不够时需要下一批发过来的内容，此时返回false，这样父类 CumulativeProtocolDecoder
     * 会将内容放进IoSession中，等下次来数据后就自动拼装再交给本类的doDecode
     * 3、当内容多时，返回true，因为需要再将本批数据进行读取，父类会将剩余的数据再次推送本类的doDecode方法
     */
    @Override
    public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) {

        int remaining = in.remaining();
//         LogUtils.i("Socket", "remaining: " + remaining);
        if (remaining > 0) {
            // 有数据时，读取 8字节判断消息长度
            byte[] sizeBytes = new byte[8];
            // 标记当前位置，以便 reset
            in.mark();
            in.get(sizeBytes);

            String str = new String(sizeBytes);
            int size;
            try {
                size = Integer.parseInt(str.substring(2));//
//                LogUtils.i("Socket", "头str:  " + str + "  ;Arrays.toString: " + Arrays.toString(sizeBytes) + " ; size: " + size);
            } catch (Exception e) {
//                 LogUtils.i("Socket", "数据类型转换异常str:  " + str +"  ;Arrays.toString:  " + Arrays.toString(sizeBytes));
                in.reset();
                return false;
            }
            in.reset();
            if (size > in.remaining()) {
                // 如果消息内容的长度不够，则重置（相当于不读取 size），返回 false
                // 接收新数据，以拼凑成完整的数据
                return false;
            } else {
                byte[] dataBytes = new byte[size];
                in.get(dataBytes);
                LogUtils.i("Socket", "完整数据dataBytes.length:  " + dataBytes.length + "  ;size: " + size + "  ;Arrays.toString: " + Arrays.toString(dataBytes));
                out.write(dataBytes);

                if (in.remaining() > 0) {
                    // 如果读取内容后还粘了包，就让父类把剩下的数据再给解析一次
                    return true;
                }
            }
        }
        // 处理成功，让父类进行接收下个包
        return false;
    }
}