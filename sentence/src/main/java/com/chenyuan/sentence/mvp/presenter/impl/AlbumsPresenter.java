package com.chenyuan.sentence.mvp.presenter.impl;

import android.content.Context;

import com.chenyuan.sentence.mvp.model.IAlbumsModel;
import com.chenyuan.sentence.mvp.model.bean.SentenceCollection;
import com.chenyuan.sentence.mvp.model.impl.AlbumsModelImpl;
import com.chenyuan.sentence.mvp.presenter.IAlbumsPresenter;
import com.chenyuan.sentence.mvp.presenter.callback.OnAlbumsListener;
import com.chenyuan.sentence.mvp.ui.view.IAlbumsView;

import java.util.List;


public class AlbumsPresenter implements IAlbumsPresenter, OnAlbumsListener {

    private IAlbumsView iAlbumsView;

    private IAlbumsModel iAlbumsModel;

    public AlbumsPresenter(IAlbumsView iAlbumsView) {
        this.iAlbumsView = iAlbumsView;
        this.iAlbumsModel = new AlbumsModelImpl();
    }

    @Override
    public void onSuccess(List<SentenceCollection> sentenceCollections) {
        iAlbumsView.onSuccess(sentenceCollections);
    }

    @Override
    public void onError(Throwable e) {
        iAlbumsView.onError(e);
    }

    @Override
    public void loadAlbums(Context context, String type, String page) {
        iAlbumsModel.loadAlbums(context, type, page, this);
    }
}
