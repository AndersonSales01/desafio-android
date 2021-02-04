package com.picpay.desafio.android.features.users.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.features.users.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.users.domain.model.User
import com.picpay.desafio.android.features.users.domain.repo.UserRepository
import com.picpay.desafio.android.features.users.domain.usercase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserViewModel @Inject constructor(private var getUsersUseCase : GetUserUseCase) : ViewModel() {

   // private val repository: UserRepository = UserRepositoryImpl()
   // private val getUsersUseCase = GetUserUseCase(repository)

    private val _usersList = MutableLiveData<List<User>>()
    var usersList: LiveData<List<User>> = _usersList

    private val _sucessState = MutableLiveData<Boolean>()
    var sucessState: LiveData<Boolean> = _sucessState

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading


    fun getUsers() {
        _loading.value = true
        Log.d("result", "old list: ${usersList.value}")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var list = getUsersUseCase.invoke()
                Log.d("result", "list: $list")
                if (list.isNotEmpty()) {
                    _sucessState.postValue(true)
                    _usersList.postValue(list)
                } else {
                    _sucessState.postValue(false)
                }
                _loading.postValue(false)
            }
        }
    }
}