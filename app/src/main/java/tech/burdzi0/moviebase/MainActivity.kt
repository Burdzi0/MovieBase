package tech.burdzi0.moviebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import tech.burdzi0.moviebase.SingleThreadExecutor.execute
import tech.burdzi0.moviebase.database.DatabaseProvider.Companion.getDatabase
import tech.burdzi0.moviebase.database.daos.DirectorDao
import tech.burdzi0.moviebase.database.daos.MovieDao
import tech.burdzi0.moviebase.database.entities.Director
import tech.burdzi0.moviebase.database.entities.Movie

class MainActivity : AppCompatActivity() {

    private var loaded = false

    private val directors = arrayListOf(
        Director(1, "Steven", "Spielberg"),
        Director(2, "James", "Cameron"),
        Director(3, "Martin", "Scorsese"),
        Director(4, "Quentin", "Tarantino"),
        Director(5, "Christopher", "Nolan"),
        Director(6, "David O.", "Russel"),
        Director(7, "Christopher", "Landon"),
        Director(8, "Cameron", "Crowe"),
        Director(9, "James", "Wan"),
        Director(10, "David", "Fincher")
    )

    private val movies = arrayListOf(
        Movie(1, "Schindler's List", "1998", " In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis. ", directors[0]),
        Movie(2, "Avatar", "2009", "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home. ", directors[1]),
        Movie(3, "Taxi Driver", "1976", " A mentally unstable veteran works as a nighttime taxi driver in New York City, where the perceived decadence and sleaze fuels his urge for violent action by attempting to liberate a presidential campaign worker and an underage prostitute. ", directors[2]),
        Movie(4, "Reservoir Dogs", "1992", "When a simple jewelry heist goes horribly wrong, the surviving criminals begin to suspect that one of them is a police informant. ", directors[3]),
        Movie(5, "Interstellar", "2014", " A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival. ", directors[4]),
        Movie(6, "American Hustle", "2013", " A con man, Irving Rosenfeld, along with his seductive partner Sydney Prosser, is forced to work for a wild F.B.I. Agent, Richie DiMaso, who pushes them into a world of Jersey powerbrokers and the Mafia. ", directors[5]),
        Movie(7, "Paranormal Activity: The Marked Ones", "2014", " When a young man becomes the target of a malevolent entity, he must uncover its true intentions before it takes complete control of him. ", directors[6]),
        Movie(8, "Almost Famous", "2000", " A high-school boy is given the chance to write a story for Rolling Stone Magazine about an up-and-coming rock band as he accompanies them on their concert tour. ", directors[7]),
        Movie(9, "Saw", "2004", " Two strangers, who awaken in a room with no recollection of how they got there, soon discover they're pawns in a deadly game perpetrated by a notorious serial killer. ", directors[8]),
        Movie(10, "Se7en", "1995", " Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives. ", directors[9])
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeButtonOnClickBehaviour()

        val loadData = findViewById<Button>(R.id.button2)
        loadData.setOnClickListener {
            if (!loaded) {
                insertDirectors()
                insertMovies()
                loaded = true
                Toast.makeText(this, "Data successfully loaded!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initializeButtonOnClickBehaviour() {
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun insertMovies() {
        val movieDao = getDatabase(applicationContext).movieDao()
        execute(loadMovies(movieDao))
    }

    private fun insertDirectors() {
        val directorDao = getDatabase(applicationContext).directorDao()
        execute(loadDirectors(directorDao))
    }

    private fun loadDirectors(directorDao: DirectorDao): Runnable {
        return Runnable {
            directors.forEach {
                directorDao.insert(it)
            }
        }
    }

    private fun loadMovies(movieDao: MovieDao): Runnable {
        return Runnable {
            movies.forEach {
                movieDao.insert(it)
            }
        }
    }
}
