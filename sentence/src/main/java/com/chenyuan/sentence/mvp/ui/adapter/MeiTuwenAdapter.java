package com.chenyuan.sentence.mvp.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.bean.SentenceImageText;
import com.chenyuan.sentence.util.StringUtil;
import com.chenyuan.sentence.widget.ShowMaxImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图文
 */
public class MeiTuwenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;

    private Activity mContext;

    private List<SentenceImageText> mDatas;

    private View.OnClickListener onItemClick;

    public MeiTuwenAdapter(Activity context, List<SentenceImageText> datas, View.OnClickListener onItemClick) {

        this.mContext = context;

        this.mDatas = datas;

        this.onItemClick = onItemClick;

        mInflater = LayoutInflater.from(context);

        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
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

            Glide.with(mContext)
                    .load(sentenceImageText.getPic())
                    .asBitmap()
                    .placeholder(R.drawable.load_default_img)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(viewHolder.imgView);

            if (StringUtil.isEmpty(sentenceImageText.getDesc())) {

                viewHolder.textDesc.setVisibility(View.GONE);
            } else {

                viewHolder.textDesc.setVisibility(View.VISIBLE);
                viewHolder.textDesc.setText(sentenceImageText.getDesc());
            }

            viewHolder.itemView.setTag(position);
            viewHolder.itemView.setOnClickListener(onItemClick);
        } else {
            Glide.clear(viewHolder.imgView);
            // remove the placeholder (optional); read comments below
            viewHolder.imgView.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {

        return mDatas != null ? mDatas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgView)
        public ShowMaxImageView imgView;

        @BindView(R.id.textDesc)
        public TextView textDesc;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
