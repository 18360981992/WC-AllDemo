package com.ifeng_tech.spotmall.weelview;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ifeng_tech.spotmall.weelview.adapter.NumericWheelAdapter;
import com.ifeng_tech.spotmall.weelview.widget.WheelView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tv_date;
    public WheelView month;
    public WheelView day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        year.setWeelViewSelectJieKou(new WheelView.WeelViewSelectJieKou() {
            @Override
            public void chuan(String ss) {
                Log.i("jiba","==="+ss);
            }
        });
    }

    private void initView() {
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        tv_date = (Button) findViewById(R.id.tv_date);
        year = (WheelView) findViewById(R.id.year);
        initYear();
        month = (WheelView) findViewById(R.id.month);
        initMonth();
        day = (WheelView) findViewById(R.id.day);
        initDay(curYear, curMonth);

        year.setCurrentItem(curYear - 1950);
        month.setCurrentItem(curMonth - 1);
        day.setCurrentItem(curDate - 1);
        year.setVisibleItems(7);
        month.setVisibleItems(7);
        day.setVisibleItems(7);

        tv_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date:
//                showDateDialog();
                String str = String.format(Locale.CHINA, "%4d年%2d月%2d日", year.getCurrentItem() + 1950, month.getCurrentItem() + 1, day.getCurrentItem() + 1);
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                break;
        }
    }

    WheelView year;

    /**
     * 显示日期
     */
    private void showDateDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.datepicker_layout);
        // 设置宽高
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);


        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        year = (WheelView) window.findViewById(R.id.year);
        initYear();
        month = (WheelView) window.findViewById(R.id.month);
        initMonth();
        day = (WheelView) window.findViewById(R.id.day);
        initDay(curYear, curMonth);


        year.setCurrentItem(curYear - 1950);
        month.setCurrentItem(curMonth - 1);
        day.setCurrentItem(curDate - 1);
        year.setVisibleItems(7);
        month.setVisibleItems(7);
        day.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = String.format(Locale.CHINA, "%4d年%2d月%2d日", year.getCurrentItem() + 1950, month.getCurrentItem() + 1, day.getCurrentItem() + 1);
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.cancel();
                return false;
            }
        });

    }

    /**
     * 初始化年
     */
    private void initYear() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1950, 2050);
        numericWheelAdapter.setLabel(" 年");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        year.setViewAdapter(numericWheelAdapter);
        year.setCyclic(true);
    }

    /**
     * 初始化月
     */
    private void initMonth() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, 12, "%02d");
        numericWheelAdapter.setLabel(" 月");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        month.setViewAdapter(numericWheelAdapter);
        month.setCyclic(true);
    }

    /**
     * 初始化天
     */
    private void initDay(int arg1, int arg2) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, getDay(arg1, arg2), "%02d");
        numericWheelAdapter.setLabel(" 日");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        day.setViewAdapter(numericWheelAdapter);
        day.setCyclic(true);
    }

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }
}
