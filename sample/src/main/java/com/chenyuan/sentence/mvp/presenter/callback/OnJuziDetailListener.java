package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.entity.SentenceDetail;

import java.util.List;

public interface OnJuziDetailListener {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);

}
