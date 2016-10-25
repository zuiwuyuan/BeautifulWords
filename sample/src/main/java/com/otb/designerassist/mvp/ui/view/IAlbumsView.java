package com.otb.designerassist.mvp.ui.view;

import com.otb.designerassist.mvp.model.entity.SentenceCollection;

import java.util.List;

/**
 * 句集
 */
public interface IAlbumsView {
    void onSuccess(List<SentenceCollection> sentenceCollections);

    void onError(Throwable e);
}
