package com.chenyuan.sentence.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.bean.SceneListDetail;
import com.chenyuan.sentence.mvp.model.bean.SentenceImageText;
import com.chenyuan.sentence.mvp.presenter.impl.ImgTextPresenter;
import com.chenyuan.sentence.mvp.presenter.impl.JuziDetailPresenter;
import com.chenyuan.sentence.mvp.ui.adapter.MeiTuwenAdapter;
import com.chenyuan.sentence.mvp.ui.view.IJuziDetailView;
import com.lnyp.flexibledivider.HorizontalDividerItemDecoration;
import com.lnyp.recyclerview.EndlessRecyclerOnScrollListener;
import com.lnyp.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter;
import com.lnyp.recyclerview.RecyclerViewStateUtils;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 句集详情
 */
public class JujiDetailActivity extends AppCompatActivity implements IJuziDetailView {

    private String baseurl;

    private String url;

    private JuziDetailPresenter mJuziDetailPresenter;

    @BindView(R.id.layoutSwipeRefresh)
    public SwipeRefreshLayout layoutSwipeRefresh;

    @BindView(R.id.listJuzi)
    public RecyclerView listJuzi;

    @BindView(R.id.rotateloading)
    public RotateLoading rotateloading;

    private String type;

    private ImgTextPresenter imgTextPresenter;

    private View view;

    private List<SentenceImageText> mDatas;

    private HeaderAndFooterRecyclerViewAdapter mAdapter;

    private String page;

    private boolean mHasMore = true;

    private boolean isRefresh = true;

    public static void actionStart(Context context, String url) {

        Intent intent = new Intent(context, JujiDetailActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juji_detail);

        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");

        baseurl = url;

        LogUtils.e("url : " + url);

        mJuziDetailPresenter = new JuziDetailPresenter(this);

        initView();

        rotateloading.start();
        qryJuji();
    }

    private void initView() {

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(getString(R.string.app_name));

        mDatas = new ArrayList<>();

        MeiTuwenAdapter meiTuwenAdapter = new MeiTuwenAdapter(this, mDatas, onClickListener);
        mAdapter = new HeaderAndFooterRecyclerViewAdapter(meiTuwenAdapter);
        listJuzi.setAdapter(mAdapter);

        listJuzi.setLayoutManager(new LinearLayoutManager(JujiDetailActivity.this));
        listJuzi.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(JujiDetailActivity.this)
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

            qryJuji();
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
                RecyclerViewStateUtils.setFooterViewState(JujiDetailActivity.this, listJuzi, mHasMore, RecyclerViewLoadingFooter.State.Loading, null);
                qryJuji();

            } else {
                RecyclerViewStateUtils.setFooterViewState(JujiDetailActivity.this, listJuzi, mHasMore, RecyclerViewLoadingFooter.State.TheEnd, null);
            }
        }
    };

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(JujiDetailActivity.this, listJuzi, mHasMore, RecyclerViewLoadingFooter.State.Loading, null);

            qryJuji();
        }
    };

    private void qryJuji() {
        mJuziDetailPresenter.loadJuziDetail(this, isRefresh, url);
    }

    private String totalpage;

    @Override
    public void onSuccess(SceneListDetail sceneListDetail) {

        if (sceneListDetail == null) {
            rotateloading.stop();
            layoutSwipeRefresh.setRefreshing(false);

            RecyclerViewStateUtils.setFooterViewState(listJuzi, RecyclerViewLoadingFooter.State.Normal);
            return;
        }
        List<SentenceImageText> sentenceImageTexts = sceneListDetail.mImageTexts;

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

            totalpage = sceneListDetail.page;
        }

        url = baseurl + "?page=" + page;

        if (page.equals(totalpage)) {
            mHasMore = false;
        }

        LogUtils.e("mHasMore : " + mHasMore + "  currentpage : " + page + "  totalpage : " + totalpage);


        if (sentenceImageTexts != null) {
            mDatas.addAll(sentenceImageTexts);
            mAdapter.notifyDataSetChanged();
        }

        rotateloading.stop();
        layoutSwipeRefresh.setRefreshing(false);

        RecyclerViewStateUtils.setFooterViewState(listJuzi, RecyclerViewLoadingFooter.State.Normal);
    }

    @Override
    public void onError(Throwable e) {
        layoutSwipeRefresh.setRefreshing(false);
        rotateloading.stop();
        RecyclerViewStateUtils.setFooterViewState(JujiDetailActivity.this, listJuzi, mHasMore, RecyclerViewLoadingFooter.State.NetWorkError, mFooterClick);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int pos = (int) view.getTag();
            SentenceImageText sentenceImageText = mDatas.get(pos);
            LogUtils.e(sentenceImageText);
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
