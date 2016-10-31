package com.chenyuan.sentence.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.apkfuns.logutils.LogUtils;
import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.entity.SentenceDetail;
import com.chenyuan.sentence.mvp.presenter.impl.JuziDetailPresenter;
import com.chenyuan.sentence.mvp.ui.view.IJuziDetailView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 句集详情
 */
public class JujiDetailActivity extends AppCompatActivity implements IJuziDetailView {

    private String url;

    private JuziDetailPresenter mJuziDetailPresenter;

    public static void actionStart(Context context, String url) {

        Intent intent = new Intent(context, JujiDetailActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juji_detail);

        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");

        LogUtils.e("url : " + url);

        mJuziDetailPresenter = new JuziDetailPresenter(this);
        mJuziDetailPresenter.loadJuziDetail(this, url);
    }

    @Override
    public void onSuccess(List<SentenceDetail> sentenceDetails) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
