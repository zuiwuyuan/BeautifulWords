package com.otb.designerassist.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.otb.designerassist.R;
import com.otb.designerassist.mvp.model.entity.SentenceImageText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 笑话列表
 */
public class JokeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;

    private Fragment mContext;

    private List<SentenceImageText> mDatas;

    private View.OnClickListener onItemClick;

    private View.OnLongClickListener onLongClickListener;

    private int screenWidth;

    public JokeListAdapter(Fragment context, List<SentenceImageText> datas, View.OnClickListener onItemClick) {

        this.mContext = context;

        this.mDatas = datas;

        this.onItemClick = onItemClick;

        mInflater = LayoutInflater.from(context.getActivity());

        DisplayMetrics metric = new DisplayMetrics();
        context.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.list_item_scene_imgtext, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        SentenceImageText sentenceImageText = mDatas.get(position);

        if (sentenceImageText != null) {

            LogUtils.e(sentenceImageText);

            Glide.with(mContext)
                    .load(sentenceImageText.getPic())
                    .asBitmap()
                    .centerCrop()
                    .into(viewHolder.imgView);

            viewHolder.textTitle.setText(sentenceImageText.getText());

        }
    }

    @Override
    public int getItemCount() {

        return mDatas != null ? mDatas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgView)
        public ImageView imgView;

        @BindView(R.id.textTitle)
        public TextView textTitle;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
