package com.chenyuan.sentence.mvp.ui.view;

import com.chenyuan.sentence.mvp.model.entity.SentenceDetail;

import java.util.List;

/**
 * 句子详情
 */
public interface IJuziDetailView {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);
}
