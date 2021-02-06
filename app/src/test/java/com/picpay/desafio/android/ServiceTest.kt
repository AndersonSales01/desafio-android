package com.picpay.desafio.android

import androidx.lifecycle.Observer
import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import com.picpay.desafio.android.features.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import com.picpay.desafio.android.features.user.domain.usercase.GetUserUseCase
import com.picpay.desafio.android.features.user.presentation.viewmodel.UserViewModel
import com.picpay.desafio.android.mockks.MockUtil
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import java.net.HttpURLConnection

class ServiceTest {

    private lateinit var viewModel: UserViewModel
    @MockK
    lateinit var getUsersUseCase: GetUserUseCase
//    @MockK
   lateinit var service: PicPayService
    @MockK
    lateinit var remoteDataSource: UserRemoteDataSource

    @MockK
    lateinit var userLocalDataSource: UserLocalDataSource

    lateinit var userRepository: UserRepository

    private lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var livedata: Observer<Boolean>

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        service = RetrofitBuilder.apiInterface
        remoteDataSource = UserRemoteDataSourceImpl(service)

        userRepository = UserRepositoryImpl(remoteDataSource,userLocalDataSource)

        viewModel = UserViewModel(getUsersUseCase)

        Dispatchers.setMain(dispatcher)

       // viewModel.sucessState.observeForever(livedata)


        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

//    @Test
//    fun `test get users Sucess`() = runBlocking {
//        val call = mock<Response<List<UserResponse>>>()
//        call.body(MockUtil.userListResponseMock())
//        // Given
//        coEvery { service.getUsersResponse() } returns MockUtil.userListResponseMock()
//
//        // When
//        var list = getUsersUseCase.invoke()
//
//        print(list.size)
//        // Then
//
//    }

//    @Test
//    fun `test get users Sucess`() = runBlocking {
//        val response = MockResponse()
//            .setResponseCode(HttpURLConnection.HTTP_OK)
//            .setBody(MockResponseFileReader("success_response.json").content)
//        mockWebServer.enqueue(response)
//
//        val  actualResponse = service.getUsersResponse()
//
//
//        print( response.getBody())
//    }
//
    @Test
    fun `test get users Sucess`() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)

        val  actualResponse = service.getUsersResponse()


        print( actualResponse.body()?.size)

    assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

        @Test
    fun `repository`() = runBlocking {
            // Given
            coEvery { remoteDataSource.getUsersList() } returns MockUtil.userListMock()

            // When
            var list = remoteDataSource.getUsersList()

            print(list.size)
            // Then
          //  Assert.assertFalse(list.isEmpty())
        }

    @Test
    fun `viewMol`() = runBlocking {
        // Given
        coEvery { getUsersUseCase.invoke()} returns MockUtil.userListMock()

        // When
        var list = viewModel.getUsers()

        print(viewModel.sucessState.value)
        // Then
        //  Assert.assertFalse(list.isEmpty())
    }
}