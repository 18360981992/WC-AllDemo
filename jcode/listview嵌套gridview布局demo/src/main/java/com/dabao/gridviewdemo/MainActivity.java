package com.dabao.gridviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyListView myListView;
    List<Bean> list = new ArrayList<>();
    List<Bean.Item_Bean> item_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        myListView.setAdapter(new ExAdapter(MainActivity.this,list));

    }

    private void initView() {
        myListView = (MyListView) findViewById(R.id.myListView);

        for (int i=0;i<3;i++){
            item_list.clear();
            for (int j=0;j<8;j++){
                Bean.Item_Bean item_bean = new Bean.Item_Bean();
                item_bean.setImg(R.drawable.fx_weituo);
                item_bean.setName("item"+j);
                item_list.add(item_bean);
            }
            Bean bean = new Bean();
            bean.setTitle("title"+i);
            bean.setList(item_list);
            list.add(bean);
        }

//        Gson gson = new Gson();
//        String s = gson.toJson(list);
//        Log.i("jba","list==="+s);
    }


}
