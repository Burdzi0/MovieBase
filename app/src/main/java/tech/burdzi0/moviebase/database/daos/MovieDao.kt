package tech.burdzi0.moviebase.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import tech.burdzi0.moviebase.database.entities.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movies")
    fun getAllMovies():List<Movie>

    @Query("SELECT * FROM Movies WHERE idM = :id")
    fun getMovieById(id: Long):Movie

    @Query("SELECT * FROM Movies WHERE title LIKE :title")
    fun getMoviesByTitle(title: String):List<Movie>

    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)
}