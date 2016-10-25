package com.otb.designerassist.mvp.presenter.impl;

import android.content.Context;

import com.otb.designerassist.mvp.model.IAllarticleModel;
import com.otb.designerassist.mvp.model.entity.SentenceSimple;
import com.otb.designerassist.mvp.model.impl.AllarticleModelImpl;
import com.otb.designerassist.mvp.presenter.IAllarticlePresenter;
import com.otb.designerassist.mvp.presenter.callback.OnAllarticleListener;
import com.otb.designerassist.mvp.ui.view.IAllarticleView;

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
