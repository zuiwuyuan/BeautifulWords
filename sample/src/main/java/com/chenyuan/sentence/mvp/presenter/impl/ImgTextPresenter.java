package com.chenyuan.sentence.mvp.presenter.impl;

import android.content.Context;

import com.chenyuan.sentence.mvp.model.IImgTextModel;
import com.chenyuan.sentence.mvp.model.entity.SceneListDetail;
import com.chenyuan.sentence.mvp.model.impl.ImgTextModelImpl;
import com.chenyuan.sentence.mvp.presenter.IMeituPresenter;
import com.chenyuan.sentence.mvp.presenter.callback.OnImgTextListener;
import com.chenyuan.sentence.mvp.ui.view.IMeituMeijuView;


public class ImgTextPresenter implements IMeituPresenter, OnImgTextListener {

    private IMeituMeijuView iMeituMeijuView;

    private IImgTextModel iImgTextModel;

    public ImgTextPresenter(IMeituMeijuView iMeituMeijuView) {
        this.iMeituMeijuView = iMeituMeijuView;
        this.iImgTextModel = new ImgTextModelImpl();
    }

    @Override
    public void loadImgText(Context context, String type, String page) {
        iImgTextModel.loadMeiju(context, type, page, this);
    }

    @Override
    public void loadImgText(Context context, String page) {
        iImgTextModel.loadMeiju(context, page, this);
    }

    @Override
    public void onSuccess(SceneListDetail sceneListDetail) {
        iMeituMeijuView.onSuccess(sceneListDetail);
    }

    @Override
    public void onError(Throwable e) {
        iMeituMeijuView.onError(e);
    }
}
