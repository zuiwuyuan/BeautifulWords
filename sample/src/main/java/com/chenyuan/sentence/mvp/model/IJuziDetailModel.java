package com.chenyuan.sentence.mvp.model;

import android.content.Context;

import com.chenyuan.sentence.mvp.presenter.callback.OnJuziDetailListener;

public interface IJuziDetailModel {

    void loadOriginal(Context context, String url, OnJuziDetailListener listener);
}
