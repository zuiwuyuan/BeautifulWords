package com.otb.designerassist.http.service;

import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 用户操作相关
 * 登录
 * 注册
 * 注销
 * 修改用户信息
 * 修改密码
 * 找回密码
 */
public interface CommonService {

    @GET
    void loadData(@Url String url);
}
