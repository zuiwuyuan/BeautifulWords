package com.chenyuan.sentence.mvp.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.ui.common.BaseActivity;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    private static final int SHOW_TIME_MIN = 1000;

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

        new SplashTask().execute();

    }

    public class SplashTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // 最短启动时间
            long startTime = System.currentTimeMillis();

            long loadingTime = System.currentTimeMillis() - startTime;

            if (loadingTime < SHOW_TIME_MIN) {
                try {
                    Thread.sleep(SHOW_TIME_MIN - loadingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            startActivity(new Intent(SplashActivity.this, MainActivity.class));

            SplashActivity.this.finish();
        }
    }
}
