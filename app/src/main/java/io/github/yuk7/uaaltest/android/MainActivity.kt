package io.github.yuk7.uaaltest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.yuk7.uaaltest.android.ui.InitializeRoutes
import io.github.yuk7.uaaltest.android.ui.blue.BlueScreen
import io.github.yuk7.uaaltest.android.ui.theme.AppTheme
import io.github.yuk7.uaaltest.android.ui.top.TopScreen
import io.github.yuk7.uaaltest.android.ui.yellow.YellowScreen

class MainActivity : ComponentActivity() {
    companion object {
        @Keep
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    InitializeRoutes(navController)
                }
            }
        }
    }

    fun onUnityObjectClick(data: String) {

    }
}