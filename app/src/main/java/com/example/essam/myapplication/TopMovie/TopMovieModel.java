package com.example.essam.myapplication.TopMovie;

import com.example.essam.myapplication.TopMovie.retrofit.Model.Result;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class TopMovieModel implements TopMoviesMVP.Model {
    private Repository repository;

    public TopMovieModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<MovieModel> result() {
       return Observable.zip(repository.getResultData(), repository.getCountryData(), new BiFunction<Result, String, MovieModel>() {
           @Override
           public MovieModel apply(Result result, String s) throws Exception {
               return  new MovieModel(result.title,s);
           }
       });
    }
}
