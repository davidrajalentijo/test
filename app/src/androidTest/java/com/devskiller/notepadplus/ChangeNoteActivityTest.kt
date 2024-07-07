package com.devskiller.notepadplus

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.devskiller.notepadplus.R.id
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class ChangeNoteActivityTest {

    private lateinit var mNote: Note

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        NoteLab.clear()

        mNote = Note()
        NoteLab.addNote(mNote)


        val intent = Intent(context, ChangeNoteActivity::class.java)
            .putExtra(ChangeNoteActivity.EXTRA_NOTE_ID, mNote.id.toString())
            .putExtra(ChangeNoteActivity.EXTRA_EDIT_NOTE_ACTION, true)

        launchActivity<ChangeNoteActivity>(intent)

    }


    @Test
    fun shouldSaveFromEditTitleToNoteLab() {

        onView(withId(id.et_title))
            .perform(clearText())

        onView(withId(id.et_title))
            .perform(typeText("Title1234"))

        onView(withId(id.b_save))
            .perform(click())

        val mCurrentNote = NoteLab.getNote(mNote.id)!!

        assertThat(mCurrentNote.title, equalTo("Title1234"))
    }

    @Test
    fun shouldSaveFromEditDescToNoteLab() {

        onView(withId(id.et_description))
            .perform(clearText())

        onView(withId(id.et_description))
            .perform(typeText("Desc 1234"))

        val currentNote: Note = NoteLab.getNote(mNote.id)!!

        onView(withId(id.b_save))
            .perform(click())

        assertThat(currentNote.description, equalTo("Desc 1234"))
    }
}
