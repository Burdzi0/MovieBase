package tech.burdzi0.moviebase

import android.os.AsyncTask
import tech.burdzi0.moviebase.database.daos.MovieDao
import tech.burdzi0.moviebase.database.entities.Movie

class Task : AsyncTask<Pair<MovieDao, Movie>, Void, Movie>() {

    override fun onPostExecute(result: Movie?) {
        super.onPostExecute(result)
        println(result)
    }

    override fun doInBackground(vararg params: Pair<MovieDao, Movie>?): Movie? {
        val movieDao = params.first()?.first
        val movie = params.first()?.second

        if (movie != null) {
            movieDao?.insert(movie)
        }

        return movie
    }



}