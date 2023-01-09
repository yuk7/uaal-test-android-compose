package io.github.yuk7.uaaltest.android.ui.top

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor() : ViewModel() {
    private val _visibleFlow = MutableStateFlow<Boolean>(false)
    val visibleFlow = _visibleFlow.asStateFlow()

    fun setVisible(data: Boolean) {
        _visibleFlow.value = data
    }
}