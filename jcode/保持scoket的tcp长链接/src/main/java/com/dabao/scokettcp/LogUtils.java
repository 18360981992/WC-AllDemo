package com.dabao.scokettcp;

import android.util.Log;

/**
 * Created by Dash
 *
 * 现在未签名的应用都是debug版本的...
 * 上线的时候要对应用签名
 *
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 */
public class LogUtils {
    //是否是调试模式
    public static final  boolean isDebug = true;

//    public static void i(String TAG,String info){
//        if(isDebug){
//            Log.i(TAG,info);
//        }
//    }

//    static String fozu = 			    "                   _ooOoo_"+"\n"+			    "                  o8888888o"+"\n"+			    "                  88\" . \"88"+"\n"+			    "                  (| -_- |)"+"\n"+			    "                  O\\  =  /O"+"\n"+			    "               ____/`---'\\____"+"\n"+			    "             .'  \\\\|     |//  `."+"\n"+			    "            /  \\\\|||  :  |||//  \\"+"\n"+			    "           /  _||||| -:- |||||-  \\"+"\n"+			    "           |   | \\\\\\  -  /// |   |"+"\n"+			    "           | \\_|  ''\\---/''  |   |"+"\n"+			    "           \\  .-\\__  `-`  ___/-. /"+"\n"+			    "         ___`. .'  /--.--\\  `. . __"+"\n"+			    "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\"."+"\n"+			    "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |"+"\n"+			    "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /"+"\n"+			    "======`-.____`-.___\\_____/___.-`____.-'======"+"\n"+			    "                   `=---='"+"\n"+			    "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+"\n"+			    "                 佛祖保佑       永无BUG";


    public static void d(String TAG,String info){
        if(isDebug){
            Log.d(TAG,info);
        }
    }
    public static void e(String TAG,String info){
        if(isDebug){
            Log.e(TAG,info);
        }
    }

    public static void i(String tag, String msg) {  //信息太长,分段打印

        if(isDebug){
            //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
            //  把4*1024的MAX字节打印长度改为2001字符数
            int max_str_length = 2001 - tag.length();
            //大于4000时
            while (msg.length() > max_str_length) {
                Log.i(tag, msg.substring(0, max_str_length));
                msg = msg.substring(max_str_length);
            }
            //剩余部分
            Log.i(tag, msg);
        }

    }

}
