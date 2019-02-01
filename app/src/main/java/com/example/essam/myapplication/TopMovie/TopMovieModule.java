package com.example.essam.myapplication.TopMovie;

import com.example.essam.myapplication.TopMovie.retrofit.MoreInfoApiService;
import com.example.essam.myapplication.TopMovie.retrofit.MovieApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TopMovieModule {

    @Provides
    public TopMoviesMVP.Presenter provideTopMoviesActivityPresenter(TopMoviesMVP.Model topMoviesModel) {
        return new TopMoviesPresenter(topMoviesModel);
    }

    @Provides
    public TopMoviesMVP.Model provideTopMoviesActivityModel(Repository repository) {
        return new TopMovieModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        return new TopMovieRepository(movieApiService, moreInfoApiService);
    }

}
