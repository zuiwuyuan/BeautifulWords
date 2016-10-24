package com.otb.designerassist.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otb.designerassist.R;


/**
 * 消息Frag
 */
public class FragmentAllArticle extends Fragment {


    private View msg_frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        msg_frag = inflater.inflate(R.layout.fragment_msg, container, false);

        return msg_frag;
    }
}
