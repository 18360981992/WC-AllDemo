package com.ifeng.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    public List<HaoHan> persons;
    public List<HaoHan> persons1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //D.model层,创建集合
        persons = new ArrayList<>();
        persons1 = new ArrayList<>();
        //D.填充并排列数据
        fillAndSortData(persons,persons1);
        //D.Controller层,设置适配器
        lv.setAdapter(new HaoHanAdapter(persons,this));

    }
    /**
     * 填充数据,并进行排序
     * @param persons
     */
    private void fillAndSortData(List<HaoHan> persons,List<HaoHan> persons1) {
        //填充
        for(int x=0; x<Cheeses.NAMES.length; x++){
            String name = Cheeses.NAMES[x];
            HaoHan haoHan = new HaoHan(name);
            if(!haoHan.getPinyin().equals("#")){
                persons.add(haoHan);
            }else{
                persons1.add(haoHan);
            }
        }
        //排序
        Collections.sort(persons);
        persons.addAll(persons1);

        Log.i("jba","persons.toString()==="+persons.toString());
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }
}
