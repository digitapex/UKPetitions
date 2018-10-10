package com.spitslide.ukpetitions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
        public TextView textView;

        public PetitionsViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.text_view);
        }
    }

    @NonNull
    @Override
    public PetitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PetitionsViewHolder(inflater.inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PetitionsViewHolder viewHolder, int position) {
        viewHolder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
