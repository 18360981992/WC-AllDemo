package com.ifeng_tech.treasuryyitong.rili;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.rili.utils.DataUtils;
import com.ifeng_tech.treasuryyitong.rili.view.DatePopupWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView info;
    private DatePopupWindow popupWindow;
    private String dataFormate = "yyyy-MM-dd" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        info = (TextView) findViewById(R.id.info);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                showPopupWindow(btn);
                break;
        }
    }

    private void showPopupWindow(View v) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);

        popupWindow = new DatePopupWindow(getApplicationContext(), format);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(v);


        popupWindow.setOnItemClick(new DatePopupWindow.OnItemClick() {
            @Override
            public void onItemClick(String date) {
                info.setText("选中的日期是："+ DataUtils.formatDate(date,dataFormate));
            }
        });

    }

}
