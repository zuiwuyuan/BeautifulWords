package com.chenyuan.sentence.mvp.model;

import android.content.Context;

import com.chenyuan.sentence.mvp.presenter.callback.OnAlbumsListener;

/**
 * 原创句子
 */
public interface IAlbumsModel {

    void loadAlbums(Context context, String type, String page, OnAlbumsListener listener);

}
