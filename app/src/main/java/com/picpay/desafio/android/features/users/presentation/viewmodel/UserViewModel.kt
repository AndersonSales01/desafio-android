package com.picpay.desafio.android.features.users.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.features.users.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.users.domain.repo.UserRepository
import com.picpay.desafio.android.features.users.domain.usercase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel () : ViewModel() {

    private val repository: UserRepository = UserRepositoryImpl()
    private val getUsersUseCase = GetUsersUseCase(repository)

    fun getUsers(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
              var list =  getUsersUseCase.invoke()
                Log.d("result", "list: $list" )
            }
        }
    }
}