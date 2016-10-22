package com.otb.designerassist.mvp.presenter.callback;

import com.otb.designerassist.mvp.model.entity.SentenceSimple;

import java.util.List;

public interface OnAllarticleListener {

    void onSuccess(List<SentenceSimple> sentenceSimples);

    void onError(Throwable e);

}
