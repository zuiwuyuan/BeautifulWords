package com.otb.designerassist.mvp.ui.view;

import com.otb.designerassist.mvp.model.entity.SentenceImageText;

import java.util.List;

/**
 * 美图美句
 */
public interface IMeituMeijuView {

    void onSuccess(List<SentenceImageText> sentenceImageTexts);

    void onError(Throwable e);
}
