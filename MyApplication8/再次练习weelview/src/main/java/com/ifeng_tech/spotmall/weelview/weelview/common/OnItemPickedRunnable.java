package com.ifeng_tech.spotmall.weelview.weelview.common;


import com.ifeng_tech.spotmall.weelview.weelview.listeners.OnItemPickListener;
import com.ifeng_tech.spotmall.weelview.weelview.widget.WheelView;

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
