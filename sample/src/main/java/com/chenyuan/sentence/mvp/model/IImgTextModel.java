package com.chenyuan.sentence.mvp.model;

import android.content.Context;

import com.chenyuan.sentence.mvp.presenter.callback.OnImgTextListener;

/**
 * 原创句子
 */
public interface IImgTextModel {

    void loadMeiju(Context context, boolean isFirst, String type, String page, OnImgTextListener listener);

    void loadMeiju(Context context, boolean isFirst, String page, OnImgTextListener listener);
}
