package com.chenyuan.sentence.mvp.ui.view;

import com.chenyuan.sentence.mvp.model.bean.SentenceDetail;

import java.util.List;

/**
 * 原创句子
 */
public interface IOrignalView {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);
}
