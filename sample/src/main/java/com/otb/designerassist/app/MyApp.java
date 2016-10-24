package com.otb.designerassist.app;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.otb.designerassist.common.CrashHandler;


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

        LogUtils.configAllowLog = true;
        LogUtils.configTagPrefix = "lnyp-";
    }

}
