package com.example.essam.myapplication.TopMovie.retrofit;

import com.example.essam.myapplication.TopMovie.retrofit.Model.OmdApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoreInfoApiService {
    @GET("/")
    Observable<OmdApi> getCountry(@Query("t") String title);
}
