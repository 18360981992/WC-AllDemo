package com.projecttest.administrator.hotboom.myapplication;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projecttest.administrator.hotboom.myapplication.adapter.MyGridViewAdapter;
import com.projecttest.administrator.hotboom.myapplication.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * 评论订单
 */
public class CommentOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView username,usertel,unsatisfy,satisfy,shopName,remark;
    private GridView gridView;
    private ImageView imageView,star1,star2,star3,star4,star5;
    private List<MaiYiBean>  unsatisfylist=new ArrayList<>();//不满意
    private List<MaiYiBean>  satisfylist=new ArrayList<>();//满意
    ArrayList<MaiYiBean> xuanzhongList = new ArrayList<>();  // 用来记录存储所选对象

    private CircleImageView shopImage;
    private int recordcount=0;   //记录点击的
    private int starcount=5;
    private int starcount1=1;
    ArrayList<ImgBean> stars = new ArrayList<>();
    private ImgBean imgBean1;
    private ImgBean imgBean2;
    private ImgBean imgBean3;
    private ImgBean imgBean4;
    private ImgBean imgBean5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sxs_activity_comment_order);


        initView();
        addData();   //添加数据
    }

    private void addData() {
        //不满意
        unsatisfylist.add(new MaiYiBean("联系沟通困难",false));
        unsatisfylist.add(new MaiYiBean("态度差",false));
        unsatisfylist.add(new MaiYiBean("路线不熟",false));
        unsatisfylist.add(new MaiYiBean("要好评",false));
        unsatisfylist.add(new MaiYiBean("货品不完好",false));
        unsatisfylist.add(new MaiYiBean("送达不通知",false));
        unsatisfylist.add(new MaiYiBean("仪表不整",false));
        unsatisfylist.add(new MaiYiBean("未到指定地点",false));
        unsatisfylist.add(new MaiYiBean("配送慢",false));
        //满意
        satisfylist.add(new MaiYiBean("货品完好",false));
        satisfylist.add(new MaiYiBean("仪表整洁",false));
        satisfylist.add(new MaiYiBean("传达工服",false));
        satisfylist.add(new MaiYiBean("风雨无阻",false));
        satisfylist.add(new MaiYiBean("快速准时",false));
        satisfylist.add(new MaiYiBean("热情礼貌",false));

    }




    private void initView() {
        username=(TextView)findViewById(R.id.sxs_userName);  //用户名称
        imageView=(ImageView)findViewById(R.id.finsh);        //返回
        usertel=(TextView)findViewById(R.id.sxs_usertel);    //用户电话
        unsatisfy=(TextView)findViewById(R.id.unsatisfy);    //不满意
        satisfy=(TextView)findViewById(R.id.satisfy);        //满意
        gridView=(GridView)findViewById(R.id.gridview);
        unsatisfy.setOnClickListener(this);  //不满意
        satisfy.setOnClickListener(this);     //满意
        imageView.setOnClickListener(this);   //返回
        shopImage=(CircleImageView)findViewById(R.id.shopimage);//店家头像
        shopName= (TextView) findViewById(R.id.shop_name);  //店家名称
        remark=(TextView)findViewById(R.id.remark);        //星级评语
        star1=(ImageView)findViewById(R.id.star1);         //星级
        star2=(ImageView)findViewById(R.id.star2);
        star3=(ImageView)findViewById(R.id.star3);
        star4=(ImageView)findViewById(R.id.star4);
        star5=(ImageView)findViewById(R.id.star5);
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        star3.setOnClickListener(this);
        star4.setOnClickListener(this);
        star5.setOnClickListener(this);

        stars.clear();

        imgBean1 = new ImgBean(star1, star1Flag);
        imgBean2 = new ImgBean(star2, star2Flag);
        imgBean3 = new ImgBean(star3,star3Flag);
        imgBean4 = new ImgBean(star4,star4Flag);
        imgBean5 = new ImgBean(star5,star5Flag);
        stars.add(imgBean1);
        stars.add(imgBean2);
        stars.add(imgBean3);
        stars.add(imgBean4);
        stars.add(imgBean5);
    }

    boolean star1Flag=true;
    boolean star2Flag=true;
    boolean star3Flag=true;
    boolean star4Flag=true;
    boolean star5Flag=true;



    // 五角星的bean类
    public class ImgBean{
        ImageView img;
        boolean flag;

        public ImgBean(ImageView img, boolean flag) {
            this.img = img;
            this.flag = flag;
        }

        public ImageView getImg() {
            return img;
        }

        public boolean getFlag() {
            return flag;
        }

        public void setImg(ImageView img) {
            this.img = img;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

    // 满意/不满意的bean类
    public class MaiYiBean{
        String text;
        boolean flag;

        public MaiYiBean(String text, boolean flag) {
            this.text = text;
            this.flag = flag;
        }

        public String getText() {
            return text;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //不满意的点击事件
            case R.id.unsatisfy:
                unsatisfy.setTextColor(Color.parseColor("#FF94D463"));
                satisfy.setTextColor(Color.parseColor("#FF0E0D0D"));
                xuanzhongList.clear();  // 先清空选中集合
                // 当点击不满意的时候，将对方也就是满意的所有数据初始化  状态全部改为false
                for (int i=0;i<satisfylist.size();i++){
                    MaiYiBean maiYiBean = satisfylist.get(i);
                    maiYiBean.setFlag(false);
                    satisfylist.set(i,maiYiBean);
                }

                  final MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter(unsatisfylist, this);
                   gridView.setAdapter(myGridViewAdapter);
//                   gridView.setOnItemClickListener(this);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MaiYiBean maiYiBean = unsatisfylist.get(position);
                        if(maiYiBean.isFlag()){ // 判断当前状态 true==点亮
                            maiYiBean.setFlag(false);
                            boolean contains = xuanzhongList.contains(maiYiBean);
                            if(contains){
                                int i = xuanzhongList.indexOf(maiYiBean);
                                xuanzhongList.remove(i);
                            }
                        }else{
                            maiYiBean.setFlag(true);
                            xuanzhongList.add(maiYiBean);
                        }
                        unsatisfylist.set(position,maiYiBean);
                        myGridViewAdapter.notifyDataSetChanged();

                        Toast.makeText(CommentOrderActivity.this,"选中集合数目==="+xuanzhongList.size(),Toast.LENGTH_SHORT).show();

                    }
                });

                break;
            //满意的点击事件
            case R.id.satisfy:
                satisfy.setTextColor(Color.parseColor("#FF94D463"));
                unsatisfy.setTextColor(Color.parseColor("#FF0E0D0D"));
                xuanzhongList.clear();  // 先清空选中集合

                // 当点击满意的时候，将对方也就是不满意的所有数据初始化  状态全部改为false
                for (int i=0;i<unsatisfylist.size();i++){
                    MaiYiBean maiYiBean = unsatisfylist.get(i);
                    maiYiBean.setFlag(false);
                    unsatisfylist.set(i,maiYiBean);
                }

                final MyGridViewAdapter myGridViewAdapter1 = new MyGridViewAdapter(satisfylist, this);
                gridView.setAdapter(myGridViewAdapter1);

//                gridView.setOnItemClickListener(this);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MaiYiBean maiYiBean = satisfylist.get(position);
                        if(maiYiBean.isFlag()){ // 判断当前状态 true==点亮
                            maiYiBean.setFlag(false);
                            boolean contains = xuanzhongList.contains(maiYiBean);
                            if(contains){
                                int i = xuanzhongList.indexOf(maiYiBean);
                                xuanzhongList.remove(i);
                            }
                        }else{
                            maiYiBean.setFlag(true);
                            xuanzhongList.add(maiYiBean);
                        }
                        satisfylist.set(position,maiYiBean);
                        myGridViewAdapter1.notifyDataSetChanged();
                        Toast.makeText(CommentOrderActivity.this,"选中集合数目==="+xuanzhongList.size(),Toast.LENGTH_SHORT).show();
                    }
                });

                break;
              //返回
            case R.id.finsh:
                this.finish();
                break;
            //一颗星
            case R.id.star1:
