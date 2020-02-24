
package com.github.mikephil.chart_3_0_1v.formatter;

import com.github.mikephil.chart_3_0_1v.components.AxisBase;
import com.github.mikephil.chart_3_0_1v.data.Entry;
import com.github.mikephil.chart_3_0_1v.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * This IValueFormatter is just for convenience and simply puts a "%" sign after
 * each value. (Recommeded for PieChart)
 *
 * @author Philipp Jahoda
 */
public class PercentFormatter implements IValueFormatter, IAxisValueFormatter
{

    protected DecimalFormat mFormat;

    public PercentFormatter() {
        mFormat = new DecimalFormat("0.00");  // 更改百分比
    }

    /**
     * Allow a custom decimalformat
     *
     * @param format
     */
    public PercentFormatter(DecimalFormat format) {
        this.mFormat = format;
    }

    // IValueFormatter  可以设置隐藏百分比
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//        return mFormat.format(value) + " %";
        return mFormat.format(value)+"";
    }

    // IAxisValueFormatter
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
//        return mFormat.format(value) + " %";
        return mFormat.format(value) + "";
    }

    public int getDecimalDigits() {
        return 1;
    }
}
