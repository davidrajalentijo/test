package com.devskiller.notepadplus

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        NoteLab.clear()
    }

    @Test
    fun shouldHaveWelcomeFragment() {
        launchActivity<MainActivity>()

        onView(withId(R.id.tv_welcome))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveWelcomeMessageInWelcomeFragment() {

        launchActivity<MainActivity>()

        onView(withId(R.id.tv_welcome))
            .check(matches(withText(R.string.welcome_message)))
    }

    @Test
    fun shouldStartChangeNoteActivityOnClickAddButton() {
        launchActivity<MainActivity>()

        Intents.init()

        onView(withId(R.id.create_note)).perform(click())

        Intents.intended(IntentMatchers.hasComponent(ChangeNoteActivity::class.java.name))

        Intents.release()
    }
}