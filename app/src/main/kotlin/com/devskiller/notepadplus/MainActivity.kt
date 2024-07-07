package com.devskiller.notepadplus

import android.content.Context
import android.content.Intent
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

        checkViewToShow()
    }

    override fun onResume() {
        super.onResume()
       checkViewToShow()
    }

    private fun checkViewToShow() {
        if (NoteLab.notes.isEmpty()) {
            showWelcomeFragment()
        } else {
            showNoteListFragment()
        }
    }

    private fun showWelcomeFragment() {
        val fragment: WelcomeFragment = WelcomeFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_fragment_container, fragment)
            .commit()
    }

    private fun showNoteListFragment() {
        val fragment: NoteListFragment = NoteListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.create_note -> {
            goToCreateNote()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun goToCreateNote() {
        val intent = Intent(this, ChangeNoteActivity::class.java)
        startActivity(intent)
    }
}
