package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.mynew.activity.GuideActivity;
import com.example.mynew.utils.CacheUtils;

public class SplashActivity extends AppCompatActivity {
    //静态常量
    public static final String START_MAIN = "start_main";
    private RelativeLayout splahs_root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        pilot();

    }
    //动画进入
    private void pilot(){
        //渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        //alpha.setDuration(5000);
        alpha.setFillAfter(true);
        //缩放动画
        ScaleAnimation scale = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        scale.setFillAfter(true);
        //旋转动画
        RotateAnimation rotate = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setFillAfter(true);

        //动画的所有启动通过他实现,没有顺序
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(alpha);
        animation.addAnimation(scale);
        animation.addAnimation(rotate);
        //动画时间
        animation.setDuration(5000);
        //设置给哪个视图
        splahs_root.startAnimation(animation);
        //设置动画监听
        animation.setAnimationListener(new MyAnimation());

    }
    //判断动画结束走向
    class MyAnimation implements Animation.AnimationListener{
        //开始动画时候
        @Override
        public void onAnimationStart(Animation animation) {

        }
        //结束动画时候
        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否直接进入主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this,START_MAIN);
            System.out.println(isStartMain);
            if (isStartMain){
                //如果是
                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
            }else {
                //如果不是，进入引导页面
            }
            //关闭sqlash页面
            finish();
        }
        //动画重复时候
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void init(){
        splahs_root=findViewById(R.id.splahs_root);
    }
}
