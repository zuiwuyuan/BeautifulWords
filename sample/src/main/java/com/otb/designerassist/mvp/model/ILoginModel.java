package com.otb.designerassist.mvp.model;

import android.content.Context;

import com.otb.designerassist.mvp.presenter.OnLoginListener;

/**
 * 登录
 */
public interface ILoginModel {

    void login(Context context, String userName, String password, OnLoginListener listener);
}
