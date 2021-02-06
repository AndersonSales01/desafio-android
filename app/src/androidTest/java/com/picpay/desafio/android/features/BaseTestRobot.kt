package com.picpay.desafio.android.features

//import androidx.test.espresso.contrib.RecyclerViewActions

import android.app.Activity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.picpay.desafio.android.util.RecyclerViewMatchers
import org.hamcrest.Matchers.*


open class BaseTestRobot {

    fun fillEditText(resId: Int, text: String): ViewInteraction =
        onView(withId(resId)).perform(ViewActions.typeText(text), ViewActions.closeSoftKeyboard())

    fun clickButton(resId: Int): ViewInteraction =
        onView((withId(resId))).perform(ViewActions.click())

    fun textView(resId: Int): ViewInteraction = onView(withId(resId))

    fun checkTextDisplayed(text: String): ViewInteraction =
        onView(withText(text)).check(matches(isDisplayed()))

    fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
        .check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String): ViewInteraction = matchText(textView(resId), text)

    fun clickListItem(listRes: Int, position: Int) {
        onData(anything())
            .inAdapterView(allOf(withId(listRes)))
            .atPosition(position).perform(ViewActions.click())
    }
//    fun clickitemRecycler(listRes: Int, position: Int){
//        onView(withId(listRes)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<EventViewHolder>(position, ViewActions.click()))
//    }

    fun sleep(time: Long) = apply {
        Thread.sleep(time)
    }

    fun checkViewDisplayed(resId: Int) {
        onView(withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkButtonDisable(resId: Int) {
        onView(withId(resId)).check(matches(not(isEnabled())));
    }

    //    fun checkTextInputError(resId: Int,string: Int) {
//        onView(withId(resId)).check(matches(checkTextInputError(withText(messageResId))))
//    }
    fun checkTextInList(resId: Int, position: Int, text: String) {
        onView(withId(resId)).check(matches(RecyclerViewMatchers.atPosition(position, hasDescendant(withText(text)))))
    }

    fun checkTextToast(context: Activity, text: Int) {
        onView(withText(text)).inRoot(withDecorView(not(context.window.decorView))).check(matches(isDisplayed()));
    }

}