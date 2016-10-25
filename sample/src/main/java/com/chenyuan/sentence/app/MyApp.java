package com.chenyuan.sentence.app;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.chenyuan.sentence.common.CrashHandler;


public class MyApp extends Application {

    private static Context context;


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        initUtil();
    }

    private void initUtil() {

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        LogUtils.configAllowLog = false;
        LogUtils.configTagPrefix = "lnyp-";
    }

}
