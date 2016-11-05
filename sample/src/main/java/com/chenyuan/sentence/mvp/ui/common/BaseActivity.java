package com.chenyuan.sentence.mvp.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chenyuan.sentence.app.MyApp;
import com.chenyuan.sentence.common.ActivityController;
import com.squareup.leakcanary.RefWatcher;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在自己的应用初始Activity中加入如下两行代码
        RefWatcher refWatcher = MyApp.getRefWatcher(this);
        refWatcher.watch(this);

        ActivityController.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

}
