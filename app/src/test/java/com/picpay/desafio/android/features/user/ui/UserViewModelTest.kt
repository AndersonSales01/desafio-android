package com.picpay.desafio.android.features.user.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.picpay.desafio.android.util.TestCoroutineRule
import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.domain.usercase.GetUserUseCase
import com.picpay.desafio.android.features.user.presentation.viewmodel.UserViewModel
import com.picpay.desafio.android.util.MockUtil
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: UserViewModel
    private lateinit var getUsersUseCase: GetUserUseCase

    private lateinit var errorStateObserver: Observer<Int>
    private lateinit var userListObserver: Observer<List<User>>

    @Before
    fun setUp() {
        errorStateObserver = mock()
        userListObserver = mock()
        getUsersUseCase = mockk()
        viewModel = UserViewModel(getUsersUseCase).apply {
            error.observeForever(errorStateObserver)
            usersList.observeForever(userListObserver)
        }
    }

    @Test
    fun `Check user list with scenario sucess`() {
        testCoroutineRule.runBlockingTest {

            // Given
            coEvery { getUsersUseCase.invoke() } returns MockUtil.userListMock()

            // When
            viewModel.getUsers()

            // Then
            verify(userListObserver).onChanged(viewModel.usersList.value)
            verify(errorStateObserver, never()).onChanged(viewModel.error.value)
        }
    }

    @Test
    fun `Check user list with scenario Error`() =
        testCoroutineRule.runBlockingTest {

            // Given
            coEvery { getUsersUseCase.invoke() } returns listOf()

            // When
            viewModel.getUsers()

            // Then
            verify(errorStateObserver).onChanged(viewModel.error.value)
            verify(userListObserver, never()).onChanged(viewModel.usersList.value)
        }
}