package io.github.yuk7.uaaltest.android.ui.top

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.toColorInt
import com.unity3d.player.UnityPlayer
import io.github.yuk7.uaaltest.android.MainActivityViewModel
import io.github.yuk7.uaaltest.android.R
import io.github.yuk7.uaaltest.android.ui.common.UnityComponent.Companion.UnityComposable
import io.github.yuk7.uaaltest.android.ui.navigateToBlue
import io.github.yuk7.uaaltest.android.ui.navigateToYellow
import io.github.yuk7.uaaltest.android.ui.theme.AppTheme

@Composable
fun TopScreen(
    navHostController: NavHostController,
    mainActivityViewModel: MainActivityViewModel,
    viewModel: TopViewModel
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(mainActivityViewModel.clickCubeFlow) {
        mainActivityViewModel.clickCubeFlow.collect {
            viewModel.setColorPickerVisible(true)
        }
    }

    LaunchedEffect(key1 = state.cubeColor, key2 = Unit) {
        UnityPlayer.UnitySendMessage("Cube", "ChangeColor", state.cubeColor.toHtmlHex())
    }

    TopScreenContent(
        onClickBlue = { navHostController.navigateToBlue() },
        onClickYellow = { navHostController.navigateToYellow() },
        color = state.cubeColor,
        colorPickerVisible = state.colorPickerVisible,
        onColorPickerColorChanged = { viewModel.setCubeColor(it) },
        onCloseColorPicker = { viewModel.setColorPickerVisible(false) }
    )
}

@Composable
fun TopScreenContent(
    onClickBlue: () -> Unit,
    onClickYellow: () -> Unit,
    color: Color,
    colorPickerVisible: Boolean,
    onColorPickerColorChanged: (Color) -> Unit,
    onCloseColorPicker: () -> Unit,
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
            if (colorPickerVisible) {
                colorPicker(
                    modifier = Modifier.aspectRatio(1.2f),
                    color = color,
                    onColorChanged = {
                        onColorPickerColorChanged(it)
                    },
                    onClose = {
                        onCloseColorPicker()
                    }
                )
            }
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

fun Color.toHtmlHex(): String {
    return String.format(
        "#%02x%02x%02x",
        (255 * red).toInt(),
        (255 * green).toInt(),
        (255 * blue).toInt()
    )
}

@Composable
fun colorPicker(
    modifier: Modifier = Modifier,
    color: Color,
    onColorChanged: (Color) -> Unit,
    onClose: () -> Unit
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { onClose() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_close_24),
                    contentDescription = "close"
                )
            }
        }
        ClassicColorPicker(
            color = color,
            showAlphaBar = false,
            onColorChanged = {
                onColorChanged(Color(it.toColorInt()))
            }
        )
    }
}

@Preview
@Composable
fun Preview() {
    AppTheme {
        TopScreenContent(
            onClickBlue = {},
            onClickYellow = {},
            color = Color.White,
            colorPickerVisible = true,
            onColorPickerColorChanged = {},
            onCloseColorPicker = {}
        )
    }
}