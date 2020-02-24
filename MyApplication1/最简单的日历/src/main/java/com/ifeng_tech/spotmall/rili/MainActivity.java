package com.ifeng_tech.spotmall.rili;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ifeng_tech.spotmall.rili.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_pre;
    private TextView tv_month;
    private TextView tv_next;
    /**日历控件*/
    private CalendarView calendar;
    /**日历对象*/
    private Calendar cal;
    /**格式化工具*/
    private SimpleDateFormat formatter;
    /**日期*/
    private Date curDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_pre = (TextView) findViewById(R.id.tv_pre);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_next = (TextView) findViewById(R.id.tv_next);
        calendar = (CalendarView) findViewById(R.id.calendar);

        cal = Calendar.getInstance();
        //初始化界面
        init();


        //给左右月份设置点击监听事件
        tv_pre.setOnClickListener(this);
        tv_next.setOnClickListener(this);

        calendar.setOnSelectChangeListener(new CalendarView.OnSelectChangeListener() {
            @Override
            public void selectChange(CalendarView calendarView, Date date) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Toast.makeText(MainActivity.this,simpleDateFormat.format(date),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 初始化界面
     */
    private void init() {

        formatter = new SimpleDateFormat("yyyy年MM月");
        //获取当前时间
        curDate = cal.getTime();
        String str = formatter.format(curDate);
        tv_month.setText(str);
        String strPre=(cal.get(Calendar.MONTH))+"月";
        if (strPre.equals("0月")){
            strPre="12月";
        }
        tv_pre.setText(strPre);
        String strNext=(cal.get(Calendar.MONTH)+2)+"月";
        if(strNext.equals("13月")){
            strNext="1月";
        }
        tv_next.setText(strNext);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_pre:
                cal.add(Calendar.MONTH,-1);
                init();
                calendar.setCalendar(cal);
                break;
            case R.id.tv_next:
                cal.add(Calendar.MONTH,+1);
                init();
                calendar.setCalendar(cal);
                break;
        }

    }
}
