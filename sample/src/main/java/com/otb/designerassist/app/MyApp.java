package com.otb.designerassist.app;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.otb.designerassist.common.CrashHandler;
import com.tencent.smtt.sdk.QbSdk;


public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        initUtil();
    }

    private void initUtil() {

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

//        PreferenceUtil.init(context);

//        cache = AppCache.init(this);

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
            }

            @Override
            public void onCoreInitFinished() {

            }
        };

        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, cb);

        LogUtils.configAllowLog = true;
        LogUtils.configTagPrefix = "lnyp-";
    }

}
