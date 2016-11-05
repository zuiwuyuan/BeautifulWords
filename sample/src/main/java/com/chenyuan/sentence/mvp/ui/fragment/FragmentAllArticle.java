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
 * 名人名句
 */
public class FragmentAllArticle extends BaseFragment {

    private static final String TYPE1 = "jingdiantaici";

    private static final String TYPE2 = "zhaichao";

    private static final String TYPE3 = "sanwen";

    private static final String TYPE4 = "dongmantaici";

    private static final String TYPE5 = "guwen";


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
            view = inflater.inflate(R.layout.fragment_all_article, container, false);
        }

        unbinder = ButterKnife.bind(this, view);

        initControls();

        return view;
    }

    private void initControls() {

        //初始化各fragment
        FragmentArticleList fragmentArticleList1 = FragmentArticleList.newInstance(TYPE1);
        FragmentArticleList fragmentArticleList2 = FragmentArticleList.newInstance(TYPE2);
        FragmentArticleList fragmentArticleList3 = FragmentArticleList.newInstance(TYPE3);
        FragmentArticleList fragmentArticleList4 = FragmentArticleList.newInstance(TYPE4);
        FragmentArticleList fragmentArticleList5 = FragmentArticleList.newInstance(TYPE5);

        //将fragment装进列表中
        List<Fragment> list_fragment = new ArrayList<>();
        list_fragment.add(fragmentArticleList1);
        list_fragment.add(fragmentArticleList2);
        list_fragment.add(fragmentArticleList3);
        list_fragment.add(fragmentArticleList4);
        list_fragment.add(fragmentArticleList5);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        List<String> list_title = new ArrayList<>();
        list_title.add("电影台词");
        list_title.add("小说摘抄");
        list_title.add("散文美句");
        list_title.add("动漫语录");
        list_title.add("古文名句");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(4)));

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
