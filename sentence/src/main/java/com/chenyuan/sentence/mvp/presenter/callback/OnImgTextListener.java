package com.chenyuan.sentence.mvp.presenter.callback;

import com.chenyuan.sentence.mvp.model.bean.SceneListDetail;

public interface OnImgTextListener {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);

}
