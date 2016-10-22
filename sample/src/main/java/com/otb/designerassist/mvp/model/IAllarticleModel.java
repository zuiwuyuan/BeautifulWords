package com.otb.designerassist.mvp.model;

import android.content.Context;

import com.otb.designerassist.mvp.presenter.callback.OnAllarticleListener;

/**
 * 名人名句
 */
public interface IAllarticleModel {

    void loadArticle(Context context, String type, String page, OnAllarticleListener listener);

}
