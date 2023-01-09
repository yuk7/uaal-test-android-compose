package io.github.yuk7.uaaltest.android

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _clickCubeFlow = MutableSharedFlow<Color?>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST

    )
    val clickCubeFlow = _clickCubeFlow.asSharedFlow()

    fun clickUnityCube(data: String) {
        val colorLong = data.toLong(16)
        _clickCubeFlow.tryEmit(Color(colorLong))
    }
}