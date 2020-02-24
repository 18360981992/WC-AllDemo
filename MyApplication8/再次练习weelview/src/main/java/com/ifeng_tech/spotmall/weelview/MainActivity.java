package com.ifeng_tech.spotmall.weelview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ifeng_tech.spotmall.weelview.weelview.common.LineConfig;
import com.ifeng_tech.spotmall.weelview.weelview.util.ConvertUtils;
import com.ifeng_tech.spotmall.weelview.weelview.widget.WheelListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WheelListView wheelview_single1;
    private WheelListView wheelview_single2;
    private WheelListView wheelview_single3;
    public int curYear;
    public int curMonth;
    public int curDate;
    List<String> listYear = new ArrayList<>();
    List<String> listMonth = new ArrayList<>();
    List<String> listDay = new ArrayList<>();
    public String selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Calendar c = Calendar.getInstance();
        curYear = c.get(Calendar.YEAR);
        //通过Calendar算出的月数要+1
        curMonth = c.get(Calendar.MONTH) + 1;
        curDate = c.get(Calendar.DATE);

        initYear();
        initMonth();
        initDay(curYear,curMonth);

        wheelview_single1.setItems(listYear);
        wheelview_single1.setSelectedTextColor(Color.BLACK);//0xFFFF00FF 紫色
        wheelview_single1.setSelectedItem(curYear+" 年");
        LineConfig config = new LineConfig();
        config.setColor(Color.WHITE);//线颜色
        config.setAlpha(100);//线透明度
        config.setThick(ConvertUtils.toPx(this, 0));//线粗
        config.setShadowVisible(false);
        wheelview_single1.setLineConfig(config);
        // 年
        wheelview_single1.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
            @Override
            public void onItemSelected(int index, String item) {
                setToast("index=" + index + ",item=" + item + wheelview_single2.getSelectedItem());
                String substring1 = item.substring(0, item.length() - 2);
                Integer integer1 = Integer.valueOf(substring1);
                String substring = wheelview_single2.getSelectedItem().substring(0, wheelview_single2.getSelectedItem().length() - 2);
                Integer integer = Integer.valueOf(substring);
                initDay(integer1,integer);
//                wheelview_single2.setItems(listMonth,1);
                selectedItem = wheelview_single3.getSelectedItem();
                wheelview_single3.setItems(listDay);
                if(listDay.contains(selectedItem))
                wheelview_single3.setSelectedItem(selectedItem);
            }
        });

        // 月
        wheelview_single2.setItems(listMonth);
        wheelview_single2.setSelectedTextColor(Color.BLACK);//0xFFFF00FF 紫色
        wheelview_single2.setSelectedItem(curMonth+" 月");
        LineConfig config1 = new LineConfig();
        config.setColor(Color.WHITE);//线颜色
        config.setAlpha(100);//线透明度
        config.setThick(ConvertUtils.toPx(this, 0));//线粗
        config.setShadowVisible(false);
        wheelview_single2.setLineConfig(config);
        // 月
        wheelview_single2.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
            @Override
            public void onItemSelected(int index, String item) {
                setToast("index=" + index + ",item=" +wheelview_single1.getSelectedItem()+item);
                //年
                String substring = wheelview_single1.getSelectedItem().substring(0, wheelview_single1.getSelectedItem().length() - 2);
                Integer integer = Integer.valueOf(substring);
                // 月
                String substring1 = item.substring(0, item.length() - 2);
                Integer integer1 = Integer.valueOf(substring1);

                initDay(integer,integer1);
//                wheelview_single2.setItems(listMonth,1);
                selectedItem = wheelview_single3.getSelectedItem();
                wheelview_single3.setItems(listDay);
                if(listDay.contains(selectedItem))
                    wheelview_single3.setSelectedItem(selectedItem);
            }
        });


        // 日
        wheelview_single3.setItems(listMonth);
        wheelview_single3.setSelectedTextColor(Color.BLACK);//0xFFFF00FF 紫色
        wheelview_single3.setSelectedItem(curDate+" 日");
        LineConfig config2 = new LineConfig();
        config.setColor(Color.WHITE);//线颜色
        config.setAlpha(100);//线透明度
        config.setThick(ConvertUtils.toPx(this, 0));//线粗
        config.setShadowVisible(false);
        wheelview_single3.setLineConfig(config);

        wheelview_single3.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
            @Override
            public void onItemSelected(int index, String item) {
                setToast("index=" + index + ",item=" +wheelview_single1.getSelectedItem()+wheelview_single2.getSelectedItem()+item);
            }
        });

    }

    private void initView() {
        wheelview_single1 = (WheelListView) findViewById(R.id.wheelview_single1);
        wheelview_single2 = (WheelListView) findViewById(R.id.wheelview_single2);
        wheelview_single3 = (WheelListView) findViewById(R.id.wheelview_single3);
    }

    /**
     * 初始化年
     */
    private void initYear() {
        listYear.clear();
        for(int i=curYear-50;i<=curYear;i++){
            listYear.add(i+" 年");
        }
    }

    /**
     * 初始化月
     */
    private void initMonth() {
        listMonth.clear();
        for(int i=1;i<=12;i++){
            listMonth.add(i+" 月");
        }
    }
    /**
     * 初始化天
     */
    private void initDay(int arg1, int arg2) {
        int day = getDay(arg1, arg2);
        listDay.clear();
        for(int i=1;i<=day;i++){
            listDay.add(i+" 日");
        }
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

    private  Toast toast;
    public  void setToast(String ss){
        if(toast==null){
            toast=Toast.makeText(this,ss,Toast.LENGTH_SHORT);
        }
        toast.setText(ss);
        toast.show();
    }
}
