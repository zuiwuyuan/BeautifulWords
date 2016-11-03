package com.chenyuan.sentence.mvp.presenter;

import android.content.Context;

/**
 * 美图美句
 */
public interface IMeituPresenter {

    void loadImgText(Context context, boolean isFirst, String type, String page);

    void loadImgText(Context context, boolean isFirst, String page);

}
