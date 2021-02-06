package com.picpay.desafio.android.usecases

import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import com.picpay.desafio.android.features.user.domain.usercase.GetUserUseCase
import com.picpay.desafio.android.mockks.MockUtil
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    @MockK
    lateinit var repository: UserRepository

    lateinit var getUsersUseCase: GetUserUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getUsersUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `test get users Sucess`() = runBlocking {
        // Given
        coEvery { repository.getUsers() } returns MockUtil.userListMock()

        // When
        var list = getUsersUseCase.invoke()

        print(list.size)
        // Then
        assertFalse(list.isEmpty())
    }

    @Test
    fun `test get users fail because list empty`() = runBlocking {
        // Given
        coEvery { repository.getUsers() } returns listOf()

        // When
        var list = getUsersUseCase.invoke()

        print(list.size)
        // Then
        assertTrue(list.isEmpty())
    }
}