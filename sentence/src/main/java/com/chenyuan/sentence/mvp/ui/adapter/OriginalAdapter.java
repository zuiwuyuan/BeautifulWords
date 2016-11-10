package com.chenyuan.sentence.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.bean.SentenceDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 原创句子
 */
public class OriginalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;

    private Fragment mContext;

    private List<SentenceDetail> mDatas;

    private View.OnClickListener onItemClick;

    public OriginalAdapter(Fragment context, List<SentenceDetail> datas, View.OnClickListener onItemClick) {

        this.mContext = context;

        this.mDatas = datas;

        this.onItemClick = onItemClick;

        mInflater = LayoutInflater.from(context.getActivity());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.list_item_scene_original, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        SentenceDetail sentenceDetail = mDatas.get(position);

        if (sentenceDetail != null) {

            viewHolder.textDesc.setText(sentenceDetail.getContent());
        }
    }

    @Override
    public int getItemCount() {

        return mDatas != null ? mDatas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textDesc)
        public TextView textDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
