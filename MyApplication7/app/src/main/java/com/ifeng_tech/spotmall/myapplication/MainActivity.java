package com.ifeng_tech.spotmall.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.joker.annotation.PermissionsCustomRationale;
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;


public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private int        year;
    private int        month;//月份是从0开始算的.
    private int        day;

    private static final int AUDIO_CODE = 2;

    private static final int CALL_PHONE_CODE = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = (TextView) findViewById(R.id.textview);
        String apiHost = BuildConfig.API_HOST;

        textview.setText(apiHost);

//        datePicker = (DatePicker) findViewById(R.id.datepicker);

//        initData();

        // 通话记录
        findViewById(R.id.btn_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Permissions4M.get(MainActivity.this)
                        // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                        .requestForce(true)
                        // 是否支持 5.0 权限申请，默认为 false
                        .requestUnderM(false)
                        .requestPermissions(Manifest.permission.RECORD_AUDIO)
                        .requestCodes(AUDIO_CODE)
                        // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                        // 返回的 intent 是跳转至**系统设置页面**
                        .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                        // 返回的 intent 是跳转至**手机管家页面**
                        .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                        .request();
            }
        });
    }



    //====================================================================
    @PermissionsGranted(AUDIO_CODE)
    public void smsAndAudioGranted() {
        ToastUtil.show(this,"录音权限申请成功 in activity with annotation");
    }

    @PermissionsDenied(AUDIO_CODE)
    public void smsAndAudioDenied() {
        ToastUtil.show(this,"录音权限申请失败 in activity with annotation");
    }
    @PermissionsCustomRationale(AUDIO_CODE)
    public void smsAndAudioCustomRationale() {
        new AlertDialog.Builder(this)
                .setMessage("录音权限申请：\n我们需要您开启录音权限(in activity with annotation)")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Permissions4M.get(MainActivity.this)
                                .requestOnRationale()
                                .requestPermissions(Manifest.permission.RECORD_AUDIO)
                                .requestCodes(AUDIO_CODE)
                                .request();
                    }
                })
                .show();
    }


    //====================================================================
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        Permissions4M.onRequestPermissionsResult(MainActivity.this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//    public void initData(){
//        Calendar calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(MainActivity.this, year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    public void btn(View view) {

        Permissions4M.get(MainActivity.this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                .requestUnderM(false)
                .requestPermissions(Manifest.permission.CALL_PHONE)
                .requestCodes(CALL_PHONE_CODE)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                // 返回的 intent 是跳转至**系统设置页面**
                .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();

        //执行打电话到10086的操作
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + "10086");
        intent.setData(uri);
        //使用AS,这里会报红,编译能通过,只是提醒你要做Android6.0权限的适配
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);


    }

    //====================================================================
    @PermissionsGranted(CALL_PHONE_CODE)
    public void storageAndCallGranted() {
        ToastUtil.show(this,"读取通话记录权限授权成功 in activity with annotation");
    }

    @PermissionsDenied(CALL_PHONE_CODE)
    public void storageAndCallDenied() {
        ToastUtil.show(this,"读取通话记录权限授权失败 in activity with annotation");
    }

    @PermissionsCustomRationale(CALL_PHONE_CODE)
    public void storageAndCallNonRationale() {
        new AlertDialog.Builder(this)
                .setMessage("权限申请：\n我们需要您开启权限(in activity with annotation)")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Permissions4M.get(MainActivity.this)
                                .requestOnRationale()
                                .requestPermissions(Manifest.permission.RECORD_AUDIO)
                                .requestCodes(CALL_PHONE_CODE)
                                .request();
                    }
                })
                .show();
    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @SuppressWarnings("ResourceType")
//    public void rili(View view) {
//
//        Calendar calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Toast.makeText(MainActivity.this,year+"-"+(month+1)+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, dateSetListener, year, month, day);
//        dialog.getDatePicker().setCalendarViewShown(true);
//        dialog.getDatePicker().setSpinnersShown(false);
//        dialog.show();
//
//    }


}
