package com.example.mynew.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.mynew.SplashActivity;

//缓存软件的一些参数和数据
public class CacheUtils {

    /*
    * 得到缓存
    * @context 上下文
    * @key
    * */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getBoolean(key,true);
    }
}
