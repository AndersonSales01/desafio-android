package com.picpay.desafio.android.features.user.repository


import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.mapper.UserMapper
import com.picpay.desafio.android.features.user.data.mapper.UserPOMapper
import com.picpay.desafio.android.features.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import com.picpay.desafio.android.util.*
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserRepositoryTest {

    private lateinit var repository: UserRepository
    private lateinit var userMapper: UserMapper
    private lateinit var userPOMapper: UserPOMapper
    private lateinit var userRemoteDataSource: UserRemoteDataSource
    private lateinit var userLocalDataSource: UserLocalDataSource

    @Before
    fun setUp() {
        userPOMapper = mockk()
        userMapper = mockk()
        userLocalDataSource = mockk()
        userRemoteDataSource = mockk()
        repository = UserRepositoryImpl(userRemoteDataSource, userLocalDataSource,userMapper,userPOMapper)
    }

    @Test
    fun `first scenario when return user list remote`() = runBlocking {
        withContext(Dispatchers.IO) {

            // Given
            coEvery { userRemoteDataSource.getUsersList() } returns Response.success(MockUtil.userResponseListMock())
            coEvery { userPOMapper.userListToUserPOList(MockUtil.userListMock()) } returns MockUtil.userPOListMock()
            coEvery { userLocalDataSource.insertUser(MockUtil.userPOListMock()) } returns listOf()
            coEvery { userMapper.responseListtoUserList(MockUtil.userResponseListMock()) } returns MockUtil.userListMock()

            // When
            var list = repository.getUsers()

            println(list)
            println(MockUtil.userListMock())
            // Then
            assertEquals(list,MockUtil.userListMock())
        }
    }

    @Test
    fun `second scenario when result list remote is empty then return user list local`() = runBlocking {
        withContext(Dispatchers.IO) {

            // Given
            coEvery { userRemoteDataSource.getUsersList() } returns Response.success(listOf())
            coEvery { userLocalDataSource.getUserListLocal() } returns MockUtil.userPOListMock()
            coEvery { userPOMapper.userPOListToUserList(MockUtil.userPOListMock()) } returns MockUtil.userListMock()

            // When
            val list = repository.getUsers()

            println(list)
            println(MockUtil.userListMock())

            // Then
            assertFalse(list.isEmpty())
        }
    }

    @Test
    fun `third scenario when result list remote and local  is empty then return user list is empty`() = runBlocking {
        withContext(Dispatchers.IO) {

            // Given
            coEvery { userRemoteDataSource.getUsersList() } returns Response.success(listOf())
            coEvery { userLocalDataSource.getUserListLocal() } returns listOf()

            // When
            val list = repository.getUsers()

            println(list)
            println(MockUtil.userListMock())

            // Then
            assertTrue(list.isEmpty())
        }
    }
}