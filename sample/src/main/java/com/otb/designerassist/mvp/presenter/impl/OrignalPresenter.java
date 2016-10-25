package com.otb.designerassist.mvp.presenter.impl;

import android.content.Context;

import com.otb.designerassist.mvp.model.IOrignalModel;
import com.otb.designerassist.mvp.model.entity.SentenceDetail;
import com.otb.designerassist.mvp.model.impl.OrignalModelImpl;
import com.otb.designerassist.mvp.presenter.IOrignalPresenter;
import com.otb.designerassist.mvp.presenter.callback.OnOrinalListener;
import com.otb.designerassist.mvp.ui.view.IOrignalView;

import java.util.List;


public class OrignalPresenter implements IOrignalPresenter, OnOrinalListener {

    private IOrignalView iOrignalView;

    private IOrignalModel iOrignalModel;

    public OrignalPresenter(IOrignalView iOrignalView) {
        this.iOrignalView = iOrignalView;
        this.iOrignalModel = new OrignalModelImpl();
    }

    @Override
    public void loadOriginal(Context context, String type, String page) {
        iOrignalModel.loadOriginal(context, type, page, this);
    }

    @Override
    public void onSuccess(List<SentenceDetail> sentenceDetails) {
        iOrignalView.onSuccess(sentenceDetails);
    }

    @Override
    public void onError(Throwable e) {
        iOrignalView.onError(e);
    }
}
