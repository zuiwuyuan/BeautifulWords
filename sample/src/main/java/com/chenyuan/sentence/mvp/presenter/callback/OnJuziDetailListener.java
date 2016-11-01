package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.entity.SentenceImageText;

import java.util.List;

public interface OnJuziDetailListener {

    void onSuccess(List<SentenceImageText> sentenceImageTexts);

    void onError(Throwable e);

}
