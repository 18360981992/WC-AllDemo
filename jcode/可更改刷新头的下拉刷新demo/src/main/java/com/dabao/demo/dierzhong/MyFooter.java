package com.dabao.demo.dierzhong;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;


/**
 * Created by zewei on 2016-04-28.
 */
public class MyFooter extends RelativeLayout implements NestRefreshLayout.LoaderDecor {
    public MyFooter(Context context) {
        super(context);
    }

    public MyFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void scrollRate(int y) {

    }

    @Override
    public void setState(int state) {
//        if (state == STATE_READY) {
//            setText("自定义：松开加载更多");
//        } else if (state == STATE_REFRESHING) {
//            setText("自定义：加载中");
//        } else if (state == STATE_NORMAL) {
//            setText("自定义：加载更多");
//        }  else if (state == STATE_ALL) {
//            setText("自定义：没有更多了");
//        } else {
//            setText("");
//        }
    }
}
