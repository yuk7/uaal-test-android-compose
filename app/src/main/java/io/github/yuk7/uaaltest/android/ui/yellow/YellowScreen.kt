package io.github.yuk7.uaaltest.android.ui.yellow

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.unity3d.player.UnityPlayer
import io.github.yuk7.uaaltest.android.R
import io.github.yuk7.uaaltest.android.ui.common.UnityComponent.Companion.UnityComposable
import io.github.yuk7.uaaltest.android.ui.theme.AppTheme

@Composable
fun YellowScreen(navHostController: NavHostController) {
    LaunchedEffect(Unit) {
        UnityPlayer.UnitySendMessage("Cube", "ChangeColor", "yellow")
    }
    YellowScreenContent(
        onClickBack = { navHostController.navigateUp() }
    )
}

@Composable
fun YellowScreenContent(
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Yellow") },
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
        UnityComposable(
            modifier = Modifier.aspectRatio(1f)
        )
    }
}

@Preview
@Composable
fun Preview() {
    AppTheme {
        YellowScreenContent(
            onClickBack = {}
        )
    }
}