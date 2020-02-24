package com.dabao.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GridView gv_zhou;
    private GridView gv_yue;

    String[] zhou = { "日","一", "二", "三", "四", "五", "六"};
    public List<String> listMonth;
    List<DayBean> listDay;
    private TextView shang;
    private TextView time;
    private TextView xia;
    public int curYear_new;
    public int curMonth_new;
    public int curDate_new;
    public int curYear_old;
    public int curMonth_old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Calendar calendar = Calendar.getInstance();
        curYear_new = calendar.get(Calendar.YEAR);
        //通过Calendar算出的月数要+1
        curMonth_new = calendar.get(Calendar.MONTH) + 1;
        curDate_new = calendar.get(Calendar.DATE);

        curYear_old = curYear_new;
        curMonth_old = curMonth_new;

        final CalendarUtils calendarUtils = new CalendarUtils(MainActivity.this, time, gv_yue, listMonth);
        calendarUtils.setFlag(true);
        calendarUtils.setFlag_Week(true);
        // 进行日期排布
        listDay = calendarUtils.getPaibu(curYear_new, curMonth_new, 0);

        // 点击上一月
        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curMonth_old==1){
                    curYear_old = curYear_old-1;
                    curMonth_old=12;
                }else{
                    curMonth_old=curMonth_old-1;
                }
                listDay = calendarUtils.getPaibu(curYear_old, curMonth_old, 0);
            }
        });

        // 点击下一月
        xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curMonth_old==12){
                    curYear_old = curYear_old+1;
                    curMonth_old=1;
                }else{
                    curMonth_old=curMonth_old+1;
                }
                listDay = calendarUtils.getPaibu(curYear_old, curMonth_old,0);
            }
        });

        // 点击日期的选择
        gv_yue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("jba","year=="+listDay.get(position).getCurYear()+",Month=="+listDay.get(position).getCurMonth()+",Date=="+listDay.get(position).getCurDate());
                if(listDay.get(position).getCurYear()>0){
                    listDay = calendarUtils.getPaibu(listDay.get(position).getCurYear(), listDay.get(position).getCurMonth(),listDay.get(position).getCurDate());
                }
            }
        });
    }

    private void initView() {
        gv_zhou = (GridView) findViewById(R.id.gv_zhou);
        gv_yue = (GridView) findViewById(R.id.gv_yue);
        listMonth = Arrays.asList(zhou);
        shang = (TextView) findViewById(R.id.shang);
        time = (TextView) findViewById(R.id.time);
        xia = (TextView) findViewById(R.id.xia);

        getZhou_Paibu(); // 排布周


        List<Integer> list = new ArrayList<>();

        for (int i=1;i<=100;i++){
            for (int j=1;j<=100;j++){
                int i1 = j%i;
                Log.i("jba","i1=="+i1);
                if(i1==0){
                    list.add(j);
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : list){
            if(map.keySet().contains(i)){
                map.put(i,(map.get(i)+1));
            }else{
                map.put(i,1);
            }
        }

        int k=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue()%2==0){
                k++;
            }
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }


        Log.i("jba","k==="+k);

    }

    private void getZhou_Paibu() {
        gv_zhou.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listMonth.size();
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
                View view = View.inflate(MainActivity.this, R.layout.zhou_item, null);
                TextView gv_zhou_text = view.findViewById(R.id.gv_zhou_text);
                gv_zhou_text.setText(listMonth.get(position));
                return view;
            }
        });
    }
}
