package com.chenyuan.sentence.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenyuan.sentence.R;
import com.chenyuan.sentence.mvp.model.bean.SentenceCollection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ju'ji句集
 */
public class JujiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;

    private Fragment mContext;

    private List<SentenceCollection> mDatas;

    private View.OnClickListener onItemClick;

    public JujiAdapter(Fragment context, List<SentenceCollection> datas, View.OnClickListener onItemClick) {

        this.mContext = context;

        this.mDatas = datas;

        this.onItemClick = onItemClick;

        mInflater = LayoutInflater.from(context.getActivity());

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.list_item_scene_juji, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        SentenceCollection sentenceCollection = mDatas.get(position);

        if (sentenceCollection != null) {

            Glide.with(mContext)
                    .load(sentenceCollection.getImgUrl())
                    .asBitmap()
                    .placeholder(R.drawable.load_default_img)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(viewHolder.imgView);

            viewHolder.textTitle.setText(sentenceCollection.getTitle());
            viewHolder.textDesc.setText(sentenceCollection.getDesc());

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
        public ImageView imgView;

        @BindView(R.id.textTitle)
        public TextView textTitle;

        @BindView(R.id.textDesc)
        public TextView textDesc;


        public ViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
