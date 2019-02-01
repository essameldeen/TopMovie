package com.example.essam.myapplication.TopMovie;

import com.example.essam.myapplication.TopMovie.retrofit.ApiModuleInfo;
import com.example.essam.myapplication.TopMovie.retrofit.ApiModuleName;
import com.example.essam.myapplication.TopMovie.retrofit.Model.OmdApi;
import com.example.essam.myapplication.TopMovie.retrofit.Model.Result;
import com.example.essam.myapplication.TopMovie.retrofit.Model.TopRated;
import com.example.essam.myapplication.TopMovie.retrofit.MoreInfoApiService;
import com.example.essam.myapplication.TopMovie.retrofit.MovieApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TopMovieRepository implements Repository {
    private MovieApiService movieApiService;
    private MoreInfoApiService moreInfoApiService;
    private List<String> countries;
    private List<Result> results;
    private long timestamp;

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    public TopMovieRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        this.moreInfoApiService = moreInfoApiService;
        this.timestamp = System.currentTimeMillis();
        this.movieApiService = movieApiService;
        countries = new ArrayList<>();
        results = new ArrayList<>();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Result> getResultsFromMemory() {
        if (isUpToDate() && results.size()>0) {
            return Observable.fromIterable(results);
        } else {
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {

        Observable<TopRated> topRatedObservable = movieApiService.getTopRated(1).concatWith(movieApiService.getTopRated(2)).concatWith(movieApiService.getTopRated(3));

        return topRatedObservable.concatMap(new Function<TopRated, Observable<Result>>() {
            @Override
            public Observable<Result> apply(TopRated topRated) {
                return Observable.fromIterable(topRated.results);
            }
        }).doOnNext(new Consumer<Result>() {
            @Override
            public void accept(Result result) {
                results.add(result);
            }
        });
    }

    @Override
    public Observable<String> getCountriesFromMemory() {
        if (isUpToDate() && countries.size()>0) {
            return Observable.fromIterable(countries);
        } else {
            timestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {
        return getResultsFromNetwork().concatMap(new Function<Result, Observable<OmdApi>>() {
            @Override
            public Observable<OmdApi> apply(Result result) {
                return moreInfoApiService.getCountry(result.title);
            }
        }).concatMap(new Function<OmdApi, Observable<String>>() {
            @Override
            public Observable<String> apply(OmdApi omdbApi) {
                return Observable.just(omdbApi.getCountry());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) {
                countries.add(s);
            }
        });
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }
}
