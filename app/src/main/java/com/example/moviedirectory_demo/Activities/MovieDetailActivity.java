package com.example.moviedirectory_demo.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirectory_demo.Model.Movie;
import com.example.moviedirectory_demo.R;
import com.example.moviedirectory_demo.Util.Constants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieBudget;
    private TextView movieYear;
    private TextView movieVoteCount;
    private TextView movieVoteAvg;
    private TextView productionCompanies;
    private TextView actors;
    private TextView category;
    private TextView rating;
    private TextView writers;
    private TextView plot;
    private TextView boxOffice;
    private TextView runTime;
    private YouTubePlayerView youTubePlayerView;

    private RequestQueue queue;
    private String movieID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        queue = Volley.newRequestQueue(this);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieID = movie.getImdbID();


        setUpUI();
        getMovieDetails(movieID);
    }

    private void getMovieDetails(String id) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_DET_LEFT2 + id + Constants.URL_DET_RIGHT2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
//                    if (response.has("Ratings")) {
//                        JSONArray ratings = response.getJSONArray("Ratings");
//
//                        String source = null;
//                        String value = null;
//
//                        if (ratings.length() > 0) {
//                            JSONObject mRating = ratings.getJSONObject(ratings.length() - 1);
//
//                            source = mRating.getString("Source");
//                            value = mRating.getString("Value");
//
//                            rating.setText(source + ":" + value);
//                        } else {
//                            rating.setText("Rating: N/A");
//                        }
//                        movieTitle.setText(response.getString("title"));
//                        movieYear.setText("Released: " + response.getString("release_date"));
////                        directors.setText("Directors: "+ response.getString("Director"));
//                        writers.setText("Writers: " + response.getString("Writer"));
//                        plot.setText("Plot: " + response.getString("overview"));
//                        runTime.setText("Runtime: " + response.getString("runtime"));
//                        actors.setText("Actors: " + response.getString("Actors"));
//                        boxOffice.setText("Box Office: " + response.getString("BoxOffice"));
//
//                        Picasso.with(getApplicationContext()).load(Constants.IMAGE_HEADER + response.getString("poster_path")).into(movieImage);
//                    }


                    movieTitle.setText(response.getString("title"));

                    if (response.has("genres")) {

                        JSONArray geners = response.getJSONArray("genres");

                        String id = null;
                        String name = null;

                        if (geners.length() > 0) {
                            JSONObject mGeners = geners.getJSONObject(0);

                            id = mGeners.getString("id");
                            name = mGeners.getString("name");
                            category.setText(category.getText() + name + "");
                            for (int i = 1; i < geners.length(); i++) {
                                mGeners = geners.getJSONObject(i);

                                id = mGeners.getString("id");
                                name = mGeners.getString("name");

                                category.setText(category.getText() + ", " + name);
                            }
                        } else {
                            category.setText("category: N/A");
                        }

                    }

                    if (response.has("production_companies")) {

                        JSONArray production_companies = response.getJSONArray("production_companies");

                        String id = null;
                        String name = null;

                        if (production_companies.length() > 0) {
                            JSONObject mProductionCompanies = production_companies.getJSONObject(0);

                            id = mProductionCompanies.getString("id");
                            name = mProductionCompanies.getString("name");
                            productionCompanies.setText(productionCompanies.getText() + name + "");
                            for (int i = 1; i < production_companies.length(); i++) {
                                mProductionCompanies = production_companies.getJSONObject(i);

                                id = mProductionCompanies.getString("id");
                                name = mProductionCompanies.getString("name");

                                productionCompanies.setText(productionCompanies.getText() + ", " + name);
                            }
                        } else {
                            productionCompanies.setText("Production Companies: N/A");
                        }

                    }
                    movieYear.setText("Released: " + response.getString("release_date"));
                    movieBudget.setText("Budget: " + response.getString("budget"));
                    movieVoteAvg.setText("Rating: " + response.getString("vote_average"));
                    movieVoteCount.setText("Vote Count: " + response.getString("vote_count"));
                    plot.setText("Plot: " + response.getString("overview"));
                    runTime.setText("Runtime: " + response.getString("runtime"));
                    boxOffice.setText("Box Office: " + response.getString("revenue"));

                    Picasso.with(getApplicationContext()).load(Constants.IMAGE_HEADER + response.getString("poster_path")).into(movieImage);

                    getMovieTrailer(movieID);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Error:", error.getMessage());

            }
        });
        queue.add(jsonObjectRequest);
    }

    private void setUpUI() {

        movieTitle = (TextView) findViewById(R.id.movieTiteleIDDets);
        movieBudget = (TextView) findViewById(R.id.budgetDets);
        movieImage = (ImageView) findViewById(R.id.movieImageIDDets);
        movieYear = (TextView) findViewById(R.id.movieReleasedIDDets);
        productionCompanies = (TextView) findViewById(R.id.productionCompaniesByDets);
        category = (TextView) findViewById(R.id.movieCatIDDets);
        movieVoteAvg = (TextView) findViewById(R.id.voteAverageDet);
        movieVoteCount = (TextView) findViewById(R.id.voteCountDet);
        plot = (TextView) findViewById(R.id.plotDet);
        boxOffice = (TextView) findViewById(R.id.revenueDet);
        runTime = (TextView) findViewById(R.id.runtimeDets);
        youTubePlayerView = findViewById(R.id.theTrailer);
        getLifecycle().addObserver(youTubePlayerView);
    }

    private void getMovieTrailer(String id) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.tserach1 + id + Constants.tserach2, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray moviesArray = response.getJSONArray("results");
                    JSONObject movieObject = moviesArray.getJSONObject(0);
                    final String videoId = movieObject.getString("key");
                    youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0);
                        }
                    });


//                        Log.i("Movies: ",movie.getTitle());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(jsonObjectRequest);

    }
}
