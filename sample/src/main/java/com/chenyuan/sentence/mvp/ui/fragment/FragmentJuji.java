package com.chenyuan.sentence.mvp.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.ui.adapter.TitleTabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 任务Frag
 */
public class FragmentJuji extends BaseFragment {

    private static final String TYPE1 = "albums";

    private static final String TYPE2 = "newalbums";

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private View view;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_juji, container, false);
        }

        unbinder = ButterKnife.bind(this, view);

        initControls();

        return view;
    }

    private void initControls() {

        //初始化各fragment
        FragmentJujiList fragmentJujiList1 = FragmentJujiList.newInstance(TYPE1);
        FragmentJujiList fragmentJujiList2 = FragmentJujiList.newInstance(TYPE2);

        //将fragment装进列表中
        List<Fragment> list_fragment = new ArrayList<>();
        list_fragment.add(fragmentJujiList1);
        list_fragment.add(fragmentJujiList2);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        List<String> list_title = new ArrayList<>();
        list_title.add("精选句集");
        list_title.add("最新句集");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));

        TitleTabAdapter titleTabAdapter = new TitleTabAdapter(getChildFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        viewPager.setAdapter(titleTabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        unbinder.unbind();
    }

}
