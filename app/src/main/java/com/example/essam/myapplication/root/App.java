package com.example.essam.myapplication.root;

import android.app.Application;

import com.example.essam.myapplication.TopMovie.TopMovieModule;
import com.example.essam.myapplication.TopMovie.retrofit.ApiModuleInfo;
import com.example.essam.myapplication.TopMovie.retrofit.ApiModuleName;

public class App extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModuleName(new ApiModuleName())
                .apiModuleInfo(new ApiModuleInfo())
                .topMovieModule(new TopMovieModule())
                .build();


    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
