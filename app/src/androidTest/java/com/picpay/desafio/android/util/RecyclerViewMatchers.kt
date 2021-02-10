package com.picpay.desafio.android.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

object RecyclerViewMatchers {

    fun atPosition(
        position: Int,
        itemMatcher: Matcher<View>
    ) = object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val viewHolder = item?.findViewHolderForAdapterPosition(position) ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }

    fun checkRecyclerViewItem(resId: Int, position: Int, text:String) {
        Espresso.onView(ViewMatchers.withId(resId)).check(
            ViewAssertions.matches(
                atPosition(
                    position,
                    ViewMatchers.hasDescendant(withText(text))
                )
            )
        )
    }

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
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(resId), ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(matcher))
        val result = COUNT
        COUNT = 0
        return result
    }
}