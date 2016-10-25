package com.chenyuan.sentence.mvp.model;

import android.content.Context;

import com.chenyuan.sentence.mvp.presenter.callback.OnOrinalListener;

/**
 * 原创句子
 */
public interface IOrignalModel {

    void loadOriginal(Context context, String type, String page, OnOrinalListener listener);

}
