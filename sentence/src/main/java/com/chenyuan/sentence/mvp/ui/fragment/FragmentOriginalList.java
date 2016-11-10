package com.chenyuan.sentence.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.bean.SentenceDetail;
import com.chenyuan.sentence.mvp.presenter.impl.OrignalPresenter;
import com.chenyuan.sentence.mvp.ui.adapter.OriginalAdapter;
import com.chenyuan.sentence.mvp.ui.view.IOrignalView;
import com.lnyp.flexibledivider.HorizontalDividerItemDecoration;
import com.lnyp.recyclerview.EndlessRecyclerOnScrollListener;
import com.lnyp.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter;
import com.lnyp.recyclerview.RecyclerViewStateUtils;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

public class FragmentOriginalList extends BaseFragment implements IOrignalView {

    private static final String ARG_TYPE = "type";

    public SwipeRefreshLayout layoutSwipeRefresh;

    public RecyclerView listJuzi;

    public RotateLoading rotateloading;

    private String type;

    private OrignalPresenter orignalPresenter;

    private View view;

    private List<SentenceDetail> mDatas;

    private HeaderAndFooterRecyclerViewAdapter mAdapter;

    private String page;

    private boolean mHasMore = true;

    private boolean isRefresh = true;

    public FragmentOriginalList() {
    }

    public static FragmentOriginalList newInstance(String type) {

        FragmentOriginalList fragment = new FragmentOriginalList();
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
            view = inflater.inflate(R.layout.fragment_original_list, container, false);
        }

        initView();

        orignalPresenter = new OrignalPresenter(this);

        rotateloading.start();
        qryMeijus();

        return view;
    }

    private void initView() {

        layoutSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.layoutSwipeRefresh);
        rotateloading = (RotateLoading) view.findViewById(R.id.rotateloading);
        listJuzi = (RecyclerView) view.findViewById(R.id.listJuzi);

        mDatas = new ArrayList<>();

        OriginalAdapter originalAdapter = new OriginalAdapter(this, mDatas, onClickListener);
        mAdapter = new HeaderAndFooterRecyclerViewAdapter(originalAdapter);
        listJuzi.setAdapter(mAdapter);

        listJuzi.setLayoutManager(new LinearLayoutManager(getActivity()));
        listJuzi.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .colorResId(R.color.divider_color)
                        .size(4)
                        .build());

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
        orignalPresenter.loadOriginal(getActivity(), type, page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Override
    public void onSuccess(List<SentenceDetail> sentenceDetails) {
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

        if (sentenceDetails != null) {
            mDatas.addAll(sentenceDetails);
            mAdapter.notifyDataSetChanged();
        }

        rotateloading.stop();
        layoutSwipeRefresh.setRefreshing(false);
        RecyclerViewStateUtils.setFooterViewState(listJuzi, RecyclerViewLoadingFooter.State.Normal);
    }

    @Override
    public void onError(Throwable e) {
        rotateloading.stop();
        layoutSwipeRefresh.setRefreshing(false);
        RecyclerViewStateUtils.setFooterViewState(getActivity(), listJuzi, mHasMore, RecyclerViewLoadingFooter.State.NetWorkError, mFooterClick);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
