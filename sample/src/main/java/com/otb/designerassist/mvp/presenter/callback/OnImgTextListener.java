package com.otb.designerassist.mvp.presenter.callback;

import com.otb.designerassist.mvp.model.entity.SentenceImageText;

import java.util.List;

public interface OnImgTextListener {

    void onSuccess(List<SentenceImageText> sentenceImageTexts);

    void onError(Throwable e);

}
