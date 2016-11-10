package com.chenyuan.sentence.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.bean.SentenceSimple;
import com.chenyuan.sentence.mvp.presenter.impl.AllarticlePresenter;
import com.chenyuan.sentence.mvp.ui.activity.JujiDetailActivity;
import com.chenyuan.sentence.mvp.ui.adapter.ArticleAdapter;
import com.chenyuan.sentence.mvp.ui.view.IAllarticleView;
import com.lnyp.flexibledivider.GridSpacingItemDecoration;
import com.lnyp.recyclerview.EndlessRecyclerOnScrollListener;
import com.lnyp.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.lnyp.recyclerview.HeaderSpanSizeLookup;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter;
import com.lnyp.recyclerview.RecyclerViewStateUtils;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

public class FragmentArticleList extends BaseFragment implements IAllarticleView {

    private static final String ARG_TYPE = "type";

    public SwipeRefreshLayout layoutSwipeRefresh;

    public RecyclerView listJuzi;

    public RotateLoading rotateloading;

    private String type;

    private AllarticlePresenter allarticlePresenter;

    private View view;

    private List<SentenceSimple> mDatas;

    private HeaderAndFooterRecyclerViewAdapter mAdapter;

    private String page;

    private boolean mHasMore = true;

    private boolean isRefresh = true;

    public FragmentArticleList() {
    }

    public static FragmentArticleList newInstance(String type) {

        FragmentArticleList fragment = new FragmentArticleList();
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

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_article_list, container, false);
        }

        initView();

        allarticlePresenter = new AllarticlePresenter(this);

        rotateloading.start();
        qryMeijus();

        return view;
    }

    private void initView() {

        layoutSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.layoutSwipeRefresh);
        rotateloading = (RotateLoading) view.findViewById(R.id.rotateloading);
        listJuzi = (RecyclerView) view.findViewById(R.id.listJuzi);

        mDatas = new ArrayList<>();

        ArticleAdapter articleAdapter = new ArticleAdapter(this, mDatas, onClickListener);
        mAdapter = new HeaderAndFooterRecyclerViewAdapter(articleAdapter);
        listJuzi.setAdapter(mAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new HeaderSpanSizeLookup(listJuzi.getAdapter(), gridLayoutManager.getSpanCount()));
        listJuzi.setLayoutManager(gridLayoutManager);

        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration.Builder(getActivity(), gridLayoutManager.getSpanCount())
                .setDividerColor(Color.parseColor("#F3F7F6"))
                .build();
        listJuzi.addItemDecoration(itemDecoration);

        listJuzi.addOnScrollListener(mOnScrollListener);

        layoutSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        layoutSwipeRefresh.setOnRefreshListener(onRefreshListener);
    }

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            page = null;
            isRefresh = true;

            qryMeijus();
        }
    };

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
        allarticlePresenter.loadAllarticle(getActivity(), type, page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }


    @Override
    public void onSuccess(List<SentenceSimple> sentenceSimples) {
        if (page == null) {
            page = "1";
        } else {
            int i_page = Integer.parseInt(page);
            i_page = i_page + 1;
            page = "" + i_page;
        }

        if (isRefresh) {
            mDatas.clear();

            isRefresh = false;
        }

        if (sentenceSimples != null) {
            mDatas.addAll(sentenceSimples);
            mAdapter.notifyDataSetChanged();
        }

        layoutSwipeRefresh.setRefreshing(false);
        rotateloading.stop();
        RecyclerViewStateUtils.setFooterViewState(listJuzi, RecyclerViewLoadingFooter.State.Normal);
    }

    @Override
    public void onError(Throwable e) {
        layoutSwipeRefresh.setRefreshing(false);
        rotateloading.stop();
        RecyclerViewStateUtils.setFooterViewState(getActivity(), listJuzi, mHasMore, RecyclerViewLoadingFooter.State.NetWorkError, mFooterClick);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            SentenceSimple sentenceSimple = mDatas.get(pos);
            LogUtils.e(sentenceSimple);
            JujiDetailActivity.actionStart(getActivity(), sentenceSimple.getDetailUrl());
        }
    };
}
