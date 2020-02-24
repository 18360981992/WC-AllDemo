package com.dabao.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zzt on 2018/11/16.
 */

public class CalendarUtils {

    Context context;
    private TextView time;
    private GridView gv_yue;
    public List<String> listMonth;
    List<DayBean> listDay = new ArrayList<>();
    public int curYear_new;
    public int curMonth_new;
    public int curDate_new;
    public int curYear_old;
    public int curMonth_old;
    boolean isFlag=false; // 控制是否需要显示前后的具体月份
    boolean isFlag_Week=false; // 控制非工作日的日期字体颜色变暗

    public CalendarUtils(Context context,TextView time, GridView gv_yue, List<String> listMonth) {
        this.context=context;
        this.time = time;
        this.gv_yue = gv_yue;
        this.listMonth = listMonth;

        Calendar calendar = Calendar.getInstance();
        curYear_new = calendar.get(Calendar.YEAR);
        //通过Calendar算出的月数要+1
        curMonth_new = calendar.get(Calendar.MONTH) + 1;
        curDate_new = calendar.get(Calendar.DATE);

        curYear_old = curYear_new;
        curMonth_old = curMonth_new;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public void setFlag_Week(boolean flag_Week) {
        isFlag_Week = flag_Week;
    }

    public List<DayBean> getPaibu(final int curYear, final int curMonth, final int curDate) {
        time.setText(curYear +"-"+ curMonth);
        initDay(curYear, curMonth); // 初始化天数

        int curYear1=curYear;  // 前一个月
        int curMonth1=curMonth;
        int day=0;
        if(isFlag){ // 如果打开的开关 就需要获取前一个月的天数  便于下面的倒序
            if(curMonth1==1){
                curYear1=curYear1-1;
                curMonth1=12;
            }else{
                curMonth1=curMonth1-1;
            }
            day = getDay(curYear1, curMonth1);// 初始化前一个月的天数
        }

        String first_weekByDateStr = getWeekByDateStr(curYear, curMonth, Integer.valueOf(listDay.get(0).getCurDate()));// 获取月份的第一天是星期几

        // 根据获取到的月份第一天和月份最后一天来计算整个日历的排版
        // 集合头的添加个数   是星期几头部就加星期几的下标
        int tou_i = listMonth.indexOf(first_weekByDateStr);
        for (int i = 0; i < tou_i; i++) {
            if(isFlag){
                int i1 = day - i;
                listDay.add(0, new DayBean(curYear1, curMonth1,i1,false,false));
            }else{
                listDay.add(0, new DayBean(0,0,0,false,false));
            }
        }
        // 集合的尾部添加个数  是星期几尾部就加上 6-星期几的下标
        int curYear2=curYear;  // 后一个月
        int curMonth2=curMonth;
        if(isFlag){ // 如果打开的开关 就需要获取后一个月的月数与年数  便于下面的倒序
            if(curMonth2==12){
                curYear2=curYear1+1;
                curMonth2=1;
            }else{
                curMonth2=curMonth2+1;
            }
        }
        String last_weekByDateStr = getWeekByDateStr(curYear, curMonth, Integer.valueOf(listDay.get(listDay.size() - 1).getCurDate())); // 获取月份的最后一天是星期几
        int wei_i = listMonth.indexOf(last_weekByDateStr);
        for (int i = 0; i < (6 - wei_i); i++) {
            if(isFlag){
                listDay.add(new DayBean(curYear2, curMonth2,i+1,false,false));
            }else{
                listDay.add(new DayBean(0,0,0,false,false));
            }
        }

        if(isFlag_Week){  // 打开开关就需要将非工作日的字体颜色改变
            getlistDay_Week(listDay);
        }

        // 将默认的当前日期点亮
        if(curYear_new==curYear&&curMonth_new==curMonth){
            for (int i = 0; i < listDay.size(); i++) {
                if(listDay.get(i).getCurYear()==curYear_new&&listDay.get(i).getCurMonth()==curMonth_new&&listDay.get(i).getCurDate()==curDate_new){
                    DayBean dayBean = listDay.get(i);
                    dayBean.setClick(true);
                    listDay.set(i,dayBean);
                    break;
                }
            }
        }

        // 将被点击的日期点亮
        for (int i = 0; i < listDay.size(); i++) {
            if(listDay.get(i).getCurYear()==curYear&&listDay.get(i).getCurMonth()==curMonth&&listDay.get(i).getCurDate()==curDate){
                DayBean dayBean = listDay.get(i);
                dayBean.setClick(true);
                listDay.set(i,dayBean);
                break;
            }
        }

        gv_yue.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listDay.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(context, R.layout.yue_item, null);
                TextView gv_yue_text = view.findViewById(R.id.gv_yue_text);
                LinearLayout gv_yue_LinearLayout = view.findViewById(R.id.gv_yue_LinearLayout);
                // 日期非0 就展示
                if(listDay.get(position).getCurDate()>0){
                    gv_yue_text.setText(listDay.get(position).getCurDate()+"");
                }

                if(listDay.get(position).isflag()){
                    gv_yue_text.setTextColor(context.getResources().getColor(R.color.name_se));
                }else{
                    gv_yue_text.setTextColor(context.getResources().getColor(R.color.color_666666));
                }

                if(listDay.get(position).isClick()){
                    gv_yue_LinearLayout.setBackgroundColor(context.getResources().getColor(R.color.zhuse));
                    gv_yue_text.setTextColor(context.getResources().getColor(R.color.baise));
                }else{
                    gv_yue_LinearLayout.setBackgroundColor(context.getResources().getColor(R.color.baise));
                    if(listDay.get(position).isflag()){
                        gv_yue_text.setTextColor(context.getResources().getColor(R.color.name_se));
                    }else{
                        gv_yue_text.setTextColor(context.getResources().getColor(R.color.color_666666));
                    }
                }
                return view;
            }
        });

        return listDay;
    }

    /**
     * 初始化天数 并装入集合
     */
    private void initDay(int year, int month) {
        int day = getDay(year, month);
        listDay.clear();
        for (int i = 1; i <= day; i++) {
            listDay.add(new DayBean(year,month,i,true,false));
        }
    }

    /**
     * 获取指定月份的天数
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


    /**
     * 识别是否是工作日 并将不是工作日的日期字体颜色变暗
     * List<DayBean> listDay
     * @return
     */
    private void getlistDay_Week(List<DayBean> listDay) {
        for (int i = 0; i < listDay.size(); i++) {
            String weekByDateStr = getWeekByDateStr(listDay.get(i).getCurYear(), listDay.get(i).getCurMonth(), listDay.get(i).getCurDate());
            if(listMonth.indexOf(weekByDateStr)==0||listMonth.indexOf(weekByDateStr)==6){
                DayBean dayBean = listDay.get(i);
                dayBean.setIsflag(false);
                listDay.set(i,dayBean);
            }
        }
    }


    // 获取指定日期是星期几
    public String getWeekByDateStr(int year, int month, int day) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        String week = "";
        int weekIndex=0;
        if(listMonth.get(0).contains("日")){
            weekIndex = c.get(Calendar.DAY_OF_WEEK)-1;
        }else{
            weekIndex = c.get(Calendar.DAY_OF_WEEK)-2;
            if(weekIndex==-1){
                weekIndex=6;
            }
        }
        String s = listMonth.get(weekIndex);
//        switch (weekIndex)
//        {
//            case 1:
//                week = "日";
//                break;
//            case 2:
//                week = "一";
//                break;
//            case 3:
//                week = "二";
//                break;
//            case 4:
//                week = "三";
//                break;
//            case 5:
//                week = "四";
//                break;
//            case 6:
//                week = "五";
//                break;
//            case 7:
//                week = "六";
//                break;
//        }
        return s;
    }
}
