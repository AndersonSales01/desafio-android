package com.picpay.desafio.android.ui

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.util.DatabaseMock
import com.picpay.desafio.android.util.TestCoroutineRule
import com.picpay.desafio.android.features.MyApplication
import com.picpay.desafio.android.features.user.data.database.AppDataBase
import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import com.picpay.desafio.android.features.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import com.picpay.desafio.android.features.user.domain.usercase.GetUserUseCase
import com.picpay.desafio.android.features.user.presentation.viewmodel.UserViewModel
import com.picpay.desafio.android.util.MockUtil
import com.picpay.desafio.android.util.RetrofitBuilder
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: UserViewModel

    @MockK
    private lateinit var context: Context
    @MockK
    private lateinit var myApplication: MyApplication

    //@MockK
    lateinit var repository: UserRepository

    @MockK
    lateinit var getUsersUseCase: GetUserUseCase

    @MockK
    private lateinit var livedata: Observer<Boolean>


    lateinit var userRemoteDataSource: UserRemoteDataSource
   // @MockK
    lateinit var userLocalDataSource: UserLocalDataSource

    lateinit var service: PicPayService

    private lateinit var database: AppDataBase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        service = RetrofitBuilder.apiInterface
        userRemoteDataSource = UserRemoteDataSourceImpl(service)
        val contextapp = ApplicationProvider.getApplicationContext<Context>()
        database = DatabaseMock.dabatase(contextapp)
        userLocalDataSource = UserLocalDataSourceImpl(database)
       // val contextapp = ApplicationProvider.getApplicationContext<Context>()
       // myApplication = mockk<MyApplication>()
      //  userLocalDataSource = UserLocalDataSourceImpl(myApplication.applicationContext)
       repository = UserRepositoryImpl(userRemoteDataSource,userLocalDataSource)
       // livedata = mockk<Observer<Boolean>>()
        getUsersUseCase = GetUserUseCase(repository)
        viewModel = UserViewModel(getUsersUseCase).apply {
            sucessState.observeForever(livedata)
        }
       // val contextapp = ApplicationProvider.getApplicationContext<Context>()
       // database = DatabaseMock(context)

    }


    @Test
    fun `Verify status of get users`() {
        // val observer = mock<Observer<Boolean>>()
        runBlocking {
            withContext(Dispatchers.IO) {

                whenever(getUsersUseCase.invoke()).thenReturn(MockUtil.userListMock())

                viewModel.getUsers()
                // delay(5000)
//                    print(database.userDao().getUsers())
//                viewModel.usersList.observeForever {
//                    print(it)
//                }
                //verify(livedata, times(1)).onChanged(true)
                //assertEquals(true, )
                verify(livedata).onChanged(viewModel.sucessState.value)
            }

        }
    }
}