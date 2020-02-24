package com.dabao.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CircleProgressBar circleProgressBar; // 自定义的进度条
    private SeekBar seekbar; // 拖动条

    private int[] colors = new int[] { Color.parseColor("#FF618ADF"), Color.parseColor("#4970E1") };
    public TextView tv;

    int progress=45000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.circleProgressBar);
//		circleProgressBar.setFirstColor(Color.LTGRAY);  // 设置圆的底色
		circleProgressBar.setColorArray(colors); //觉得进度条颜色丑的，这里可以自行传入一个颜色渐变数组。
//		circleProgressBar.setCircleWidth(6);   // 设置圆的粗细


//        tv.setText(progress+"");
//        circleProgressBar.setMaxValue(50000);  // 设置最大值
//        circleProgressBar.setProgress1(progress); //不使用动画


        /**
         *  如果不需要seekbar的话，就将下面的代码全部注掉，并放开上面的三行代码
         */

        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setMax(50000);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (fromUser)
                {

                    tv.setText(progress+"");
                    circleProgressBar.setMaxValue(50000);  // 设置最大值
                    circleProgressBar.setProgress1(progress); // 自定义的 不使用动画


//                     circleProgressBar.setProgress(progress); //不使用动画
//                    circleProgressBar.setProgress(progress, true); // 使用数字过渡动画
                }
            }
        });


    }
}
