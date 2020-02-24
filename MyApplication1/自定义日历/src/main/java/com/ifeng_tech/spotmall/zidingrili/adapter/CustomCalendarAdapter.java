package com.ifeng_tech.spotmall.zidingrili.adapter;


import com.ifeng_tech.spotmall.zidingrili.calendar.CalendarAdapter;
import com.ifeng_tech.spotmall.zidingrili.calendar.CalendarView;

/**
 * Created by huang on 2017/11/22.
 */

public class CustomCalendarAdapter extends CalendarAdapter {
    private CustomTextDrawFormat format;

    public CustomCalendarAdapter(int mode) {
        super(mode);
    }

    public CustomCalendarAdapter() {
    }

    @Override
    protected void decorateCalendarView(CalendarView calendarView) {
        if(format == null){
            format = new CustomTextDrawFormat(mContext);
        }
        calendarView.addDrawFormat(format);
    }
}
