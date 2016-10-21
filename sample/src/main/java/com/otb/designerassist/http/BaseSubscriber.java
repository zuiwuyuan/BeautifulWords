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

       /* if (e instanceof HttpException) {
            ToastUtil.showToast(mContext, mContext.getString(R.string.net_error));
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ToastUtil.showToast(mContext, mContext.getString(R.string.shuju_error));
        } else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;

            if (exception.isTokenExpried()) {
                //处理token失效对应的逻辑
                ToastUtil.showToast(mContext, mContext.getString(R.string.login_error));
                clearData(mContext);
            } else if (exception.isOtherDeviceLogin()) {
                //处理其他设备登录的逻辑
                ToastUtil.showToast(mContext, mContext.getString(R.string.other_device_error));
                clearData(mContext);
            } else {
                ToastUtil.showToast(mContext, e.getMessage());
                clearData(mContext);
            }
        } else {
            ToastUtil.showToast(mContext, mContext.getString(R.string.unknown_error));       //未知错误
        }*/
    }

    @Override
    public void onNext(T t) {
    }

//    private void clearData(Context context) {
//
//        // 清空所有缓存，并直接跳转登录
//        MyApp.cache.clearUserData();
//
//        // 关闭Dialog
//        if (DialogLoading.getDialog() != null) {
//            DialogLoading.getDialog().dismiss();
//        }
//
//        // 刷新用户中心的页面
////        EventBus.getDefault().post(new ChangeUserEvent(UserOperate.USER_LOGOUT));
//
//        LogUtils.e("*********clearData**********");
//
//        // 跳转到登录
//        Intent login = new Intent(context, LoginActivity.class);
//        context.startActivity(login);
//    }
}