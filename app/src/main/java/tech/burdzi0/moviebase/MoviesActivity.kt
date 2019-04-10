package tech.burdzi0.moviebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import tech.burdzi0.moviebase.database.DatabaseProvider.getDB
import tech.burdzi0.moviebase.SingleThreadExecutor.execute
import java.util.concurrent.Callable

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val listView = findViewById<ListView>(R.id.listView)

        val res = getAllTitles() + "ABC"
        println(res[0])

        val adapter = ArrayAdapter<String>(this,
            R.layout.row,
            res
        )

        listView.adapter = adapter
    }

    private fun getAllTitles(): Array<String> {
        return execute(Callable {
            getDB(this)
                .movieDao()
                .getAllMovies()
                .map {
                    it.title
                }.toTypedArray()
        }).get()
    }
}
