package com.example.notepad_client.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：尚硅谷-杨光福 on 2016/11/15 14:40
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：缓存工具类
 */
public class SpUtils {


    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("qxmedia", Context.MODE_PRIVATE);
        //  Utils.v("getString"+sp.getString(key,""));
        return sp.getBoolean(key,false);
    }

    /**
     * 得到保持的String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("qxmedia", Context.MODE_PRIVATE);
      //  Utils.v("getString"+sp.getString(key,""));
        return sp.getString(key,"");
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("qxmedia", Context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }


    /**
     * 保持String类型数据
     * @param context 上下文
     * @param key
     * @param value 保持的值
     */
    public static void saveBoolean(Context context, String key, Boolean value) {
        SharedPreferences sp = context.getSharedPreferences("qxmedia", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 保持String类型数据
     * @param context 上下文
     * @param key
     * @param value 保持的值
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("qxmedia", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences("qxmedia", Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
    /**
     * 清除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences("qxmedia", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    /**
     * 移除某个key值已经对应的值 
     * @param context
     * @param key
     */
    public static void remove(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences("qxmedia",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key).commit();
    }

}
