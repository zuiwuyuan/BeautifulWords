package com.chenyuan.sentence.common;

import io.realm.Realm;

/**
 * Created by lining on 2016/10/28.
 */
public class RealmHelper {

    public static final String DB_NAME = "ghost.realm";
    private Realm mRealm;
    private static RealmHelper instance;

    private RealmHelper() {
    }

    public static synchronized RealmHelper getInstance() {
        if (instance == null) {
            synchronized (RealmHelper.class) {
                if (instance == null)
                    instance = new RealmHelper();
            }
        }
        return instance;
    }


    protected Realm getRealm() {
        if (mRealm == null || mRealm.isClosed())
            mRealm = Realm.getDefaultInstance();
        return mRealm;
    }
}
