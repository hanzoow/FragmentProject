package com.example.simpleprojectroom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class LinearLayoutManagerWrapper extends LinearLayoutManager {
    public LinearLayoutManagerWrapper(Context context) {
        super(context);
    }

    public LinearLayoutManagerWrapper(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public LinearLayoutManagerWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}
