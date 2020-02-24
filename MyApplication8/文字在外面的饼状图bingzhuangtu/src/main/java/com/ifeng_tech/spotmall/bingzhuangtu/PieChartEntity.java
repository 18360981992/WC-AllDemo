package com.ifeng_tech.spotmall.bingzhuangtu;

import android.graphics.Color;

import com.github.mikephil.chart_3_0_1v.animation.Easing;
import com.github.mikephil.chart_3_0_1v.charts.PieChart;
import com.github.mikephil.chart_3_0_1v.components.Legend;
import com.github.mikephil.chart_3_0_1v.data.PieData;
import com.github.mikephil.chart_3_0_1v.data.PieDataSet;
import com.github.mikephil.chart_3_0_1v.data.PieEntry;
import com.github.mikephil.chart_3_0_1v.formatter.PercentFormatter;

import java.util.List;

/**
 * 饼状图
 * Created by jin
 */

public class PieChartEntity  {

    private PieChart mChart;
    private List<PieEntry> mEntries;
    private String[] labels;
    private int[] mPieColors;
    private int mValueColor;
    private float mTextSize;
    private PieDataSet.ValuePosition mValuePosition;

    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          int []chartColor,  float textSize, int valueColor, PieDataSet.ValuePosition valuePosition) {
        this.mChart = chart;
        this.mEntries = entries;
        this.labels= labels;
        this.mPieColors = chartColor;
        this.mTextSize= textSize;
        this.mValueColor = valueColor;
        this.mValuePosition = valuePosition;
        initPieChart();
    }

    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          int[] chartColor,  float textSize, int valueColor) {
        this(chart, entries, labels, chartColor, textSize, valueColor, PieDataSet.ValuePosition.INSIDE_SLICE);
    }

    private void initPieChart() {
//        mChart.setRotationEnabled(false);
//        mChart.setUsePercentValues(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setDrawCenterText(false);
        mChart.getDescription().setEnabled(false);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(false);  // 禁止旋转
        mChart.setHighlightPerTapEnabled(true);
        mChart.setDrawEntryLabels(true);
        setChartData();
        mChart.animateY(1000, Easing.EasingOption.EaseInOutQuad); // 添加动画

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(20f);
        l.setYOffset(20f);

        // entry label styling 入口标签造型 控制圆外的文字  文字颜色在这边改不了，去底层改，采用硬编码  PieChartRenderer
//        mChart.setEntryLabelColor(mValueColor);
        mChart.setEntryLabelTextSize(mTextSize);
        mChart.setExtraOffsets(15, 10, 10, 10); // 边距  上 右 下 左

        // 设置初始旋转角度
        mChart.spin(1000, mChart.getRotationAngle(), mChart.getRotationAngle() - 40, Easing.EasingOption
                .EaseInCubic);
    }

    public void setHoleDisenabled () {
        mChart.setDrawHoleEnabled(false);
    }

    /**
     * 中心圆是否可见
     * @param holeColor 中心圆颜色
     * @param holeRadius 半径
     * @param transColor 透明圆颜色
     * @param transRadius 透明圆半径
     */
    public void setHoleEnabled (int holeColor, float holeRadius, int transColor, float transRadius) {
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(holeColor);
        mChart.setTransparentCircleColor(transColor);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(holeRadius);
        mChart.setTransparentCircleRadius(transRadius);
    }

    private void setChartData() {

        PieDataSet dataSet = new PieDataSet(mEntries, "");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(mPieColors);
        dataSet.setYValuePosition(mValuePosition);
        dataSet.setXValuePosition(mValuePosition);
//        dataSet.setValueLineColor(mValueColor);

        // entry label styling 入口标签造型 控制圆外的线
        dataSet.setSelectionShift(15f);
        dataSet.setValueLinePart1Length(0.6f);  // 设置圆外线的长度
        dataSet.setValueLineColor(Color.GRAY);  // 设置圆外线的颜色

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());  // 绘制百分比 不能去掉，如果去掉也只是去掉%号，想要隐藏必须去改代码
        // 这是干啥的，不知道
//        data.setValueTextSize(mTextSize);
//        data.setValueTextColor(mValueColor);
//        data.setValueTextColors(ints);

        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    /**
     * <p>说明文字是否可见</p>
     * @param enabled true 可见,默认可见
     *
     * 角落上的说明文字
     */
    public void setLegendEnabled(boolean enabled) {
        mChart.getLegend().setEnabled(enabled);
        mChart.invalidate();
    }

    public void setPercentValues (boolean showPercent) {
        mChart.setUsePercentValues(showPercent);
    }
}
