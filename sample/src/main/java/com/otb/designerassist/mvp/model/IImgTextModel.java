package com.otb.designerassist.mvp.model;

import android.content.Context;

import com.otb.designerassist.mvp.presenter.callback.OnImgTextListener;

/**
 * 原创句子
 */
public interface IImgTextModel {

    void loadMeiju(Context context, String type, String page, OnImgTextListener listener);

    void loadMeiju(Context context,  String page, OnImgTextListener listener);
}
