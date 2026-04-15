package com.udacity.pawhaven

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.pawhaven.data.Repository
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserRoleVisibilityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        // Clear user before each test to ensure we start from ProfileActivity logic
        Repository.user = null
    }

    @Test
    fun volunteerUser_canSeeAddButton_butNotAdoptButton() {
        // Navigate to Profile
        onView(withId(R.id.get_started_button)).perform(click())

        // Fill in details for a valid volunteer from Repository
        val volunteer = Repository.validVolunteers.first()
        onView(withId(R.id.first_name_edit_text)).perform(typeText(volunteer.firstName), closeSoftKeyboard())
        onView(withId(R.id.last_name_edit_text)).perform(typeText(volunteer.lastName), closeSoftKeyboard())
        onView(withId(R.id.age_edit_text)).perform(typeText(volunteer.age.toString()), closeSoftKeyboard())
        onView(withId(R.id.radio_volunteer)).perform(click())
        onView(withId(R.id.save_profile_button)).perform(click())

        // Now in PetListActivity
        // Check if Add Pet FAB is visible
        onView(withId(R.id.addPetFab)).check(matches(isDisplayed()))

        // Navigate to Pet Detail
        onView(withId(R.id.petRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PetListAdapter.PetViewHolder>(0, click()))

        // Check if Adopt Button is NOT visible
        onView(withId(R.id.adoptButton)).check(matches(not(isDisplayed())))
    }

    @Test
    fun interestedParentUser_canSeeAdoptButton_butNotAddButton() {
        // Navigate to Profile
        onView(withId(R.id.get_started_button)).perform(click())

        // Fill in details for an interested parent
        onView(withId(R.id.first_name_edit_text)).perform(typeText("Jane"), closeSoftKeyboard())
        onView(withId(R.id.last_name_edit_text)).perform(typeText("Doe"), closeSoftKeyboard())
        onView(withId(R.id.age_edit_text)).perform(typeText("25"), closeSoftKeyboard())
        onView(withId(R.id.radio_parent)).perform(click())
        onView(withId(R.id.save_profile_button)).perform(click())

        // Now in PetListActivity
        // Check if Add Pet FAB is NOT visible
        onView(withId(R.id.addPetFab)).check(matches(not(isDisplayed())))

        // Navigate to Pet Detail
        onView(withId(R.id.petRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PetListAdapter.PetViewHolder>(0, click()))

        // Check if Adopt Button is visible
        onView(withId(R.id.adoptButton)).check(matches(isDisplayed()))
    }
}
