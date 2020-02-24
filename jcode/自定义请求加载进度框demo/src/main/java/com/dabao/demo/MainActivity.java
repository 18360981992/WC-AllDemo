package com.dabao.demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private CircleLoadingView circleLoadingView;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        circleLoadingView.startDotAnimator();

//        seekbar.setMax(100);
//        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar)
//            {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar)
//            {
//
//            }
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
//            {
//                if (fromUser){
//                    circleLoadingView.setProgress(progress); // 自定义的 不使用动画
//                }
//            }
//        });

        //  进度框
        final ProgressDialog aniDialog = getProgressDialog(MainActivity.this, "");

    }

    private void initView() {
        circleLoadingView = (CircleLoadingView) findViewById(R.id.circleLoadingView);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
    }

    /**
     *  网络加载进度框
     * @param context
     * @param text
     * @return
     */
    public static ProgressDialog getProgressDialog(Context context, String text){
        //  进度框
        Loading_view aniDialog = new Loading_view(context, R.style.CustomDialog);
        aniDialog.show();
        return aniDialog;
    }
}
