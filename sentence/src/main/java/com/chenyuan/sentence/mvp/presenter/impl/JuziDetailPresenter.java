package com.chenyuan.sentence.mvp.presenter.impl;

import android.content.Context;

import com.chenyuan.sentence.mvp.model.IJuziDetailModel;
import com.chenyuan.sentence.mvp.model.bean.SceneListDetail;
import com.chenyuan.sentence.mvp.model.impl.JuziDetailModelImpl;
import com.chenyuan.sentence.mvp.presenter.IJuziDetailPresenter;
import com.chenyuan.sentence.mvp.presenter.callback.OnJuziDetailListener;
import com.chenyuan.sentence.mvp.ui.view.IJuziDetailView;


public class JuziDetailPresenter implements IJuziDetailPresenter, OnJuziDetailListener {

    private IJuziDetailView mIJuziDetailView;

    private IJuziDetailModel mIJuziDetailModel;

    public JuziDetailPresenter(IJuziDetailView mIJuziDetailView) {
        this.mIJuziDetailView = mIJuziDetailView;
        this.mIJuziDetailModel = new JuziDetailModelImpl();
    }

    @Override
    public void onSuccess(SceneListDetail sceneListDetail) {
        mIJuziDetailView.onSuccess(sceneListDetail);
    }

    @Override
    public void onError(Throwable e) {
        mIJuziDetailView.onError(e);
    }

    @Override
    public void loadJuziDetail(Context context, boolean isFrist, String url) {
        mIJuziDetailModel.loadOriginal(context, isFrist, url, this);
    }
}
