package com.otb.designerassist.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyp.flexibledivider.HorizontalDividerItemDecoration;
import com.otb.designerassist.R;
import com.otb.designerassist.mvp.model.entity.SentenceImageText;
import com.otb.designerassist.mvp.presenter.impl.ImgTextPresenter;
import com.otb.designerassist.mvp.ui.adapter.JokeListAdapter;
import com.otb.designerassist.mvp.ui.view.IMeituMeijuView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMeijuList extends Fragment implements IMeituMeijuView {

    private static final String ARG_TYPE = "type";

    @BindView(R.id.listJuzi)
    public RecyclerView listJuzi;

    private String type;

    private ImgTextPresenter imgTextPresenter;

    private View view;

    private Unbinder unbinder;

    private String page;

    private List<SentenceImageText> mDatas;

    private JokeListAdapter jokeListAdapter;

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

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_meiju_list, container, false);
        }

        unbinder = ButterKnife.bind(this, view);

        imgTextPresenter = new ImgTextPresenter(this);

        if (TextUtils.isEmpty(type)) {
            imgTextPresenter.loadImgText(getActivity(), page);
        } else {
            imgTextPresenter.loadImgText(getActivity(), type, page);
        }

        initView();

        return view;
    }

    private void initView() {

        mDatas = new ArrayList<>();

        jokeListAdapter = new JokeListAdapter(this, mDatas, onClickListener);
        listJuzi.setAdapter(jokeListAdapter);

        listJuzi.setLayoutManager(new LinearLayoutManager(getActivity()));
        listJuzi.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
//                        .colorResId(R.color.divider_color)
                        .size(20)
                        .build());

//        listJuzi.addOnScrollListener(mOnScrollListener);

//        swipeRefreshLayout.setRefreshDrawable(new SmartisanDrawable(getActivity(), swipeRefreshLayout));
//        swipeRefreshLayout.setBackgroundColor(Color.parseColor("#EFEFEF"));
//        swipeRefreshLayout.setColor(Color.parseColor("#8F8F81"));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void onSuccess(List<SentenceImageText> sentenceImageTexts) {
        if (sentenceImageTexts != null) {
            mDatas.addAll(sentenceImageTexts);
            jokeListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
