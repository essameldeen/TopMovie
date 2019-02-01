package com.example.essam.myapplication.root;

import com.example.essam.myapplication.TopMovie.TopMovieModule;
import com.example.essam.myapplication.TopMovie.TopMoviesActivity;
import com.example.essam.myapplication.TopMovie.retrofit.ApiModuleInfo;
import com.example.essam.myapplication.TopMovie.retrofit.ApiModuleName;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,ApiModuleName.class,ApiModuleInfo.class,TopMovieModule.class})
public interface ApplicationComponent {
    void inject(TopMoviesActivity topMoviesActivity);
}
