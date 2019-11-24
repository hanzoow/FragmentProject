package com.example.simpleprojectroom.adapter.ViewHolder;


import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleprojectroom.R;

public class ViewHolderLoading extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public ViewHolderLoading(View view) {
        super(view);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }
}
