package com.chenyuan.sentence.mvp.presenter.impl;

import android.content.Context;

import com.chenyuan.sentence.mvp.model.IAllarticleModel;
import com.chenyuan.sentence.mvp.model.bean.SentenceSimple;
import com.chenyuan.sentence.mvp.model.impl.AllarticleModelImpl;
import com.chenyuan.sentence.mvp.presenter.IAllarticlePresenter;
import com.chenyuan.sentence.mvp.presenter.callback.OnAllarticleListener;
import com.chenyuan.sentence.mvp.ui.view.IAllarticleView;

import java.util.List;


public class AllarticlePresenter implements IAllarticlePresenter, OnAllarticleListener {

    private IAllarticleView iAllarticleView;

    private IAllarticleModel iAllarticleModel;

    public AllarticlePresenter(IAllarticleView iAllarticleView) {
        this.iAllarticleView = iAllarticleView;
        this.iAllarticleModel = new AllarticleModelImpl();
    }

    @Override
    public void loadAllarticle(Context context, String type, String page) {
        iAllarticleModel.loadArticle(context, type, page, this);
    }

    @Override
    public void onSuccess(List<SentenceSimple> sentenceSimples) {
        iAllarticleView.onSuccess(sentenceSimples);
    }

    @Override
    public void onError(Throwable e) {
        iAllarticleView.onError(e);
    }
}
