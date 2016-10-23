package com.otb.designerassist.mvp.presenter.impl;

import android.content.Context;

import com.otb.designerassist.mvp.model.IAlbumsModel;
import com.otb.designerassist.mvp.model.entity.SentenceCollection;
import com.otb.designerassist.mvp.model.impl.AlbumsModelImpl;
import com.otb.designerassist.mvp.presenter.IAlbumsPresenter;
import com.otb.designerassist.mvp.presenter.callback.OnAlbumsListener;
import com.otb.designerassist.mvp.ui.view.IAlbumsView;

import java.util.List;


public class AlbumsPresenter implements IAlbumsPresenter, OnAlbumsListener {

    private IAlbumsView iAlbumsView;

    private IAlbumsModel iAlbumsModel;

    public AlbumsPresenter(IAlbumsView iAlbumsView) {
        this.iAlbumsView = iAlbumsView;
        this.iAlbumsModel = new AlbumsModelImpl();
    }

    @Override
    public void onSuccess(List<SentenceCollection> sentenceImageTexts) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void loadAlbums(Context context, String type, String page) {
        iAlbumsModel.loadAlbums(context, type, page, this);
    }
}
