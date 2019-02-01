package com.example.essam.myapplication.TopMovie.retrofit;

import com.example.essam.myapplication.TopMovie.retrofit.Model.TopRated;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("top_rated")
    Observable<TopRated> getTopRated(@Query("page") Integer numPage); // get the data by paging  and specify # of page
}
