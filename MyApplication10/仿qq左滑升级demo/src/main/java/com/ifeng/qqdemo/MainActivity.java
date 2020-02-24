package com.ifeng.qqdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ifeng.qqdemo.sildelibrary.ISlide;
import com.ifeng.qqdemo.sildelibrary.OnClickSlideItemListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    LvAdapter adapter;
    List<Integer> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }


        mListView = (ListView) findViewById(R.id.mListView);
        adapter = new LvAdapter(list);
        mListView.setAdapter(adapter);
        adapter.setupListView(mListView);

        adapter.setOnClickSlideItemListener(new OnClickSlideItemListener() {
            @Override
            public void onItemClick(ISlide iSlideView, View v, int position) {
                Toast.makeText(v.getContext(), "click item position:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(ISlide iSlideView, View v, int position) {
                if (v.getId() == R.id.btn_del) {
                    iSlideView.close();
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }else if(v.getId() == R.id.btn_zhuan){
                    Toast.makeText(v.getContext(), "转让:" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
