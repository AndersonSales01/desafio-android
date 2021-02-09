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

    private val server = MockWebServer()

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
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }

        server.start(serverPort)
        var hasItems = false

            // TODO("validate if list displays items returned by server")
            try {
                UsersScreen {
                    checkingTitleDisplayed(context.getString(expectedTitle))
                    await(3000)
                    hasItems = checkIfItemListExists(R.id.recyclerView)
                    if (hasItems) {
                        checkIfUserInUserList(R.id.recyclerView, 1, "Marina Coelho")
                    }
                }
            } catch (e: NoMatchingViewException) {
                hasItems = false
            }

        assertTrue(hasItems)
        server.close()
    }

    companion object {
        private const val serverPort = 8080

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }

}