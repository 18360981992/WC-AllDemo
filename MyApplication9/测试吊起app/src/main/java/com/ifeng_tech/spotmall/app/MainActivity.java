package com.ifeng_tech.spotmall.app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = getIntent().getData();
        Log.i("jba","uri==="+uri);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("xl://goods:8888/goodsDetail?goodsId=10011002"));
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isValid = !activities.isEmpty();
                if (isValid) {
                    startActivity(intent);
                }
            }
        });

        Map<String, String> map = new HashMap<>();
        map.put("username","张三");
        map.put("password","123456");
        map.put("scode","的手机号多少简单说");

        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });

        for(Map.Entry<String,String> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
    }
}
