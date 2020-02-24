package com.dabao.demo;

/**
 * Created by zzt on 2018/11/16.
 */

public class DayBean {
    int curYear;
    int curMonth;
    int curDate;
    boolean isflag; // 控制字体颜色
    boolean isClick; // 控制点击时的颜色切换

    public void setClick(boolean click) {
        isClick = click;
    }

    public boolean isClick() {

        return isClick;
    }

    public void setCurYear(int curYear) {
        this.curYear = curYear;
    }

    public void setCurMonth(int curMonth) {
        this.curMonth = curMonth;
    }

    public void setCurDate(int curDate) {
        this.curDate = curDate;
    }

    public void setIsflag(boolean isflag) {
        this.isflag = isflag;
    }

    public int getCurYear() {

        return curYear;
    }

    public int getCurMonth() {
        return curMonth;
    }

    public int getCurDate() {
        return curDate;
    }

    public boolean isflag() {
        return isflag;
    }

    public DayBean() {

    }

    public DayBean(int curYear, int curMonth, int curDate, boolean isflag, boolean isClick) {
        this.curYear = curYear;
        this.curMonth = curMonth;
        this.curDate = curDate;
        this.isflag = isflag;
        this.isClick = isClick;
    }
}
