package tech.burdzi0.moviebase.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import tech.burdzi0.moviebase.database.entities.Director

@Dao
interface DirectorDao {

    @Query("SELECT * FROM Directors")
    fun getAllDirectors():List<Director>

    @Query("SELECT * FROM Directors WHERE idD = :id")
    fun getDirectorById(id: Long): Director

    @Query("SELECT * FROM Directors WHERE last_name LIKE :lastName")
    fun getDirectorsByLastName(lastName: String):List<Director>

    @Insert
    fun insert(vararg director: Director)

    @Delete
    fun delete(director: Director)
}