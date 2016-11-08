package com.chenyuan.sentence.mvp.ui.view;

import com.chenyuan.sentence.mvp.model.entity.SentenceSimple;

import java.util.List;

/**
 * 名人名句
 */
public interface IAllarticleView {

    void onSuccess(List<SentenceSimple> sentenceSimples);

    void onError(Throwable e);

}
