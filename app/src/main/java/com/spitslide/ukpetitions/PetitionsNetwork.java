package com.spitslide.ukpetitions;


import com.spitslide.ukpetitions.data.Petitions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PetitionsNetwork {

    @GET("petitions.json")
    Call<Petitions> getResponse();
}
