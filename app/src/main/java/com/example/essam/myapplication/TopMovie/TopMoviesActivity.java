package com.example.essam.myapplication.TopMovie;


import android.arch.lifecycle.ViewModel;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.essam.myapplication.R;
import com.example.essam.myapplication.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesMVP.View {


    @Inject
    public TopMoviesMVP.Presenter presenter;


    RecyclerView recyclerView;


    ViewGroup viewGroup;

    private List<MovieModel> movieModels = new ArrayList<>();
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);
        ((App) getApplication()).getApplicationComponent().inject(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        viewGroup = (ViewGroup)findViewById(R.id.root_view);


        // set up recycler View and Adapter
        adapter = new MoviesAdapter(movieModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }


    @Override
    public void updateData(MovieModel viewModel) {
        movieModels.add(viewModel);
        adapter.notifyItemInserted(movieModels.size() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
        movieModels.clear();
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showSnackPar(String message) {
        Snackbar.make(viewGroup, message, Snackbar.LENGTH_LONG);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
