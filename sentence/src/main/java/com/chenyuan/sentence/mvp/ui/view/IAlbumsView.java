package com.chenyuan.sentence.mvp.ui.view;

import com.chenyuan.sentence.mvp.model.bean.SentenceCollection;

import java.util.List;

/**
 * 句集
 */
public interface IAlbumsView {
    void onSuccess(List<SentenceCollection> sentenceCollections);

    void onError(Throwable e);
}
