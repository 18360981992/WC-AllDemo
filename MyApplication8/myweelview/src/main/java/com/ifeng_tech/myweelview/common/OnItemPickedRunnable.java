package com.ifeng_tech.myweelview.common;


import com.ifeng_tech.myweelview.listeners.OnItemPickListener;
import com.ifeng_tech.myweelview.widget.WheelView;

final public class OnItemPickedRunnable implements Runnable {
    final private WheelView wheelView;
    private OnItemPickListener onItemPickListener;
    public OnItemPickedRunnable(WheelView wheelView, OnItemPickListener onItemPickListener) {
        this.wheelView = wheelView;
        this.onItemPickListener = onItemPickListener;
    }

    @Override
    public final void run() {
        onItemPickListener.onItemPicked(wheelView.getCurrentPosition(),wheelView.getCurrentItem());
    }
}
