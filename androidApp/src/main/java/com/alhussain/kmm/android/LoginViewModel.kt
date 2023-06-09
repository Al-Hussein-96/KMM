package com.alhussain.kmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alhussain.kmm.data.LoginRepository
import com.alhussain.kmm.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository): ViewModel() {
    private val _state = MutableStateFlow("Loading")
    val state: StateFlow<String> = _state


    init {

        viewModelScope.launch {
            val result = repository.login("Mohammad","12345")

            if(result is Result.Success)
                _state.value = "Done"
            else
                _state.value = "Error"

        }
    }

}