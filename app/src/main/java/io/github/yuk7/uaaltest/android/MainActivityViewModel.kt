package io.github.yuk7.uaaltest.android

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _clickCubeFlow = MutableStateFlow<Color?>(null)
    val clickCubeFlow = _clickCubeFlow.asStateFlow()

    fun clickUnityCube(data: String) {
        val colorLong = data.toLong(16)
        _clickCubeFlow.value = Color(colorLong)
    }
}