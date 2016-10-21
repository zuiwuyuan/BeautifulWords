package com.otb.designerassist.common;

import android.content.Context;

import com.otb.designerassist.util.PreferenceUtil;

/**
 * APP全局配置
 *
 * @author lining
 */
public class AppSetting {

    //token信息
    private static final String CACHE_TOKEN = "cache_token";

    // user信息
    private static final String CACHE_USER = "cache_user";


    private static AppSetting mInstance = null;

    private AppSetting(Context context) {
    }

    public synchronized static AppSetting init(Context context) {
        if (mInstance == null) {
            mInstance = new AppSetting(context);
        }

        return mInstance;
    }

    public static AppSetting getInstance() {
        return mInstance;
    }

    public String getCacheToken() {
        return PreferenceUtil.getStringPreference(CACHE_TOKEN);
    }

    public void setCacheToken(String value) {
        PreferenceUtil.setStringPreference(CACHE_TOKEN, value);
    }

    public String getCacheUser() {
        return PreferenceUtil.getStringPreference(CACHE_USER);
    }

    public void setCacheUser(String value) {
        PreferenceUtil.setStringPreference(CACHE_USER, value);
    }

    public void clearUserCache() {
        try {
            setCacheToken(null);
            setCacheUser(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
