package com.ifeng_tech.spotmall.bingzhuangtu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.chart_3_0_1v.charts.PieChart;
import com.github.mikephil.chart_3_0_1v.data.PieDataSet;
import com.github.mikephil.chart_3_0_1v.data.PieEntry;

import java.util.ArrayList;

/**
 *
 * 绘制饼状图的百分比  DataRenderer 类
 *
 * 改变标签文字颜色 PieChartRenderer
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * #E8571F  转赠
         *
         * #567CEC 仓储
         *
         * #0FA9A7 鉴定
         *
         * #E8791F 提货
         */
//        int[] colors = {Color.parseColor("#E8571F"), Color.parseColor("#567CEC"), Color.parseColor("#0FA9A7"), Color.parseColor("#E8791F"), Color.parseColor("#f1ea56"),
//                Color.parseColor("#f49468"), Color.parseColor("#d5932c"), Color.parseColor("#34b5cc"), Color.parseColor("#8169c6"), Color.parseColor("#ca4561"), Color.parseColor("#fee335")};

        int[] colors = {Color.parseColor("#567CEC"), Color.parseColor("#0FA9A7"), Color.parseColor("#E8791F"), Color.parseColor("#E8571F")};
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        PieEntry pieEntry1 = new PieEntry(150, "仓储费");
        entries.add(pieEntry1);

        PieEntry pieEntry2 = new PieEntry(100, "鉴定费");
        entries.add(pieEntry2);

        PieEntry pieEntry3 = new PieEntry(80, "提货手续费");
        entries.add(pieEntry3);


        PieEntry pieEntry = new PieEntry(50, "转赠手续费");
        entries.add(pieEntry);

        if (entries.size() != 0) {
            PieChart new_pie_chart = (PieChart) findViewById(R.id.new_pie_chart);
            PieChartEntity pieChartEntity = new PieChartEntity(new_pie_chart, entries, new String[]{"", "", ""}, colors, 12f, Color.RED, PieDataSet.ValuePosition.OUTSIDE_SLICE);
            pieChartEntity.setHoleEnabled(Color.TRANSPARENT, 60f, Color.TRANSPARENT, 10f);
            pieChartEntity.setLegendEnabled(false);
            pieChartEntity.setPercentValues(true);

        }
    }
}
