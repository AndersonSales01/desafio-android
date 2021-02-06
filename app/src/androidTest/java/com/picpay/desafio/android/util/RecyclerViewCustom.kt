package com.picpay.desafio.android.util

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher


object RecyclerViewCustom {

    fun getCountFromRecyclerView(resId: Int): Int {
        var COUNT = 0
        val matcher = object : TypeSafeMatcher<View?>() {
            override fun describeTo(description: Description?) {
                description?.appendText("with list size: $COUNT")
            }
            override fun matchesSafely(item: View?): Boolean {
                COUNT = (item as RecyclerView).adapter!!.itemCount
                return true
            }
        }
        onView(Matchers.allOf(ViewMatchers.withId(resId), isDisplayed()))
            .check(ViewAssertions.matches(matcher))
        val result = COUNT
        COUNT = 0
        return result
    }

}