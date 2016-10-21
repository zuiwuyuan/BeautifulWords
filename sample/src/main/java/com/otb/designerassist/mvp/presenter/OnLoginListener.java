package com.otb.designerassist.mvp.presenter;

import com.otb.designerassist.mvp.model.entity.User;

public interface OnLoginListener {


    void onSuccess(User user);


    void onError();

}
