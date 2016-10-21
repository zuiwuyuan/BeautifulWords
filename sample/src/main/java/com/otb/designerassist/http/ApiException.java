package com.otb.designerassist.http;

import com.otb.designerassist.common.Constants;

public class ApiException extends RuntimeException {

    private String mErrorCode;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);

        mErrorCode = errorCode;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {

        if (mErrorCode.equals(Constants.TOKEN_PAST_DUE) || mErrorCode.equals(Constants.TOKEN_CHECK_FAILURE)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOtherDeviceLogin() {

        if (mErrorCode.equals(Constants.OTHER_DEVICE_LOGIN)) {
            return true;
        } else {
            return false;
        }
    }
}