package tech.burdzi0.moviebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MovieDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)

        println(intent?.extras?.getString("title"))
        setTextFromBundle(R.id.titleHeader, "title")
        setTextFromBundle(R.id.premiereText, "premiere")
        setTextFromBundle(R.id.directorText, "director")
        setTextFromBundle(R.id.descriptionText, "description")
    }

    private fun setTextFromBundle(id: Int, key:String) {
        findViewById<TextView>(id).text = intent?.extras?.getString(key)
    }
}
