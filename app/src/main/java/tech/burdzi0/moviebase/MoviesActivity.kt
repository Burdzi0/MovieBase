package tech.burdzi0.moviebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import tech.burdzi0.moviebase.database.DatabaseProvider.Companion.getDatabase
import tech.burdzi0.moviebase.SingleThreadExecutor.execute
import java.util.concurrent.Callable

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val listView = findViewById<ListView>(R.id.listView)

        val titles = getAllTitles()

        val adapter = ArrayAdapter<String>(this,
            R.layout.row,
            titles
        )

        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            val intent = Intent(this, MovieDescriptionActivity::class.java)
            val filledBundle = execute(fillWithData(Bundle(), id)).get()
            intent.putExtras(filledBundle)
            startActivity(intent, filledBundle)
        }
    }

    private fun fillWithData(bundle: Bundle, id:Long): Callable<Bundle> {
        return Callable {
            val movie = getDatabase(applicationContext).movieDao().getMovieById(id)
            bundle.apply {
                putString("title", movie.title)
                putString("premiere", movie.date)
                putString("description", movie.description)
                putString("director", movie.director.firstName + " " + movie.director.lastName)
            }
        }
    }

    private fun getAllTitles(): Array<String> {
        return execute(Callable {
            getDatabase(applicationContext).movieDao().getAllMovies()
        }).get().map{
            it.title
        }.toTypedArray()
    }
}
