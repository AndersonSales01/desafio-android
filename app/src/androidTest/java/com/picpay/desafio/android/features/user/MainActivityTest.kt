package com.picpay.desafio.android.features.user

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.NoMatchingViewException
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.picpay.desafio.android.R
import com.picpay.desafio.android.features.user.presentation.ui.MainActivity
import junit.framework.Assert.assertTrue
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val expectedTitle = R.string.title
    private val messageError = R.string.error

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldDisplayTitle() {
        UsersScreen {
            checkingTitleDisplayed(context.getString(expectedTitle))
        }
    }

    @Test
    fun shouldDisplayToastError() {
        UsersScreen {
            checkingTitleDisplayed(context.getString(expectedTitle))
            checkDisplayToastError(activityRule.activity, messageError)
            await(3000)
        }
    }

    @Test
    fun shouldDisplayListItem() {
        UsersScreen {
            startMockWebServer()
            checkingTitleDisplayed(context.getString(expectedTitle))
            await(3000)
            checkIfItemListExists(R.id.recyclerView)
            stopMockWebServer()
        }
    }
}