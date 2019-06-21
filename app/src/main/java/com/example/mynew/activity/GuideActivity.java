package com.example.mynew.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mynew.R;
import com.example.mynew.SplashActivity;
import com.example.mynew.utils.CacheUtils;
import com.example.mynew.utils.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private int widthdpi;
    private int heightdpi;


    private ViewPager view_pager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageViews;
    private ImageView iv_red_point;
    //获取两点之间的间距
    private int leftmax=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();

    }
    private void init(){
        view_pager= findViewById(R.id.view_pager);
        btn_start_main=findViewById(R.id.btn_start_main);
        ll_point_group=findViewById(R.id.ll_point_group);
        iv_red_point=findViewById(R.id.iv_red_point);

        //准备数据
        int [] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3,
        };
        widthdpi = DensityUtil.dip2px(this,10);
        heightdpi = DensityUtil.dip2px(this,10);
        imageViews = new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);
            //添加到集合里面
            imageViews.add(imageView);
            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            //显示
            //单位像素
            //将dp转成像素
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,heightdpi);
             //设置间距
            if (i!=0){
                params.leftMargin=widthdpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局里面
            ll_point_group.addView(point);
        }
        //设置viewPage适配器
        view_pager.setAdapter(new MyPagerAdapter());
        //根据view的生命周期，当到onDraw的时候,获取height width  //视图数TreeObserver 监听
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        view_pager.addOnPageChangeListener(new MyONPageChangeListener());

        //设置点击事件 关闭引导页
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存进入过的主页面
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);
                //掉转主页面
                Intent intent = new Intent(GuideActivity.this,Main2Activity.class);
                startActivity(intent);
                //关闭当前引导页
                finish();
            }
        });

    }
    //监听滑动界面
    private class MyONPageChangeListener implements ViewPager.OnPageChangeListener {
        /***
         * 页面滚动回调
         * position 滑动位置
         * positionOffset 页面滑动百分比
         * positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点之间移动的距离=屏幕滑动百分比*间距
            int leftmargins = (int) (positionOffset*leftmax);
            //两点滑动距离对应的坐标 = 原来的位置+两点移动的距离
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = position*leftmax+leftmargins;
            iv_red_point.setLayoutParams(params);
        }
        /**
         * 当页面被选中的时候回调
         * position 位置
         * */
        @Override
        public void onPageSelected(int position) {
            //是否跳到了最后的界面
                if (position==imageViews.size()-1){
                    btn_start_main.setVisibility(View.VISIBLE);
                }else {
                    btn_start_main.setVisibility(View.GONE);
                }
        }
        /**
         * 当滑动状态变化时回调  三个状态
         * */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    //获取两点之间的间距
    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //获取两点之间的间距
            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
        }
    }
    //适配器
    class MyPagerAdapter extends PagerAdapter{
        //返回数据的个数
        @Override
        public int getCount() {
            return imageViews.size();
        }
        /**
         * container 容器就是view Pager
         * position 创建页面的位置
         * return 返回创建当前页面关系的值
         * */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器重
            container.addView(imageView);
            return imageView;
        }
        /**
         * 判断
         * view 当前视图
         * object instantiateItem返回的结果值
         * */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//            return View==imageViews.get(Integer.parseInt((String) object));
            return view==object;
        }
        /**
        * 销毁页面
         * container 要销毁的页面
         * position 要销毁页面的位置
         * object 要销毁的数据
        * */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

}
