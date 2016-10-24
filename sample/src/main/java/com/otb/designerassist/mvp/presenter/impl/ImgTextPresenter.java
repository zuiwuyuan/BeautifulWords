package com.otb.designerassist.mvp.presenter.impl;

import android.content.Context;

import com.otb.designerassist.mvp.model.IImgTextModel;
import com.otb.designerassist.mvp.model.entity.SentenceImageText;
import com.otb.designerassist.mvp.model.impl.ImgTextModelImpl;
import com.otb.designerassist.mvp.presenter.IMeituPresenter;
import com.otb.designerassist.mvp.presenter.callback.OnImgTextListener;
import com.otb.designerassist.mvp.ui.view.IMeituMeijuView;

import java.util.List;


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
    public void onSuccess(List<SentenceImageText> sentenceImageTexts) {
        iMeituMeijuView.onSuccess(sentenceImageTexts);
    }

    @Override
    public void onError(Throwable e) {
        iMeituMeijuView.onError(e);
    }
}
