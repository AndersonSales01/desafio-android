package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.domain.usercase.GetUserUseCase
import com.picpay.desafio.android.features.user.presentation.viewmodel.UserViewModel
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: UserViewModel

    @Mock
    lateinit var getUsersUseCase: GetUserUseCase

    @Mock
    private lateinit var livedata: Observer<Boolean>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<User>())
                .`when`(getUsersUseCase)
                .invoke()

            viewModel = UserViewModel(getUsersUseCase)
            viewModel.sucessState.observeForever(livedata)

            verify(getUsersUseCase).invoke()
            verify(livedata).onChanged(true)
            viewModel.sucessState.removeObserver(livedata)
        }
    }
}