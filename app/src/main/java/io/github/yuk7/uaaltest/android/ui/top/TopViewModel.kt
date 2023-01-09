package io.github.yuk7.uaaltest.android.ui.top

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor() : ViewModel() {
    private val colorPickerVisible = MutableStateFlow(false)
    private val cubeColor = MutableStateFlow(Color.White)

    val state = combine(colorPickerVisible, cubeColor) { colorPickerVisible, cubeColor ->
        TopUiState(colorPickerVisible, cubeColor)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = TopUiState()
    )

    fun setColorPickerVisible(visible: Boolean) {
        colorPickerVisible.value = visible
    }

    fun setCubeColor(color: Color) {
        cubeColor.value = color
    }


}