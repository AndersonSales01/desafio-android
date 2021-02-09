package com.picpay.desafio.android.features.user.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.domain.usercase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserViewModel @Inject constructor(private var getUsersUseCase : GetUserUseCase) : ViewModel() {

    private val _usersList = MutableLiveData<List<User>>()
    var usersList: LiveData<List<User>> = _usersList

    private val _error = MutableLiveData<Int>()
    var error: LiveData<Int> = _error

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    fun getUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _loading.postValue(true)

                var list = getUsersUseCase.invoke()

                if (list.isNotEmpty()) {

                    _usersList.postValue(list)
                } else {
                    _error.postValue(R.string.error)
                }
                _loading.postValue(false)
            }
        }
    }
}