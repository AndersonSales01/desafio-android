package com.picpay.desafio.android.features.user.usecases

import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import com.picpay.desafio.android.features.user.domain.usercase.GetUserUseCase
import com.picpay.desafio.android.util.MockUtil
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private lateinit var repository: UserRepository

    private lateinit var getUsersUseCase: GetUserUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getUsersUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `Use case test when return user list is not empty`() = runBlocking {
        // Given
        coEvery { repository.getUsers() } returns MockUtil.userListMock()

        // When
        var list = getUsersUseCase.invoke()

        print(list.size)
        // Then
        assertFalse(list.isEmpty())
    }

    @Test
    fun `Use case test with when return user list is empty`() = runBlocking {
        // Given
        coEvery { repository.getUsers() } returns listOf()

        // When
        var list = getUsersUseCase.invoke()

        print(list.size)
        // Then
        assertTrue(list.isEmpty())
    }

}