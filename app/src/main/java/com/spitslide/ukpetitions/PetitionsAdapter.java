package com.spitslide.ukpetitions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PetitionsAdapter extends RecyclerView.Adapter<PetitionsAdapter.PetitionsViewHolder> {

    private List<PetitionItem> data;

    public PetitionsAdapter(List<PetitionItem> data) {
        this.data = data;
    }

    public class PetitionsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView signatures;

        public PetitionsViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            signatures = v.findViewById(R.id.signatures);
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
        viewHolder.title.setText(data.get(position).getTitle());
        String sigWithSeparator = String.format("%,d", data.get(position).getSignatureCount());
        viewHolder.signatures.setText(sigWithSeparator);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
