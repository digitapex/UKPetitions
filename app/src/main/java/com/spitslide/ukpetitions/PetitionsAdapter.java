package com.spitslide.ukpetitions;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class PetitionsAdapter extends RecyclerView.Adapter<PetitionsAdapter.PetitionsViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded;
    private List<PetitionItem> data;

    public PetitionsAdapter(List<PetitionItem> data) {
        this.data = data;
    }

    public class PetitionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        public TextView title;
        public TextView signatures;
        public TextView closedStatus;
        public RelativeLayout recyclerItem;

        public PetitionsViewHolder(View v) {
            super(v);
            context = v.getContext();
            title = v.findViewById(R.id.title);
            signatures = v.findViewById(R.id.signatures);
            closedStatus = v.findViewById(R.id.close_status);
            recyclerItem = v.findViewById(R.id.recycler_item);
            if (recyclerItem != null) {
                recyclerItem.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            PetitionItem petitionItem = data.get(getLayoutPosition());
            Intent intent = new Intent(context, PetitionItemActivity.class);
            intent.putExtra("PetitionItem", petitionItem);
            context.startActivity(intent);
        }

    }


    @NonNull
    @Override
    public PetitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PetitionsViewHolder petitionsViewHolder = null;
        switch (viewType) {
            case ITEM:
                petitionsViewHolder = new PetitionsViewHolder(inflater.inflate(R.layout.item_layout, parent, false));
                break;
            case LOADING:
                petitionsViewHolder = new PetitionsViewHolder(inflater.inflate(R.layout.item_loading, parent, false));
                break;
        }
        return petitionsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PetitionsViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                PetitionItem item = data.get(position);
                viewHolder.title.setText(item.getTitle());
                String sigWithSeparator = String.format("%,d", item.getSignatureCount());
                viewHolder.signatures.setText(sigWithSeparator + " signatures");
                viewHolder.closedStatus.setText(item.getState());
                break;
            case LOADING:
                break;
        }

    }

    public List<PetitionItem> getCurrentData() {
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        // isLoadingAdded needed here, because otherwise the last item gets replaced with loading indicator
        return (position == data.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void addLoadingItem(PetitionItem petitionItem) {
        isLoadingAdded = true;
        data.add(petitionItem);
        notifyDataSetChanged();
    }

    public void update(List<PetitionItem> newData) {
        isLoadingAdded = false;
        int last = data.size() - 1;
        // this check needed because on first retrofit call it's -1
        if (last > 0) {
            // removing the loading item
            data.remove(last);
        }
        data.addAll(newData);
        notifyDataSetChanged();
    }
}
