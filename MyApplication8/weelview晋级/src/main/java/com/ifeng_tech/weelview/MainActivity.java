package com.ifeng_tech.weelview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pl.wheelview.WheelView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public int curYear;
    public int curMonth;
    public int curDate;
    ArrayList<String> listYear = new ArrayList<>();
    private WheelView wheelView;

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
        wheelView.setData(listYear);

        wheelView.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                setToast("endSelect==id=="+id+",text=="+text);
            }

            @Override
            public void selecting(int id, String text) {
//                setToast("selecting==id=="+id+",text=="+text);
            }
        });
    }

    private void initView() {
        wheelView = (WheelView) findViewById(R.id.wheelView);
    }

    /**
     * 初始化年
     */
    private void initYear() {
        listYear.clear();
        for (int i = curYear - 50; i <= curYear; i++) {
            listYear.add(i + " 年");
        }
    }

    private Toast toast;

    public void setToast(String ss) {
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        toast.setText(ss);
        toast.show();
    }
}
