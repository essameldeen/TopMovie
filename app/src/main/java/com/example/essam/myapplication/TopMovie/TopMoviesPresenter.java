package com.example.essam.myapplication.TopMovie;

import com.example.essam.myapplication.TopMovie.TopMoviesMVP;




import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TopMoviesPresenter  implements TopMoviesMVP.Presenter {
     private  TopMoviesMVP.View  view;
     private  TopMoviesMVP.Model model;
    private Disposable subscription = null;

     public TopMoviesPresenter(TopMoviesMVP.Model model){
         this.model = model;
     }


    @Override
    public void setView(TopMoviesMVP.View view) {
         this.view = view;
    }

    @Override
    public void unSubscribe() {
       if(subscription!=null){
           if(!subscription.isDisposed()){
               subscription.dispose();
           }
       }
    }

    @Override
    public void loadData() {
         subscription = model.result().
                 subscribeOn(Schedulers.io()).
                 observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<MovieModel>() {
            @Override
            public void onComplete() {
            }


            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (view != null) {
                    view.showSnackPar("Error getting movies");
                }
            }

            @Override
            public void onNext(MovieModel viewModel) {
                if (view != null) {
                    view.updateData(viewModel);
                }
            }
        });



    }
}
