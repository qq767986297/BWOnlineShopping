package com.bawei.bwonlineshopping.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bawei.bwonlineshopping.R;

/**
 * Time: 2020/3/6
 * Author: 王冠华
 * Description:
 */
public class SPUtils {
    public static void putInt(Context context,String name,String key,int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key,value);

        edit.commit();
    }

    public static void putString(Context context,String name,String key,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,value);
        edit.commit();
    }
    public static String getString(Context context,String name,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
    public static void getBoolean(Context context,String name,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key,false);
        edit.commit();
    }
    public static boolean puttBoolean(Context context,String name,String key,boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }
}
