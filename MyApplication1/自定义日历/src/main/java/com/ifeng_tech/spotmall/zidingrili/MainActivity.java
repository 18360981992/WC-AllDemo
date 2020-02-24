package com.ifeng_tech.spotmall.zidingrili;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ifeng_tech.spotmall.zidingrili.adapter.CustomCalendarAdapter;
import com.ifeng_tech.spotmall.zidingrili.calendar.CalendarRecyclerHelper;
import com.ifeng_tech.spotmall.zidingrili.calendar.CalendarView;
import com.ifeng_tech.spotmall.zidingrili.calendar.CustomDate;

public class MainActivity extends AppCompatActivity {

    private TextView showTv;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTv = (TextView) findViewById(R.id.tv_show_date);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        CalendarRecyclerHelper.init(this, recyclerView, new CustomCalendarAdapter(), new CalendarView.OnCalendarPageChanged() {
            @Override
            public void onPageChanged(CustomDate showDate) {
                showTv.setText(showDate.year+"年"+showDate.month+"月");
            }
        });
    }
}
