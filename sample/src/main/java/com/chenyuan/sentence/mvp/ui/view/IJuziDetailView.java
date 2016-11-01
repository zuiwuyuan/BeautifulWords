package com.chenyuan.sentence.mvp.ui.view;

import com.chenyuan.sentence.mvp.model.entity.SentenceImageText;

import java.util.List;

/**
 * 句子详情
 */
public interface IJuziDetailView {

    void onSuccess(List<SentenceImageText> sentenceImageTexts);

    void onError(Throwable e);
}
