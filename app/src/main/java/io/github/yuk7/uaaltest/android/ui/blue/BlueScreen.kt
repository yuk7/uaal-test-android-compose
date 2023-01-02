package io.github.yuk7.uaaltest.android.ui.blue

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.unity3d.player.UnityPlayer
import io.github.yuk7.uaaltest.android.R
import io.github.yuk7.uaaltest.android.ui.common.UnityComponent.Companion.UnityComposable
import io.github.yuk7.uaaltest.android.ui.theme.AppTheme

@Composable
fun BlueScreen(navHostController: NavHostController) {
    LaunchedEffect(Unit) {
        UnityPlayer.UnitySendMessage("Cube", "ChangeColor", "blue")
    }
    BlueScreenContent(
        onClickBack = { navHostController.navigateUp() }
    )
}

@Composable
fun BlueScreenContent(
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Blue") },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_arrow_back_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        UnityComposable()
    }
}

@Preview
@Composable
fun Preview() {
    AppTheme {
        BlueScreenContent(
            onClickBack = {}
        )
    }
}