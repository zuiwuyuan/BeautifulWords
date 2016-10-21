package com.otb.designerassist.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference处理工具类
 */
public class PreferenceUtil
{
    private static final String PerferenceName = "Merchants_Preference";

    private static SharedPreferences preferences = null;

    public static synchronized void init(Context context)
    {
        if (preferences != null)
            return;

        preferences = context.getSharedPreferences(PerferenceName, Context.MODE_PRIVATE);
    }

    /**
     * 清除所有数据
     */
    public static void clearAll()
    {
        preferences.edit().clear().commit();
    }

    public static void remove(String name)
    {
        preferences.edit().remove(name).commit();
    }

    public static boolean getBooleanPreference(String name, boolean defValue)
    {

        return preferences.getBoolean(name, defValue);
    }

    public static void setBooleanPreference(String name, boolean value)
    {
        preferences.edit().putBoolean(name, value).commit();
    }

    public static String getStringPreference(String key)
    {
        return preferences.getString(key, "");
    }

    public static void setStringPreference(String key, String value)
    {
        preferences.edit().putString(key, value).commit();
    }

    public static int getIntPreference(String name)
    {
        return preferences.getInt(name, -1);
    }

    public static void setIntPreference(String name, int value)
    {
        preferences.edit().putInt(name, value).commit();
    }

    public static float getFloatPreference(String name)
    {
        return preferences.getFloat(name, 0);
    }

    public static void setFloatPreference(String name, float value)
    {
        preferences.edit().putFloat(name, value);
    }

    public static void setLongPreference(String name, long value)
    {
        preferences.edit().putLong(name, value).commit();
    }

    public static long getLongPreference(String name)
    {
        return preferences.getLong(name, 0);
    }
}
