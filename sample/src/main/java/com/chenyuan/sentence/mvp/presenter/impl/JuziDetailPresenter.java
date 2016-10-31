package com.chenyuan.sentence.mvp.presenter.impl;

import android.content.Context;

import com.chenyuan.sentence.mvp.model.IJuziDetailModel;
import com.chenyuan.sentence.mvp.model.entity.SentenceDetail;
import com.chenyuan.sentence.mvp.model.impl.JuziDetailModelImpl;
import com.chenyuan.sentence.mvp.presenter.IJuziDetailPresenter;
import com.chenyuan.sentence.mvp.presenter.callback.OnJuziDetailListener;
import com.chenyuan.sentence.mvp.ui.view.IJuziDetailView;

import java.util.List;


public class JuziDetailPresenter implements IJuziDetailPresenter, OnJuziDetailListener {

    private IJuziDetailView mIJuziDetailView;

    private IJuziDetailModel mIJuziDetailModel;

    public JuziDetailPresenter(IJuziDetailView mIJuziDetailView) {
        this.mIJuziDetailView = mIJuziDetailView;
        this.mIJuziDetailModel = new JuziDetailModelImpl();
    }

    @Override
    public void onSuccess(List<SentenceDetail> sentenceDetails) {
        mIJuziDetailView.onSuccess(sentenceDetails);
    }

    @Override
    public void onError(Throwable e) {
        mIJuziDetailView.onError(e);
    }

    @Override
    public void loadJuziDetail(Context context, String url) {
        mIJuziDetailModel.loadOriginal(context, url, this);
    }
}
