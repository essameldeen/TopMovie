package com.example.essam.myapplication.TopMovie;

import com.example.essam.myapplication.TopMovie.retrofit.Model.Result;

import io.reactivex.Observable;

public interface Repository {


    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    Observable<String> getCountryData();

    Observable<Result> getResultData();
}
