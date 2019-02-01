package com.example.essam.myapplication.TopMovie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.essam.myapplication.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<MovieModel> listMovie;

    public MoviesAdapter(List<MovieModel> listMovie) {
        this.listMovie = listMovie;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listMovie.get(position).getName());
        holder.country.setText(listMovie.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView country;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            country = (TextView) itemView.findViewById(R.id.country);
        }
    }
}
