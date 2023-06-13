package com.alhussain.kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alhussain.kmm.Greeting
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginView(loginViewModel)
                }
            }
        }
    }
}

@Composable
fun LoginView(loginViewModel: LoginViewModel) {
    val state = loginViewModel.state.collectAsState()

    var username by remember {
        mutableStateOf("superAdmin")
    }
    var password by remember {
        mutableStateOf("SuperAdmin@2022")
    }

    if (state.value) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = state.value.toString())
        }
    } else {
        Scaffold {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = username, onValueChange = { newValue ->
                    username = newValue
                }, placeholder = {
                    Text(text = "Type your username")
                })
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = password,
                    onValueChange = { newValue ->
                        password = newValue
                    },
                    placeholder = {
                        Text(text = "Type your password")
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(modifier = Modifier.fillMaxWidth(.7f), onClick = {
                    loginViewModel.login(username, password)
                }, content = {
                    Text(text = "Login")
                })
            }
        }
    }


}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
    }
}