//                ++starcount1;
//                if (starcount-4==1){
//                    if(starcount1%2==0){
//                        Drawable drawable = this.getResources().getDrawable(R.drawable.star2);
//                        star1.setBackground(drawable);
//                        remark.setText("“很差”");
//                    }else{
//                        Drawable drawable = this.getResources().getDrawable(R.drawable.star1);
//                        star1.setBackground(drawable);
//                        remark.setText("");
//                    }
//
//                }else{
//                    Drawable drawable = this.getResources().getDrawable(R.drawable.star1);
//                    star1.setBackground(drawable);
//                    star2.setBackground(drawable);
//                    star3.setBackground(drawable);
//                    star4.setBackground(drawable);
//                    star5.setBackground(drawable);
//                    remark.setText("");
//                }
                setXingXing(star1Flag,imgBean1);

                break;
            case R.id.star2:
//                if(starcount-3==2){
//                    Drawable drawable = this.getResources().getDrawable(R.drawable.star2);
//                    star2.setBackground(drawable);
//                    remark.setText("“一般”");
//                }
                setXingXing(star2Flag,imgBean2);
                break;
            case R.id.star3:
//                if(starcount-2==3){
//                    Drawable drawable = this.getResources().getDrawable(R.drawable.star2);
//                    star3.setBackground(drawable);
//                    remark.setText("“满意”");
//                }

                setXingXing(star3Flag,imgBean3);
                break;
            case R.id.star4:
