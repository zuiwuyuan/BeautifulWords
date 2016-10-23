package com.otb.designerassist.mvp.model;

import android.content.Context;

import com.otb.designerassist.mvp.presenter.callback.OnAlbumsListener;

/**
 * 原创句子
 */
public interface IAlbumsModel {

    void loadAlbums(Context context, String type, String page, OnAlbumsListener listener);

}
