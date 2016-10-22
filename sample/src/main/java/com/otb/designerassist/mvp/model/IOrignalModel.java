package com.otb.designerassist.mvp.model;

import android.content.Context;

import com.otb.designerassist.mvp.presenter.callback.OnOrinalListener;

/**
 * 原创句子
 */
public interface IOrignalModel {

    void loadOriginal(Context context, String type, String page, OnOrinalListener listener);

}
