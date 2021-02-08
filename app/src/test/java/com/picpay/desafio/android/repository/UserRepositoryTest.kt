package com.picpay.desafio.android.repository

import android.content.Context
import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import com.picpay.desafio.android.features.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import com.picpay.desafio.android.util.MockResponseFileReader
import com.picpay.desafio.android.util.MockUtil
import com.picpay.desafio.android.util.RetrofitBuilder
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection


//@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [Build.VERSION_CODES.P])
class UserRepositoryTest {

    lateinit var repository: UserRepository

    lateinit var service: PicPayService

    private lateinit var mockWebServer: MockWebServer

    @MockK
    lateinit var context: Context

   // @MockK
    lateinit var userRemoteDataSource: UserRemoteDataSource
    @MockK
    lateinit var userLocalDataSource: UserLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        service = RetrofitBuilder.apiInterface
        userRemoteDataSource = UserRemoteDataSourceImpl(service)
//        val contextapp = ApplicationProvider.getApplicationContext<Context>()
//        userLocalDataSource = UserLocalDataSourceImpl(contextapp)
        repository = UserRepositoryImpl(userRemoteDataSource,userLocalDataSource)

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `get users remote`() = runBlocking {
        // Given
        coEvery { repository.getUsers() } returns MockUtil.userListMock()

        // When
        var list = repository.getUsers()

        print(list.size)
        // Then
        assertFalse(list.isEmpty())
    }


    @Test
    fun `fetch users and check response success returned`() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(
                MockResponseFileReader(
                    "success_response.json"
                ).content)
        mockWebServer.enqueue(response)

        val  actualResponse = service.getUsersResponse()

        assertEquals(
            response.toString().contains("200"),
            actualResponse.code().toString().contains("200")
        )

        mockWebServer.close()
    }
}