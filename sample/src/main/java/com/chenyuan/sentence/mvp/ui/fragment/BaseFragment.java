package com.chenyuan.sentence.mvp.ui.fragment;


import android.support.v4.app.Fragment;

import com.chenyuan.sentence.app.MyApp;
import com.squareup.leakcanary.RefWatcher;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApp.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

}
