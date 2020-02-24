package com.ifeng_tech.spotmall.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by joker on 2017/7/27.
 */

public class ToastUtil {
    private static Toast toast;

    public static void show(Context context,String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
