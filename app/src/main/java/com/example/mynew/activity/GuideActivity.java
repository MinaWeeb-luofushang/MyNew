package com.example.mynew.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mynew.R;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private ViewPager view_pager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageViews;

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

        //准备数据
        int [] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
        };
        imageViews = new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);
            System.out.println(imageView+"恶心7788");
            //添加到集合里面
            imageViews.add(imageView);
        }
        //设置viewPage适配器
        view_pager.setAdapter(new MyPagerAdapter());
    }
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
