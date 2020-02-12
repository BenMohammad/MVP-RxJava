package com.benmohammad.mvp_rxjava.presentation.adapters;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    public SpacingItemDecoration(int mSpacing) {
        this.mSpacing = mSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        int itemCount =state.getItemCount();
        outRect.left = mSpacing;
        outRect.right = mSpacing;
        outRect.top = mSpacing;
        outRect.bottom = position == itemCount - 1? mSpacing : 0;

    }
}
