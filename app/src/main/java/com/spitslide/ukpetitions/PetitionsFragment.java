package com.spitslide.ukpetitions;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spitslide.ukpetitions.data.Petitions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PetitionsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_petitions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<String> data = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petition.parliament.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PetitionsNetwork petitionsNetwork = retrofit.create(PetitionsNetwork.class);
        Call<Petitions> call = petitionsNetwork.getResponse();
        call.enqueue(new Callback<Petitions>() {
            @Override
            public void onResponse(Call<Petitions> call, Response<Petitions> response) {
                Petitions petitions = response.body();
                if(petitions.getData()!=null)
                for (int i = 0; i < petitions.getData().size(); i++) {
                    data.add(petitions.getData().get(i).getAttributes().getAction());
                }
                PetitionsAdapter petitionsAdapter = new PetitionsAdapter(data);
                recyclerView.setAdapter(petitionsAdapter);
            }

            @Override
            public void onFailure(Call<Petitions> call, Throwable t) {

            }
        });


    }


}
