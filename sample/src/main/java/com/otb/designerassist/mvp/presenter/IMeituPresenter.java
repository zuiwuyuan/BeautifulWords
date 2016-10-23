package com.otb.designerassist.mvp.presenter;

import android.content.Context;

/**
 * 美图美句
 */
public interface IMeituPresenter {

    void loadImgText(Context context, String type, String page);

    void loadImgText(Context context, String page);

}
