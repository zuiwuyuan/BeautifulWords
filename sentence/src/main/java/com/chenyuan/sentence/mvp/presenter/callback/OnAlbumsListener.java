package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.bean.SentenceCollection;

import java.util.List;

public interface OnAlbumsListener {

    void onSuccess(List<SentenceCollection> sentenceCollections);

    void onError(Throwable e);

}
