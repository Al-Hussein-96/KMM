package com.alhussain.kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    GreetingView(Greeting().greet(),loginViewModel)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String, loginViewModel: LoginViewModel) {
    val state = loginViewModel.state.collectAsState()
    Box(contentAlignment = Alignment.Center) {
        Text(text = state.value)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
    }
}
