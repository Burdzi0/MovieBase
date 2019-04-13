package tech.burdzi0.moviebase.database

import android.arch.persistence.room.Room
import android.content.Context

class DatabaseProvider {

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE ?: synchronized(this) {
                INSTANCE = INSTANCE ?: buildDatabase(context)
            }
            return this.INSTANCE!!
        }

        private fun buildDatabase(context: Context):AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "Sample.db")
                .build()
        }

    }
}