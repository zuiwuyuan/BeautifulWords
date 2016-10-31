package com.chenyuan.sentence.app;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.chenyuan.sentence.common.CrashHandler;
import com.chenyuan.sentence.common.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;


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

        initRealm();
    }

    private void initRealm() {

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .schemaVersion(1)
                .rxFactory(new RealmObservableFactory())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
