package com.example.mynew.utils;


import android.content.Context;
import android.content.SharedPreferences;


//缓存软件的一些参数和数据
public class CacheUtils {

    /*
    * 得到缓存
    * @context 上下文
    * @key
    * */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }
    /**
     * 保存软件的参数
     * contextWrapper 上下文
     * */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
}
