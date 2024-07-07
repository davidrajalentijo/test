package com.devskiller.notepadplus

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.devskiller.notepadplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        //showWelcomeFragment()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        showWelcomeFragment()
        return super.onCreateView(name, context, attrs)
    }
    private fun showWelcomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WelcomeFragment.newInstance())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.create_note -> {
            // START YOUR CHANGE
            // END YOUR CHANGE
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
