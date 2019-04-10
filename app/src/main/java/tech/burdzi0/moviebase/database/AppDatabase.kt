package tech.burdzi0.moviebase.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import tech.burdzi0.moviebase.database.daos.DirectorDao
import tech.burdzi0.moviebase.database.daos.MovieDao
import tech.burdzi0.moviebase.database.entities.Director
import tech.burdzi0.moviebase.database.entities.Movie

@Database(entities = [Movie::class, Director::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun directorDao(): DirectorDao
}
