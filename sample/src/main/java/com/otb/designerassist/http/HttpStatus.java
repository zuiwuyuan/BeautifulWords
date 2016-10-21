package com.otb.designerassist.http;

import com.otb.designerassist.common.Constants;

public class HttpStatus {

    private String code;

    private String mess;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return !code.equals(Constants.CMD_CODE_SERVER_OK);
    }

}