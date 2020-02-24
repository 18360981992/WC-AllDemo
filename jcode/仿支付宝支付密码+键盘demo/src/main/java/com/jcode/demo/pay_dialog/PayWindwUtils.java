package com.jcode.demo.pay_dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcode.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/9/12.
 */

public class PayWindwUtils {
    private static List<PayBean> payList;

    //标记取值的下标
    private static int keyLocation = 0;

    //结果
    private static String result = "";

    //
    private static String num1 = "";
    private static String num2 = "";
    private static String num3 = "";
    private static String num4 = "";
    private static String num5 = "";
    private static String num6 = "";

    public static void initList() {
        payList = new ArrayList<>();
        PayBean payBean1 = new PayBean();
        payBean1.setKey("");
        payBean1.setNum("1");
        payList.add(payBean1);

        PayBean payBean2 = new PayBean();
        payBean2.setKey("ABC");
        payBean2.setNum("2");
        payList.add(payBean2);

        PayBean payBean3 = new PayBean();
        payBean3.setKey("DEF");
        payBean3.setNum("3");
        payList.add(payBean3);

        PayBean payBean4 = new PayBean();
        payBean4.setKey("GHI");
        payBean4.setNum("4");
        payList.add(payBean4);

        PayBean payBean5 = new PayBean();
        payBean5.setKey("JKL");
        payBean5.setNum("5");
        payList.add(payBean5);

        PayBean payBean6 = new PayBean();
        payBean6.setKey("MNO");
        payBean6.setNum("6");
        payList.add(payBean6);

        PayBean payBean7 = new PayBean();
        payBean7.setKey("PQRS");
        payBean7.setNum("7");
        payList.add(payBean7);

        PayBean payBean8 = new PayBean();
        payBean8.setKey("TUV");
        payBean8.setNum("8");
        payList.add(payBean8);

        PayBean payBean9 = new PayBean();
        payBean9.setKey("WXYZ");
        payBean9.setNum("9");
        payList.add(payBean9);

        PayBean payBean10 = new PayBean();
        payBean10.setKey("");
        payBean10.setNum("");
        payList.add(payBean10);

        PayBean payBean11 = new PayBean();
        payBean11.setKey("");
        payBean11.setNum("0");
        payList.add(payBean11);

        PayBean payBean12 = new PayBean();
        payBean12.setKey("delete");
        payBean12.setNum("D");
        payList.add(payBean12);

    }


    //支付弹出框
    public static void showPayDialog(final Context context, final ResultCallBack callBack) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();

        result = "";
        keyLocation = 0;

        dialog.getWindow().setWindowAnimations(R.style.dialog_setting);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
        dialog.getWindow().setContentView(R.layout.item_pay_window);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alphaBlack);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);


        Window view = dialog.getWindow();


        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);

        final TextView tvNum1 = (TextView) view.findViewById(R.id.tv_1);
        final TextView tvNum2 = (TextView) view.findViewById(R.id.tv_2);
        final TextView tvNum3 = (TextView) view.findViewById(R.id.tv_3);
        final TextView tvNum4 = (TextView) view.findViewById(R.id.tv_4);
        final TextView tvNum5 = (TextView) view.findViewById(R.id.tv_5);
        final TextView tvNum6 = (TextView) view.findViewById(R.id.tv_6);

        initList();

        PayAdapter payAdapter = new PayAdapter(payList, context);
        GridView gridView = (GridView) view.findViewById(R.id.grid_pay);

        gridView.setAdapter(payAdapter);


        // 点击取消
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //赋值
                if (position != 11&&position!=9) {
                    if (keyLocation < 6) {
                        keyLocation++;
                        switch (keyLocation) {
                            case 1:
                                tvNum1.setBackgroundResource(R.drawable.black_dot);
                                num1 = payList.get(position).getNum();
                                break;
                            case 2:
                                tvNum2.setBackgroundResource(R.drawable.black_dot);
                                num2 = payList.get(position).getNum();
                                break;
                            case 3:
                                tvNum3.setBackgroundResource(R.drawable.black_dot);
                                num3 = payList.get(position).getNum();
                                break;
                            case 4:
                                tvNum4.setBackgroundResource(R.drawable.black_dot);
                                num4 = payList.get(position).getNum();
                                break;
                            case 5:
                                tvNum5.setBackgroundResource(R.drawable.black_dot);
                                num5 = payList.get(position).getNum();
                                break;
                            case 6:
                                tvNum6.setBackgroundResource(R.drawable.black_dot);
                                num6 = payList.get(position).getNum();
                                break;
                        }
                    }



                    if (keyLocation == 6) {
                        result += num1 + num2 + num3 + num4 + num5 + num6;
                        callBack.setString(result, dialog);
                    }

                    //删除
                } else if(position==11){
                    if (keyLocation >= 1) {
                        switch (keyLocation) {
                            case 1:
                                tvNum1.setBackgroundResource(R.color.white);
                                num1 = "";
                                break;
                            case 2:
                                tvNum2.setBackgroundResource(R.color.white);
                                num2 = "";
                                break;
                            case 3:
                                tvNum3.setBackgroundResource(R.color.white);
                                num3 = "";
                                break;
                            case 4:
                                tvNum4.setBackgroundResource(R.color.white);
                                num4 = "";
                                break;
                            case 5:
                                tvNum5.setBackgroundResource(R.color.white);
                                num5 = "";
                                break;
                            case 6:
                                tvNum6.setBackgroundResource(R.color.white);
                                num6 = "";
                                break;
                        }
                        keyLocation--;
                    }
                }
            }
        });


    }
}
