package com.example.moviedirectory_demo.Data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedirectory_demo.Activities.MovieDetailActivity;
import com.example.moviedirectory_demo.Model.Movie;
import com.example.moviedirectory_demo.R;
import com.example.moviedirectory_demo.Util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

   private Context context;
   private List<Movie> movieList;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies)  {
        this.context = context;
        movieList = movies;

    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder holder, int position) {

        Movie movie = movieList.get(position);

        String posterLink = movie.getPoster();

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.rating.setText(movie.getRating());
        Picasso.with(context).load(Constants.IMAGE_HEADER +posterLink).placeholder(R.drawable.ic_local_movies_black_24dp)
                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView year;
        TextView rating;
        ImageView poster;

        public ViewHolder(@NonNull View itemView , Context ctx) {
            super(itemView);
            context = ctx;

            title = (TextView) itemView.findViewById(R.id.movieTiteleID);
            year = (TextView) itemView.findViewById(R.id.movieReleasedID);
            rating = (TextView) itemView.findViewById(R.id.movieRating);
            poster = (ImageView) itemView.findViewById(R.id.imageID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Movie movie = movieList.get(getAdapterPosition());
                    Intent intent = new Intent (context, MovieDetailActivity.class);
                    intent.putExtra("movie",movie);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }
}
