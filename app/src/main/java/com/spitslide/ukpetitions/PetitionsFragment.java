package com.spitslide.ukpetitions;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spitslide.ukpetitions.data.Attributes;
import com.spitslide.ukpetitions.data.Petitions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PetitionsFragment extends Fragment {
    private boolean isLoading;
    private String state;
    private int currentPage = 1;
    private List<PetitionItem> data;
    private PetitionsAdapter petitionsAdapter;
    private boolean isLastPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("state")) {
            state = arguments.getString("state");
        } else {
            state = "all";
        }
        return inflater.inflate(R.layout.fragment_petitions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        data = new ArrayList<>();
        petitionsAdapter = new PetitionsAdapter(data);
        recyclerView.setAdapter(petitionsAdapter);
        retrofitCall(recyclerView, layoutManager, data);


    }

    private void retrofitCall(final RecyclerView recyclerView, final LinearLayoutManager layoutManager, final List<PetitionItem> data) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petition.parliament.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PetitionsNetwork petitionsNetwork = retrofit.create(PetitionsNetwork.class);
        Call<Petitions> call = petitionsNetwork.getResponse(state, currentPage);
        call.enqueue(new Callback<Petitions>() {
            @Override
            public void onResponse(Call<Petitions> call, Response<Petitions> response) {
                Petitions petitions = response.body();
                List<PetitionItem> newData = getDataNextPage(petitions);
                petitionsAdapter.update(newData);
                isLastPage = false;
                if (petitions.getLinks().getNext() == null) {
                    isLastPage = true;
                }
                isLoading = false;
                currentPage++;
                setScrollListener(recyclerView, layoutManager, petitionsAdapter);
            }

            @Override
            public void onFailure(Call<Petitions> call, Throwable t) {

            }
        });
    }

    private void setScrollListener(RecyclerView recyclerView, final LinearLayoutManager layoutManager, final PetitionsAdapter petitionsAdapter) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && !isLoading && !isLastPage) {
                    petitionsAdapter.addLoadingItem(new PetitionItem());
                    isLoading = true;
                    retrofitCall(recyclerView, layoutManager, data);
                }
            }
        });
    }

    private List<PetitionItem> getDataNextPage(Petitions petitions) {
        ArrayList<PetitionItem> data = new ArrayList<>();
        for (int i = 0; i < petitions.getData().size(); i++) {
            PetitionItem petitionItem = new PetitionItem();
            Attributes attributesObj = petitions.getData().get(i).getAttributes();
            petitionItem.setTitle(attributesObj.getAction());
            petitionItem.setSignatureCount(attributesObj.getSignatureCount());
            petitionItem.setBackground(attributesObj.getBackground());
            petitionItem.setAdditionalDetails(attributesObj.getAdditionalDetails());
            if (attributesObj.getGovernmentResponse() != null) {
                petitionItem.setGovResponseSummary(attributesObj.getGovernmentResponse().getSummary());
                petitionItem.setGovResponseDetails(attributesObj.getGovernmentResponse().getDetails());
            }
            petitionItem.setParlDebateThresholdReached(attributesObj.getDebateThresholdReachedAt());
            if (attributesObj.getDebate() != null) {
                petitionItem.setParlDebateTranscript(attributesObj.getDebate().getTranscriptUrl());
                petitionItem.setParlDebateVideo(attributesObj.getDebate().getVideoUrl());
            }
            petitionItem.setState(attributesObj.getState());
            data.add(petitionItem);
        }
        return data;
    }


}
