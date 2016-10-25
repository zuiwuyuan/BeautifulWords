package com.otb.designerassist.mvp.ui.view;

import com.otb.designerassist.mvp.model.entity.SentenceDetail;

import java.util.List;

/**
 * 原创句子
 */
public interface IOrignalView {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);
}
