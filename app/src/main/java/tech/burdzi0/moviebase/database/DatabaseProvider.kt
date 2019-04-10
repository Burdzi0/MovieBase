package tech.burdzi0.moviebase.database

import android.arch.persistence.room.Room
import android.content.Context

object DatabaseProvider {

    fun getDB(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movies_base"
        ).build()
    }
}