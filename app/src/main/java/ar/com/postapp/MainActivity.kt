package ar.com.postapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.com.postapp.listing.view.fragment.ListingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val fragment = ListingFragment()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment, ListingFragment.TAG)
                .commit()
    }
}