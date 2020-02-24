package com.ifeng.androi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.jungly.gridpasswordview.GridPasswordView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acTextView;
    private String[] res ={"aac","aab","aad","bbc","bcd","dab","1111111"};
    public ArrayList<PhoneContact> mList;
    public GridPasswordView gpv_normal;
    boolean isFirst = true;
    String firstPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<15;i++){
            list.add("000000"+i);
        }


        /**
         * 1、初始化控件
         * 2、需要一个适配器---适配当前文本框输入的内容
         * 3、初始化数据源---匹配文本框输入的内容
         * 4、将adapter与当前AutoCompleteTextView绑定
         *
         */
        buildAppData();
        findView();

        gpv_normal = (GridPasswordView) findViewById(R.id.gpv_normal);

        gpv_normal.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
//                if (psw.length() == 6 && isFirst){
//                    gpv_normal.clearPassword();
//                    isFirst = false;
//                    firstPwd = psw;
//                }else if (psw.length() == 6 && !isFirst){
//                    if (psw.equals(firstPwd)){
//                        Log.d("MainActivity", "The password is: " + psw);
//                    }else {
//                        Log.d("MainActivity", "password doesn't match the previous one, try again!");
//                        gpv_normal.clearPassword();
//                        isFirst = true;
//                    }
//                }
            }

            @Override
            public void onInputFinish(String psw) {
                Log.d("MainActivity", "The password is: " + psw);
            }
        });

    }

    private void buildAppData() {
        String[] names = { "abc", "allen", "bird", "bike", "book", "cray",
                "david", "demon", "eclipse", "felling", "frank", "google",
                "green", "hill", "hook","jin zhiwen", "jack", "jay", "king","kevin","kobe",
                "lily", "lucy", "mike", "nike", "nail", "open","open cv",
                "panda", "pp", "queue", "ray allen", "risk", "tim cook","T-MAC","tony allen",
                "x man", "x phone", "yy", "world", "w3c", "zoom","zhu ziqing"};

        mList = new ArrayList<PhoneContact>();

        for (int i = 0; i < names.length; i++) {
            PhoneContact pc = new PhoneContact(100 + i, names[i], "1861234567"
                    + i, names[i].concat("@gmail.com"));
            mList.add(pc);
        }

    }

    private void findView() {
        acTextView=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        acTextView.setThreshold(1);
        MyAdapter mAdapter = new MyAdapter(mList, getApplicationContext());
        acTextView.setAdapter(mAdapter);


        acTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneContact pc = mList.get(position);
                acTextView.setText(pc.getName()+" "+pc.getS());
            }
        });
    }
}
