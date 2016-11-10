package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.bean.SentenceSimple;

import java.util.List;

public interface OnAllarticleListener {

    void onSuccess(List<SentenceSimple> sentenceSimples);

    void onError(Throwable e);

}
