package com.otb.designerassist.mvp.presenter.callback;

import com.otb.designerassist.mvp.model.entity.SentenceCollection;

import java.util.List;

public interface OnAlbumsListener {

    void onSuccess(List<SentenceCollection> sentenceCollections);

    void onError(Throwable e);

}