//                if(starcount-1==4){
//                    Drawable drawable = this.getResources().getDrawable(R.drawable.star2);
//                    star4.setBackground(drawable);
//                    remark.setText("“非常满意”");
//                }
                setXingXing(star4Flag,imgBean4);
                break;
            case R.id.star5:
//                if(starcount-0==5){
//                    Drawable drawable = this.getResources().getDrawable(R.drawable.star2);
//                    star5.setBackground(drawable);
//                    remark.setText("“无可挑剔”");
//                }
                setXingXing(star5Flag,imgBean5);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setXingXing(boolean flag,ImgBean imgBean1) {
        if(flag){  // 根据状态值 判断当前的状态 是亮 还是暗  默认是暗的 状态==true

            for (int i=0;i<stars.size();i++){
                int i1 = stars.indexOf(imgBean1); // 根据对象取出下标
                ImgBean imgBean = stars.get(i);
                if(i<=i1){  // 判断 只要集合中的下标小于当前点击对象的下标 就让它变色
                    imgBean.getImg().setBackground(getResources().getDrawable(R.drawable.star2));
                    imgBean.setFlag(false);
                    stars.set(i,imgBean);
                }else{
                    imgBean.getImg().setBackground(getResources().getDrawable(R.drawable.star1));
                    imgBean.setFlag(true);
                    stars.set(i,imgBean);
                }

            }

        }else{  // 当状态为点亮的时候  状态==false
            for (int i=0;i<stars.size();i++){
                    int i1 = stars.indexOf(imgBean1); // 根据对象取出下标
                    ImgBean imgBean = stars.get(i);
                    if(i<i1){  // 判断 只要集合中的下标小于当前点击对象的下标 就让它变色
                        imgBean.getImg().setBackground(getResources().getDrawable(R.drawable.star2));
                        imgBean.setFlag(true);
                        stars.set(i,imgBean);
                    }else{
                        imgBean.getImg().setBackground(getResources().getDrawable(R.drawable.star1));
                        imgBean.setFlag(false);
                        stars.set(i,imgBean);
                    }
            }


        }
    }


//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        ++recordcount;
//        Drawable drawable = this.getResources().getDrawable(R.drawable.textbg2);
//        Drawable drawable1 = this.getResources().getDrawable(R.drawable.textbg);
//        TextView tv = view.findViewById(R.id.tv_gridview);
//        if(recordcount%2==0){
//            tv.setBackground(drawable1);
//            int color = this.getResources().getColor(R.color.black3);
//            tv.setTextColor(color);
//         }else{
//            tv.setBackground(drawable);
//            int color = this.getResources().getColor(R.color.colorgreen1);
//            tv.setTextColor(color);
//        }
//
//    }

}
