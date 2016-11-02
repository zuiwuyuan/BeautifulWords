package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.entity.SceneListDetail;

public interface OnImgTextListener {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);

}
