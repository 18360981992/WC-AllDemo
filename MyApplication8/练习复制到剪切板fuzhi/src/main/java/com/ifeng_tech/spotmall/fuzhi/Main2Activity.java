package com.ifeng_tech.spotmall.fuzhi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ListView listview;
    List<TextBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

        listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
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
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=View.inflate(Main2Activity.this,R.layout.list_item,null);
                }
                TextView item_text = convertView.findViewById(R.id.item_text);
                ImageView item_img = convertView.findViewById(R.id.item_img);
                item_text.setText(list.get(position).getText());
                item_img.setImageResource(list.get(position).getImg());

                // 点击事件的拦截 自我消费
                item_text.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Toast.makeText(Main2Activity.this,"点击textview控件"+list.get(position).getText(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                return convertView;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this,"点击listview条目"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);

        for (int i=0;i<15;i++){
            list.add(new TextBean("条目"+i , R.drawable.roant));
        }
    }

    class TextBean{
        String text;
        int img;

        public void setText(String text) {
            this.text = text;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getText() {

            return text;
        }

        public int getImg() {
            return img;
        }

        public TextBean(String text, int img) {

            this.text = text;
            this.img = img;
        }
    }


}
