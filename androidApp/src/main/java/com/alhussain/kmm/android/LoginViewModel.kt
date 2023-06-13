package com.alhussain.kmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alhussain.kmm.data.LoginRepository
import com.alhussain.kmm.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {


    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean> = _state

    fun login(username: String, password: String) = viewModelScope.launch {
        val result = repository.login(username, password)

        _state.value = result is Result.Success
    }
}