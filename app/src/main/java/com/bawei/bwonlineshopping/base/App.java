package com.bawei.bwonlineshopping.base;

import android.app.Application;
import android.content.Context;

import com.bawei.bwonlineshopping.utils.CrashHandler;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CrashHandler.getInstance().init(context);
        CrashReport.initCrashReport(getApplicationContext(),"fb6ebd1747",true);
    }
    public static Context getAppContext(){
        return context;
    }
}
