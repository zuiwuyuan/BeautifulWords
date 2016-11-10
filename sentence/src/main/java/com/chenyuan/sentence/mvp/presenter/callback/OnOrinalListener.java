package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.bean.SentenceDetail;

import java.util.List;

public interface OnOrinalListener {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);

}
