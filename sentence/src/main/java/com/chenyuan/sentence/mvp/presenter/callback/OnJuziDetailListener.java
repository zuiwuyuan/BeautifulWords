package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.entity.SceneListDetail;

public interface OnJuziDetailListener {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);

}
