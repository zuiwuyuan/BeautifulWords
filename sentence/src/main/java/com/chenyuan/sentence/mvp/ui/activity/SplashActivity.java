package com.chenyuan.sentence.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.bean.UpgradeBean;
import com.chenyuan.sentence.mvp.ui.common.BaseActivity;
import com.chenyuan.sentence.util.AppUtils;
import com.google.gson.Gson;

import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    UpgradeBean respUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!this.isTaskRoot()) {
            //判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
            //如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;//finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
            }
        }

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        checkUpdate();
    }

    private void checkUpdate() {

        FIR.checkForUpdateInFIR("c4eba07f521cf456edd68b9517c24df3", new VersionCheckCallback() {
                    @Override
                    public void onSuccess(String versionJson) {
                        Log.i("fir", "onSuccess " + "\n" + versionJson);
                        Gson gson = new Gson();
                        respUpdate = gson.fromJson(versionJson, UpgradeBean.class);
                    }

                    @Override
                    public void onFail(Exception exception) {
                        exception.printStackTrace();
                    }

                    @Override
                    public void onStart() {
                        Log.i("fir", "onStart ");
                    }

                    @Override
                    public void onFinish() {
                        Log.i("fir", "onFinish ");
                        try {

                            // 判断是否需要更新
                            if (respUpdate.getVersion() > AppUtils.getVersionCode(SplashActivity.this)) {
                                String update_desc = respUpdate.getChangelog();
                                showDialog(update_desc);
                            } else {
                                jumpToMain();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            jumpToMain();
                        }
                    }
                }
        );
    }

    /**
     * 这是兼容的 AlertDialog
     */
    private void showDialog(String update_desc) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("升级提示");
        builder.setMessage(update_desc);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface anInterface, int i) {
                jumpToMain();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface anInterface, int i) {
                Uri uri = Uri.parse(respUpdate.getInstall_url());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                SplashActivity.this.startActivity(intent);
            }
        });
        builder.show();
    }

    private void jumpToMain() {
        Intent intent = new Intent();

        intent.setClass(SplashActivity.this, MainActivity.class);

        startActivity(intent);
        SplashActivity.this.finish();
    }
}
