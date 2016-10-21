package com.otb.designerassist.http;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;

import rx.Subscriber;

public class BaseSubscriber<T> extends Subscriber<T> {

    protected Context mContext;

    public BaseSubscriber(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(final Throwable e) {
        LogUtils.e(e);
    }

    @Override
    public void onNext(T t) {
    }
}