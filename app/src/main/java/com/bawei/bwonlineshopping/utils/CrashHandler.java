package com.bawei.bwonlineshopping.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Time: 2020/3/5
 * Author: 王冠华
 * Description:
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{

    private static CrashHandler INSTANCE=new CrashHandler();
    private Thread.UncaughtExceptionHandler handler;
    private Context mcontext;
    //储存信息
    HashMap<String, String> map = new HashMap<>();
    //格式化日期
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public CrashHandler() {

    }
    //初始化
    public void init(Context context){
        mcontext=context;
        // 获取系统默认的UncaughtException处理器
        handler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(!handleException(e)&&handler!=null){
            // 如果用户没有处理则让系统默认的异常处理器来处理
            // 基本就等于直接应用崩溃
            handler.uncaughtException(t,e);
        }
    }
    private boolean handleException(Throwable ex){
        //没有崩溃信息，就return false，告知系统崩出去
        if(ex==null){
            return false;
        }
        Toast.makeText(mcontext, "程序异常,即将退出", Toast.LENGTH_SHORT).show();
        //保存日志文件
        saveCrashInfo2File(ex);
        //collectDeviceInfo(mcontext);
        return true;
    }
//    public void collectDeviceInfo(Context ctx){
//        try {
//            PackageManager pm = ctx.getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
//            if (pi != null) {
//                String versionName = pi.versionName == null ? "null" : pi.versionName;
//                String versionCode = pi.versionCode + "";
//                map.put("versionName", versionName);
//                map.put("versionCode", versionCode);
//            }
//        }catch (PackageManager.NameNotFoundException e){
//            Log.e("TAG", "收集版本信息失败", e);
//        }
//        Field[] fields = Build.class.getDeclaredFields();
//        for (Field field : fields) {
//            try {
//                field.setAccessible(true);
//                map.put(field.getName(), field.get(null).toString());
//                Log.d("TAG", field.getName() + " : " + field.get(null));
//            } catch (Exception e) {
//                Log.e("TAG", "获取设备信息失败", e);
//            }
//        }
//    }

    //保存错误信息到文件中
    private String saveCrashInfo2File(Throwable ex){
//        // 通过遍历我们刚才获取的手机信息，写入 StringBuffer 中，如果获取手机信息没写，这几行就不用写了
      StringBuffer sb = new StringBuffer();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            sb.append(key + "=" + value + "\n");
//        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        // 这里是重点，我要把崩溃信息，通过 while 循环的形式，全部获取出来
        Throwable cause = ex.getCause();
        while (cause!=null){
            cause.printStackTrace(printWriter);
            cause=cause.getCause();
        }
        printWriter.close();
        // 写入到 StringBuffer 中，StringBuffer 中就已经有了设备信息和崩溃信息
        String string = writer.toString();
        sb.append(string);
        try {
            //把信息写入到指定文件
            long timeMillis = System.currentTimeMillis();
            String time = this.format.format(new Date());
            String fileName="crach-"+time+"-"+timeMillis+".log";
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                String path="/sdcard/cc/";
                File file = new File(path);
                // 判断文件是否已经创建过，如果没有则创建一个
                if (!file.exists()){
                    file.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        }catch (Exception e){
            Log.i("TAG","写入失败",e);
        }
            return null;
    }
}