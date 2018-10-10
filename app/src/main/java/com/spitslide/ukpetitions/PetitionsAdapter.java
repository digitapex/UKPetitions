package com.spitslide.ukpetitions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PetitionsAdapter extends RecyclerView.Adapter<PetitionsAdapter.PetitionsViewHolder> {

    private List<String> data;

    public PetitionsAdapter(List<String> data) {
        this.data = data;
    }

    public class PetitionsViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public PetitionsViewHolder(View v) {
            super(v);
        }
    }

    @NonNull
    @Override
    public PetitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PetitionsViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
