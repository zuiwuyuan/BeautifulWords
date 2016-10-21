package com.lnyp.flexibledivider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lnyp.recyclerview.HeaderAndFooterRecyclerViewAdapter;

/**
 * 网格布局分割线
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private Builder builder;

    public GridSpacingItemDecoration(Builder builder) {

        this.builder = builder;

        if (this.builder.mDivider == null) {
            this.builder.mDivider = new ColorDrawable(0x00FFFFFF);
        }
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        RecyclerView.Adapter adapter = parent.getAdapter();

        HeaderAndFooterRecyclerViewAdapter mAdapter = null;
        if (adapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            mAdapter = (HeaderAndFooterRecyclerViewAdapter) adapter;
        }

        int itemCount = adapter.getItemCount();

        for (int i = 0; i < itemCount; i++) {

            View child = parent.getChildAt(i);

            if (child != null) {

                int childPosition = parent.getChildAdapterPosition(child);

                if (builder.hasHeader) {
                    if (childPosition == 0) {
                        continue;
                    } else {
                        childPosition = childPosition - 1;
                    }

                    if (mAdapter != null && mAdapter.isFooter(childPosition + 1)) {
                        continue;
                    }

                } else {

                    if (mAdapter != null && mAdapter.isFooter(childPosition)) {
                        continue;
                    }
                }

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
                final int right = left + builder.h_spacing;
                final int top = child.getTop() + params.topMargin + Math.round(ViewCompat.getTranslationY(child));
                final int bottom = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));

                builder.mDivider.setBounds(left, top, right, bottom);
                builder.mDivider.draw(c);

                if (childPosition >= builder.spanCount) {

                    final int left1 = child.getLeft() + params.leftMargin + Math.round(ViewCompat.getTranslationX(child));
                    final int right1 = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child) + builder.h_spacing);
                    final int top1 = child.getTop() + params.topMargin + Math.round(ViewCompat.getTranslationY(child)) - builder.v_spacing;
                    final int bottom1 = top1 + builder.v_spacing;

                    builder.mDivider.setBounds(left1, top1, right1, bottom1);
                    builder.mDivider.draw(c);
                }
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);

        RecyclerView.Adapter adapter = parent.getAdapter();

        HeaderAndFooterRecyclerViewAdapter mAdapter = null;

        if (adapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            mAdapter = (HeaderAndFooterRecyclerViewAdapter) adapter;
        }
        /**
         * header和footer
         */
        if (builder.hasHeader) {

            if (position == 0) {
                return;
            } else {
                position = position - 1;
            }

            if (mAdapter != null && mAdapter.isFooter(position + 1)) {
                return;
            }
        } else {

            if (mAdapter != null && mAdapter.isFooter(position)) {
                return;
            }
        }

        int column = position % builder.spanCount;

        outRect.left = column * builder.h_spacing / builder.spanCount;
        outRect.right = builder.h_spacing - (column + 1) * builder.h_spacing / builder.spanCount;
        if (position >= builder.spanCount) {
            outRect.top = builder.v_spacing;
        }
    }

    public static class Builder {

        private Context mContext;

        private Drawable mDivider;

        private int spanCount;

        private int h_spacing = 1;

        private int v_spacing = 1;

        private boolean hasHeader = false;

        public Builder(Context context, int spanCount) {
            this.mContext = context;
            this.spanCount = spanCount;
        }

        public Builder hasHeader() {
            this.hasHeader = true;
            return this;
        }

        public Builder setDividerColor(int color) {
            this.mDivider = new ColorDrawable(color);
            return this;
        }

        public Builder setmDivider(Drawable mDivider) {
            this.mDivider = mDivider;
            return this;
        }

        public Builder setH_spacing(int h_spacing) {
            this.h_spacing = h_spacing;
            return this;
        }

        public Builder setV_spacing(int v_spacing) {
            this.v_spacing = v_spacing;
            return this;
        }

        public Builder setSpanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }

        public GridSpacingItemDecoration build() {
            return new GridSpacingItemDecoration(this);
        }
    }
}