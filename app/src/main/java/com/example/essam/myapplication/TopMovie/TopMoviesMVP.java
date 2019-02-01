package com.example.essam.myapplication.TopMovie;



import io.reactivex.Observable;

public interface TopMoviesMVP {
    interface  View{
       void updateData(MovieModel viewModel);
       void showSnackPar(String message);

    }

    interface Presenter{

        void setView(TopMoviesMVP.View view);
        void unSubscribe();
        void loadData();

    }
    interface  Model{
       Observable<MovieModel> result();

    }
}
