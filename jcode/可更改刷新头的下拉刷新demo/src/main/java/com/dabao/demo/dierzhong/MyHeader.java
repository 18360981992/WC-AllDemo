package com.dabao.demo.dierzhong;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;


/**
 * Created by zewei on 2016-04-28.
 */
public class MyHeader extends RelativeLayout implements NestRefreshLayout.LoaderDecor {

    private Context mContext;

    public MyHeader(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public void scrollRate(int y) {

    }

    @Override
    public void setState(int state) {
        if (state == STATE_READY) {
//            setText("自定义：松开加载更多");
        } else if (state == STATE_REFRESHING) {
        } else if (state == STATE_NORMAL) {
//            setText("自定义：加载更多");
        }  else if (state == STATE_ALL) {
//            setText("自定义：没有更多了");
        } else {   // 成功
        }
    }


}
