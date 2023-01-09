package io.github.yuk7.uaaltest.android.ui.yellow

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unity3d.player.UnityPlayer
import io.github.yuk7.uaaltest.android.MainActivityViewModel
import io.github.yuk7.uaaltest.android.R
import io.github.yuk7.uaaltest.android.ui.common.UnityComponent.Companion.UnityComposable
import io.github.yuk7.uaaltest.android.ui.theme.AppTheme

@Composable
fun YellowScreen(navHostController: NavHostController, mainActivityViewModel: MainActivityViewModel) {
    LaunchedEffect(Unit) {
        UnityPlayer.UnitySendMessage("Cube", "ChangeColor", "yellow")
    }

    var additionalHeight = remember { mutableStateOf(0.dp) }

    LaunchedEffect(mainActivityViewModel.clickCubeFlow) {
        mainActivityViewModel.clickCubeFlow.collect {
            additionalHeight.value += 10.dp
        }
    }

    YellowScreenContent(
        onClickBack = { navHostController.navigateUp() },
        additionalHeight = additionalHeight.value
    )
}

@Composable
fun YellowScreenContent(
    onClickBack: () -> Unit,
    additionalHeight: Dp
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
        BoxWithConstraints {
            UnityComposable(
                modifier = Modifier.height(maxWidth + additionalHeight)
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    AppTheme {
        YellowScreenContent(
            onClickBack = {},
            additionalHeight = 0.dp
        )
    }
}