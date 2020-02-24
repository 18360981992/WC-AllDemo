package com.dabao.retrofitmvpdemo.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dabao.retrofitmvpdemo.appliction.DashApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mypc on 2018/4/27.
 */

public class MyUtils {

    private static Toast toast;
    public static void setToast(String ss){
        if(toast==null){
            toast=Toast.makeText(DashApplication.getAppContext(),ss,Toast.LENGTH_SHORT);
        }
        toast.setText(ss);
        toast.show();
    }

    /**
     * 禁止EditText输入空格
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText){
        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.equals(" ")){
                    return "";
                }
                return null;


            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText){

        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if(matcher.find())return "";

                if(source.equals(" "))return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});  // 设置最大输入长度
    }

    /**
     * 禁止EditText输入特殊字符 以及空格  且设置长度为1
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat1(EditText editText){

        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if(matcher.find())return "";

                if(source.equals(" "))return "";

                return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});  // 设置最大输入长度
    }

    /**
     * 判断手机号是否符合规范
     * @param phoneNo 输入的手机号
     * @return
     */
    public static boolean isPhoneNumber(String phoneNo) {
//        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
//        return phoneNo.matches(telRegex);
        if(phoneNo.substring(0,1).equals("1")&&phoneNo.length()==11){
            return true;
        }else{
            return false;
        }
    }

    //邮箱验证
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断密码的格式 8-16位字母，数字组合密码
     * @param input
     * @return
     */
    public static boolean isPassWord(String input){
        Pattern p = Pattern.compile("^[^ ]{8,12}$");
        Matcher m = p.matcher(input);
        if(m.matches()){
            if(input.getBytes().length==input.length()){
                return true;
            }
        }
        return false;
    }



    // 判断是否符合身份证号码的规范
    public static boolean isIDCard(String IDCard) {
        if (IDCard != null) {
            String IDCardRegex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x|Y|y)$)";
            return IDCard.matches(IDCardRegex);
        }
        return false;
    }


    //字符串转指定格式时间  "yyyy-MM-dd" 到 "yyyy年MM月dd日"
    public static String getMyDate(String str) {
        return StringToDate(str, "yyyy-MM-dd", "yyyy年MM月dd日");
    }

    //字符串转指定格式时间   "yyyy年MM月dd日" 到 "yyyy-MM-dd"
    public static String getMyDate2(String str) {
        return StringToDate(str, "yyyy年MM月dd日", "yyyy-MM-dd");
    }

    // 时间转换
    public static String StringToDate(String dateStr, String dateFormatStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try{
            date = sdf.parse(dateStr);
        } catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatStr);

        return s.format(date);
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType){
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
            if (date == null) {
                return 0;
            } else {
                long currentTime = date.getTime(); // date类型转成long类型
                return currentTime;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @param file  文件
     * @return
     */
    public static byte[] Filebyte(File file){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * 检测
     * @return
     */
    public static String getSDPath(){
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.i("wc",
                    "SD card is not avaiable/writeable right now.");
            return null;
        }
        return sdStatus;

    }



    /**
     *  计算 下一年的时间
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, +1);//+1今天的时间加一年
        date = calendar.getTime();
        return date;
    }

    /**
     * 根据当前的状态来旋转箭头。
     */
    public static void rotateArrow(ImageView arrow, boolean flag) {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        // flag为true则向上
        if (flag) {
            fromDegrees = 0f;
            toDegrees = 180f;
        } else {
            fromDegrees = 180f;
            toDegrees = 0f;
        }
        //旋转动画效果   参数值 旋转的开始角度  旋转的结束角度  pivotX x轴伸缩值
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees,
                pivotX, pivotY);
        //该方法用于设置动画的持续时间，以毫秒为单位
        animation.setDuration(100);
        //设置重复次数
        //animation.setRepeatCount(int repeatCount);
        //动画终止时停留在最后一帧
        animation.setFillAfter(true);
        //启动动画
        arrow.startAnimation(animation);
    }


    /**
     * <pre>
     * 根据指定的日期字符串获取星期几
     * </pre>
     *
     * @param strDate 指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
     * @return week
     *         星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)
     */
    public static String getWeekByDateStr(String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

        switch (weekIndex)
        {
            case 1:
                week = "SUNDAY";
                break;
            case 2:
                week = "MONDAY";
                break;
            case 3:
                week = "TUESDAY";
                break;
            case 4:
                week = "WEDNESDAY";
                break;
            case 5:
                week = "THURSDAY";
                break;
            case 6:
                week = "FRIDAY";
                break;
            case 7:
                week = "SATURDAY";
                break;
        }
        return week;
    }

    // 获取拼接的email
    public static String getEmail(String email){
        String tou = email.substring(0, 2);
        String wei = email.substring(email.length()-10, email.length());
        int i=email.length()-12;  // 获取“*”的个数
        String ss="";
        for (int j=0;j<i;j++){
            ss=ss+"*";
        }
        String newEmail=tou+ss+wei;
        return newEmail;
    }


    /**
     * 判断服务是否开启
     *
     * @param mContext
     * @param className 这里是包名+类名 xxx.xxx.xxx.TestService
     * @return
     */
    public static boolean isServiceRunning(Context mContext,String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);

        if (!(serviceList.size()>0)) {
            return isRunning;
        }

        for (int i=0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }

        return isRunning;
    }

    /**
     * 将dp转成px
     */
    private static DisplayMetrics mMetrics;
    public static int convertDpToPixel(int dp) {

        if (mMetrics == null) {

            Log.e("MPChartLib-Utils",
                    "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before" +
                            " calling Utils.convertDpToPixel(...). Otherwise conversion does not " +
                            "take place.");
            return dp;
            // throw new IllegalStateException(
            // "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before
            // calling Utils.convertDpToPixel(...).");
        }

        DisplayMetrics metrics = mMetrics;
        int px = dp * (metrics.densityDpi / 160);
        return px;
    }

}
