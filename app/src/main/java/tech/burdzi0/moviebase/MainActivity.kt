package tech.burdzi0.moviebase

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import tech.burdzi0.moviebase.database.AppDatabase
import tech.burdzi0.moviebase.database.entities.Director
import tech.burdzi0.moviebase.database.entities.Movie
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database =  Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "movies_base"
        ).build()

        val movieDao = database.movieDao()
        val directorDao = database.directorDao()

        val executorService = Executors.newSingleThreadExecutor()

        Log.d("INFO", "Starting...")
        executorService.execute {
            directorDao.insert(
                Director(0,"Łukasz", "Burdyna")
            )
        }
        Log.d("INFO", "Second...")
        executorService.execute {
            movieDao.insert(
                Movie(0, "Title", Date(),
                    directorDao.getAllDirectors()[0])
            )
        }
        Log.d("INFO", "Last...")
        val future = executorService.submit(Callable<List<Movie>> {
            movieDao.getAllMovies()
        })
        Log.d("INFO", "Get...")
        future.get().forEach{println(it)}
    }
}
