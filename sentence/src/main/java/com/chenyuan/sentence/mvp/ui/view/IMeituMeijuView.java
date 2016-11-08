package com.chenyuan.sentence.mvp.ui.view;

import com.chenyuan.sentence.mvp.model.entity.SceneListDetail;

/**
 * 美图美句
 */
public interface IMeituMeijuView {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);
}
