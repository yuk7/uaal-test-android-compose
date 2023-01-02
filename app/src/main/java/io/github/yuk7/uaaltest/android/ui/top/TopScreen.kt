package io.github.yuk7.uaaltest.android.ui.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unity3d.player.UnityPlayer
import io.github.yuk7.uaaltest.android.ui.common.UnityComponent.Companion.UnityComposable
import io.github.yuk7.uaaltest.android.ui.navigateToBlue
import io.github.yuk7.uaaltest.android.ui.navigateToYellow
import io.github.yuk7.uaaltest.android.ui.theme.AppTheme

@Composable
fun TopScreen(navHostController: NavHostController) {
    LaunchedEffect(Unit) {
        UnityPlayer.UnitySendMessage("Cube", "ChangeColor", "white")
    }

    TopScreenContent(
        onClickBlue = { navHostController.navigateToBlue() },
        onClickYellow = { navHostController.navigateToYellow() }
    )
}

@Composable
fun TopScreenContent(
    onClickBlue: () -> Unit,
    onClickYellow: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Top") }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            UnityComposable()
            Button(
                modifier = Modifier
                    .waterfallPadding()
                    .padding(10.dp)
                    .align(Alignment.BottomStart),
                onClick = { onClickBlue() }
            ) {
                Text(text = "Blue")
            }
            Button(
                modifier = Modifier
                    .waterfallPadding()
                    .padding(10.dp)
                    .align(Alignment.BottomEnd),
                onClick = { onClickYellow() }
            ) {
                Text(text = "Yellow")
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    AppTheme {
        TopScreenContent(
            onClickBlue = {},
            onClickYellow = {}
        )
    }
}