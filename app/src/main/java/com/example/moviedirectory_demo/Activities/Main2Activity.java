package com.example.moviedirectory_demo.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirectory_demo.Data.MovieRecyclerViewAdapter;
import com.example.moviedirectory_demo.Model.Movie;
import com.example.moviedirectory_demo.Util.Constants;
import com.example.moviedirectory_demo.Util.Prefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.moviedirectory_demo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView nowInCinemaRV;
    private RecyclerView topRatedRV;
    private RecyclerView popularRV;
    private RecyclerView upcomingRV;
    private MovieRecyclerViewAdapter nowInCRecyclerViewAdapter;
    private MovieRecyclerViewAdapter topRatedRecyclerViewAdapter;
    private MovieRecyclerViewAdapter popularRecyclerViewAdapter;
    private MovieRecyclerViewAdapter upcomingRecyclerViewAdapter;
    private List<Movie> NICMovieList;
    private List<Movie> TRMovieList;
    private List<Movie> POPMovieList;
    private List<Movie> UPCMovieList;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        setNowInCinemaRV();
        setTopRatedRV();
        setPopularRV();
        setUpcomingRV();

    }

    //Get now in cinema movies from API
    public List<Movie> getNowInCinemaMovies(String searchTerm) {
        NICMovieList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_DET_LEFT2 + searchTerm + Constants.URL_DET_RIGHT2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray moviesArray = response.getJSONArray("results");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObject = moviesArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObject.getString("title"));
                        movie.setYear("Year Released: " + movieObject.getString("release_date"));
                        movie.setPoster(movieObject.getString("poster_path"));
                        movie.setImdbID(movieObject.getString("id"));
                        movie.setRating("Rating:" + movieObject.getString("vote_average"));


//                        Log.i("Movies: ",movie.getTitle());

                        NICMovieList.add(movie);
                    }
                    nowInCRecyclerViewAdapter.notifyDataSetChanged();

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
        return NICMovieList;
    }

    //Get top rated movies from API
    public List<Movie> getTopRatedMovies(String searchTerm) {
        TRMovieList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_DET_LEFT2 + searchTerm + Constants.URL_DET_RIGHT2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray moviesArray = response.getJSONArray("results");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObject = moviesArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObject.getString("title"));
                        movie.setYear("Year Released: " + movieObject.getString("release_date"));
                        movie.setPoster(movieObject.getString("poster_path"));
                        movie.setImdbID(movieObject.getString("id"));
                        movie.setRating("Rating:" + movieObject.getString("vote_average"));


//                        Log.i("Movies: ",movie.getTitle());

                        TRMovieList.add(movie);
                    }
                    topRatedRecyclerViewAdapter.notifyDataSetChanged();

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
        return TRMovieList;
    }

    //Get top popular movies from API
    public List<Movie> getPopularMovies(String searchTerm) {
        TRMovieList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_DET_LEFT2 + searchTerm + Constants.URL_DET_RIGHT2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray moviesArray = response.getJSONArray("results");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObject = moviesArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObject.getString("title"));
                        movie.setYear("Year Released: " + movieObject.getString("release_date"));
                        movie.setPoster(movieObject.getString("poster_path"));
                        movie.setImdbID(movieObject.getString("id"));
                        movie.setRating("Rating:" + movieObject.getString("vote_average"));


//                        Log.i("Movies: ",movie.getTitle());

                        POPMovieList.add(movie);
                    }
                    popularRecyclerViewAdapter.notifyDataSetChanged();

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
        return POPMovieList;
    }

    //Get top upcoming movies from API
    public List<Movie> getUpcomingMovies(String searchTerm) {
        TRMovieList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_DET_LEFT2 + searchTerm + Constants.URL_DET_RIGHT2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray moviesArray = response.getJSONArray("results");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObject = moviesArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObject.getString("title"));
                        movie.setYear("Year Released: " + movieObject.getString("release_date"));
                        movie.setPoster(movieObject.getString("poster_path"));
                        movie.setImdbID(movieObject.getString("id"));
                        movie.setRating("Rating:" + movieObject.getString("vote_average"));


//                        Log.i("Movies: ",movie.getTitle());

                        UPCMovieList.add(movie);
                    }
                    upcomingRecyclerViewAdapter.notifyDataSetChanged();

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
        return UPCMovieList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_search) {
            showInputDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showInputDialog() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        getApplicationContext().startActivity(intent);

    }

    public void setNowInCinemaRV() {
        nowInCinemaRV = (RecyclerView) findViewById(R.id.recyclerViewNowInCinema);
        nowInCinemaRV.setHasFixedSize(true);
//        horizontal recyclerView
        nowInCinemaRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Prefs prefs = new Prefs(Main2Activity.this);
        String search = prefs.getSearch();

        NICMovieList = new ArrayList<>();


        NICMovieList = getNowInCinemaMovies("now_playing");

        nowInCRecyclerViewAdapter = new MovieRecyclerViewAdapter(this, NICMovieList);
        nowInCinemaRV.setAdapter(nowInCRecyclerViewAdapter);
        nowInCRecyclerViewAdapter.notifyDataSetChanged();

    }

    public void setTopRatedRV() {
        topRatedRV = (RecyclerView) findViewById(R.id.recyclerViewTopRated);
        topRatedRV.setHasFixedSize(true);
//        horizontal recyclerView
        topRatedRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Prefs prefs = new Prefs(Main2Activity.this);
        String search = prefs.getSearch();

        TRMovieList = new ArrayList<>();

        TRMovieList = getTopRatedMovies("top_rated");

        topRatedRecyclerViewAdapter = new MovieRecyclerViewAdapter(this, TRMovieList);
        topRatedRV.setAdapter(topRatedRecyclerViewAdapter);
        topRatedRecyclerViewAdapter.notifyDataSetChanged();

    }

    public void setPopularRV() {
        popularRV = (RecyclerView) findViewById(R.id.recyclerViewPopular);
        popularRV.setHasFixedSize(true);
//        horizontal recyclerView
        popularRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Prefs prefs = new Prefs(Main2Activity.this);
        String search = prefs.getSearch();

        POPMovieList = new ArrayList<>();

        POPMovieList = getPopularMovies("popular");

        popularRecyclerViewAdapter = new MovieRecyclerViewAdapter(this, POPMovieList);
        popularRV.setAdapter(popularRecyclerViewAdapter);
        popularRecyclerViewAdapter.notifyDataSetChanged();

    }

    public void setUpcomingRV() {
        upcomingRV = (RecyclerView) findViewById(R.id.recyclerViewUpcoming);
        upcomingRV.setHasFixedSize(true);
//        horizontal recyclerView
        upcomingRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Prefs prefs = new Prefs(Main2Activity.this);
        String search = prefs.getSearch();

        UPCMovieList = new ArrayList<>();

        UPCMovieList = getUpcomingMovies("upcoming");

        upcomingRecyclerViewAdapter = new MovieRecyclerViewAdapter(this, UPCMovieList);
        upcomingRV.setAdapter(upcomingRecyclerViewAdapter);
        upcomingRecyclerViewAdapter.notifyDataSetChanged();
    }

}


