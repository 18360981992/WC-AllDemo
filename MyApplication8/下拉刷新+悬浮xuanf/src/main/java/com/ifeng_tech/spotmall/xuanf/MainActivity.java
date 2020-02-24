package com.ifeng_tech.spotmall.xuanf;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ifeng_tech.spotmall.xuanf.bean.Bill;
import com.ifeng_tech.spotmall.xuanf.pull.ListViewPlus;
import com.ifeng_tech.spotmall.xuanf.pull.PinnedSectionListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListViewPlus.ListViewPlusListener{

    public PinnedSectionListView listView;
    private BillAdapter adapter;
    private ArrayList<Bill>bills;
    private int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (PinnedSectionListView) findViewById(R.id.list);
        bills=new ArrayList<Bill>();
        listView.setLoadEnable(true);
        listView.setListViewPlusListener(this);
        adapter=new BillAdapter(this, bills);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                BillAdapter.Item item=(BillAdapter.Item) adapter.getItem(position-1);
                if(item.getType()== BillAdapter.Item.SECTION)return;
                Toast.makeText(MainActivity.this,item.getTitle() ,Toast.LENGTH_LONG).show();
            }
        });
        loadData(ListViewPlus.REFRESH);

    }
    private void loadData(final int what) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ListViewPlus.REFRESH==what) {
                    bills.clear();
                    listView.stopRefresh();
                    page=1;
                }else {
                    listView.stopLoadMore();
                    page++;
                }
                for (int i = (page-1)*20; i <page*20; i++) {
                    Bill bill=new Bill(i, "title:"+i, i*100+"元",System.currentTimeMillis()-1000L*60*60*24*12*i, "交易成功");
                    bills.add(bill);
                }
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        loadData(ListViewPlus.REFRESH);
    }

    @Override
    public void onLoadMore() {
        loadData(ListViewPlus.LOAD);
    }
}
