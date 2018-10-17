package com.spitslide.ukpetitions;


import com.spitslide.ukpetitions.data.Petitions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PetitionsNetwork {

    @GET("{archived}/petitions.json")
    Call<Petitions> getResponse(@Path("archived") String archived, @Query("state") String state, @Query("page") int page, @Query("q") String searchQuery);
}
