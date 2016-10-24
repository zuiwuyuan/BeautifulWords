package com.otb.designerassist.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.lnyp.flexibledivider.HorizontalDividerItemDecoration;
import com.lnyp.recyclerview.EndlessRecyclerOnScrollListener;
import com.lnyp.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter;
import com.lnyp.recyclerview.RecyclerViewStateUtils;
import com.otb.designerassist.R;
import com.otb.designerassist.mvp.model.entity.SentenceImageText;
import com.otb.designerassist.mvp.presenter.impl.ImgTextPresenter;
import com.otb.designerassist.mvp.ui.adapter.MeiTuwenAdapter;
import com.otb.designerassist.mvp.ui.view.IMeituMeijuView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

public class FragmentMeijuList extends Fragment implements IMeituMeijuView {

    private static final String ARG_TYPE = "type";

    public RecyclerView listJuzi;

    private String type;

    private ImgTextPresenter imgTextPresenter;

    private View view;

    private Unbinder unbinder;

    private List<SentenceImageText> mDatas;

    private HeaderAndFooterRecyclerViewAdapter mAdapter;

    private String page;

    private boolean mHasMore = true;

    public FragmentMeijuList() {
    }

    public static FragmentMeijuList newInstance(String type) {
        FragmentMeijuList fragment = new FragmentMeijuList();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LogUtils.e("view : " + view);

        if (view == null) {

            view = inflater.inflate(R.layout.fragment_meiju_list, container, false);

            initView();

            imgTextPresenter = new ImgTextPresenter(this);

            qryMeijus();
        }


        return view;
    }

    private void initView() {

        listJuzi = (RecyclerView) view.findViewById(R.id.listJuzi);

        mDatas = new ArrayList<>();

        MeiTuwenAdapter meiTuwenAdapter = new MeiTuwenAdapter(this, mDatas, onClickListener);
        mAdapter = new HeaderAndFooterRecyclerViewAdapter(meiTuwenAdapter);
        listJuzi.setAdapter(mAdapter);

        listJuzi.setLayoutManager(new LinearLayoutManager(getActivity()));
        listJuzi.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .colorResId(R.color.divider_color)
                        .size(20)
                        .build());

        listJuzi.addOnScrollListener(mOnScrollListener);

//        swipeRefreshLayout.setRefreshDrawable(new SmartisanDrawable(getActivity(), swipeRefreshLayout));
//        swipeRefreshLayout.setBackgroundColor(Color.parseColor("#EFEFEF"));
//        swipeRefreshLayout.setColor(Color.parseColor("#8F8F81"));

    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            RecyclerViewLoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(listJuzi);

            if (state == RecyclerViewLoadingFooter.State.Loading) {
                return;
            }

            if (mHasMore) {
                RecyclerViewStateUtils.setFooterViewState(getActivity(), listJuzi, mHasMore, RecyclerViewLoadingFooter.State.Loading, null);
                qryMeijus();

            } else {
                RecyclerViewStateUtils.setFooterViewState(getActivity(), listJuzi, mHasMore, RecyclerViewLoadingFooter.State.TheEnd, null);
            }
        }
    };

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(getActivity(), listJuzi, mHasMore, RecyclerViewLoadingFooter.State.Loading, null);

            qryMeijus();
        }
    };

    private void qryMeijus() {

        LogUtils.e("type : " + type);

        if (TextUtils.isEmpty(type)) {
            imgTextPresenter.loadImgText(getActivity(), page);
        } else {
            imgTextPresenter.loadImgText(getActivity(), type, page);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Override
    public void onSuccess(List<SentenceImageText> sentenceImageTexts) {

        if (page == null) {
            page = "1";
        } else {
            int i_page = Integer.parseInt(page);
            i_page = i_page + 1;
            page = "" + i_page;
        }

        LogUtils.e("page : " + page);
        if (sentenceImageTexts != null) {
            mDatas.addAll(sentenceImageTexts);
            mAdapter.notifyDataSetChanged();
        }

        RecyclerViewStateUtils.setFooterViewState(listJuzi, RecyclerViewLoadingFooter.State.Normal);
    }

    @Override
    public void onError(Throwable e) {
        RecyclerViewStateUtils.setFooterViewState(getActivity(), listJuzi, mHasMore, RecyclerViewLoadingFooter.State.NetWorkError, mFooterClick);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
