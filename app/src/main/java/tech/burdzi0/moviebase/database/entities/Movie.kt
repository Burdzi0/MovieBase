package tech.burdzi0.moviebase.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "Movies")
data class Movie(
    @PrimaryKey(autoGenerate = true) val idM:Long,
    val title: String,
    @ColumnInfo(name = "premiere_date") var date: Date?,
    val description: String,
    @Embedded val director: Director
)