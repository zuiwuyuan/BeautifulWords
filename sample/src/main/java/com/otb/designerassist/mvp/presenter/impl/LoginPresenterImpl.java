package com.otb.designerassist.mvp.presenter.impl;

import android.content.Context;

import com.otb.designerassist.mvp.model.ILoginModel;
import com.otb.designerassist.mvp.model.entity.User;
import com.otb.designerassist.mvp.model.impl.LoginModelImpl;
import com.otb.designerassist.mvp.presenter.ILoginPresenter;
import com.otb.designerassist.mvp.presenter.OnLoginListener;
import com.otb.designerassist.mvp.ui.view.ILoginView;


public class LoginPresenterImpl implements ILoginPresenter, OnLoginListener {

    private ILoginView iLoginView;

    private ILoginModel iLoginModel;

    public LoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModelImpl();
    }


    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void login(Context context, String userName, String password) {

    }
}
