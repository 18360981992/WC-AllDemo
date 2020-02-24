package com.dabao.retrofitmvpdemo.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.dabao.retrofitmvpdemo.R;
import com.dabao.retrofitmvpdemo.appliction.DashApplication;
import com.dabao.retrofitmvpdemo.presenter.MyPresenter;
import com.dabao.retrofitmvpdemo.util.LogUtils;



/**
 *
 * @param <V>  v是activity
 * @param <T>  t是p层
 */
public abstract class BaseMVPActivity<V,T extends MyPresenter<V>> extends AppCompatActivity {

    public T myPresenter;
    //将子类共有方法抽成公有，，，因为每一个Activity都会需要new一个P层，所以将new的这个过程，抽成抽象方法才是最佳良策
    public abstract T initPresenter();

    DashApplication application;

    BaseMVPActivity oContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mvp);
        myPresenter=initPresenter();

        if (application == null) {
            // 得到Application对象
            application = (DashApplication) getApplication();
        }
        oContext = this;// 把当前的上下文对象赋值给BaseActivity
        addActivity();// 调用添加方法
    }

    // 添加Activity方法
    public void addActivity() {
        application.addActivity_(oContext);// 调用myApplication的添加Activity方法
    }

    //销毁所有Activity方法
    public void removeALLActivity() {
        application.removeALLActivity_();// 调用myApplication的销毁所有Activity方法
    }

    //在获取焦点是将当前对象与p层绑定
    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.attch((V) this);

    }


    //在页面销毁时，调用p层方法，将view对象置空，方便垃圾回收
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPresenter.setonDestroy();
    }

    public interface BaseMVP_JieKou{
        void chuan();
    }

    public static BaseMVP_JieKou baseMVP_JieKou;

    public static void setBaseMVP_JieKou(BaseMVP_JieKou baseMVP_JieKou) {
        BaseMVPActivity.baseMVP_JieKou = baseMVP_JieKou;
    }

    /**
     * 获取本地软件 versionName
     */
    public static String getLocalVersion() {
        String localVersionName="";
        try {
            PackageInfo packageInfo = DashApplication.getAppContext()
                    .getPackageManager()
                    .getPackageInfo(DashApplication.getAppContext().getPackageName(), 0);
             localVersionName = packageInfo.versionName;
//             localVersionCode = packageInfo.versionCode;  //获取code值
            LogUtils.i("jba", "本软件的版本号。。" + localVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersionName;
    }

    /**
     * 获取本地软件 versionCode
     */
    public static int getLocalVersionCode() {
        int localVersionCode=0;
        try {
            PackageInfo packageInfo = DashApplication.getAppContext()
                    .getPackageManager()
                    .getPackageInfo(DashApplication.getAppContext().getPackageName(), 0);
//            localVersionName = packageInfo.versionName;
             localVersionCode = packageInfo.versionCode;  //获取code值
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersionCode;
    }


    // eidttext的获焦并软件盘的显示
    public void setEditTexttHuo(EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.findFocus();
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);// 显示输入法
    }

}
