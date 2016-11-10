package com.chenyuan.sentence.mvp.ui.view;

import com.chenyuan.sentence.mvp.model.bean.SceneListDetail;

/**
 * 句子详情
 */
public interface IJuziDetailView {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);
}
