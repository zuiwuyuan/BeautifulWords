package com.otb.designerassist.mvp.presenter.callback;

import com.otb.designerassist.mvp.model.entity.SentenceDetail;

import java.util.List;

public interface OnOrinalListener {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);

}
