package com.chenyuan.sentence.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityController {

    public static final List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        synchronized (activities) {
            for (Activity act : activities) {
                if (act != null && !act.isFinishing())
                    act.finish();
            }
        }
    }

    public static void exitApp() {

        finishAll();

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}  