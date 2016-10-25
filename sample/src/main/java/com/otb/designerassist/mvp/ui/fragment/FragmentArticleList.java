package com.otb.designerassist.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.lnyp.flexibledivider.GridSpacingItemDecoration;
import com.lnyp.recyclerview.EndlessRecyclerOnScrollListener;
import com.lnyp.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.lnyp.recyclerview.HeaderSpanSizeLookup;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter;
import com.lnyp.recyclerview.RecyclerViewStateUtils;
import com.otb.designerassist.R;
import com.otb.designerassist.mvp.model.entity.SentenceSimple;
import com.otb.designerassist.mvp.presenter.impl.AllarticlePresenter;
import com.otb.designerassist.mvp.ui.adapter.ArticleAdapter;
import com.otb.designerassist.mvp.ui.view.IAllarticleView;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

public class FragmentArticleList extends Fragment implements IAllarticleView {

    private static final String ARG_TYPE = "type";

    public RecyclerView listJuzi;

    public RotateLoading rotateloading;

    private String type;

    private AllarticlePresenter allarticlePresenter;

    private View view;

    private List<SentenceSimple> mDatas;

    private HeaderAndFooterRecyclerViewAdapter mAdapter;

    private String page;

    private boolean mHasMore = true;

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
//                .setH_spacing(10)
//                .setV_spacing(10)
                .setDividerColor(Color.parseColor("#EFEFEF"))
                .build();
        listJuzi.addItemDecoration(itemDecoration);

        listJuzi.addOnScrollListener(mOnScrollListener);
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

        if (sentenceSimples != null) {
            LogUtils.e("page : " + page + " size : " + sentenceSimples.size());
            mDatas.addAll(sentenceSimples);
            mAdapter.notifyDataSetChanged();
        }

        rotateloading.stop();
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
