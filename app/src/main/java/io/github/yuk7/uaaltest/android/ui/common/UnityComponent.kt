package io.github.yuk7.uaaltest.android.ui.common

import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.lifecycleScope
import com.unity3d.player.UnityPlayer
import kotlinx.coroutines.launch

class UnityComponent {
    companion object {
        var unityPlayer: UnityPlayer? = null

        @Composable
        fun UnityComposable(modifier: Modifier = Modifier/*, unityPlayer: UnityPlayer*/) {
            val context = LocalContext.current.applicationContext
            val frameLayout = remember { FrameLayout(context, null, 0, 0) }

            AndroidView(
                modifier = modifier,
                factory = {
                    frameLayout
                }
            )

            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME -> {
                            lifecycleOwner.lifecycleScope.launch {
                                if (unityPlayer == null) {
                                    unityPlayer = UnityPlayer(context)
                                }

                                /* val createGlViewMethod =
                                    unityPlayer?.javaClass?.getDeclaredMethod("CreateGlView")
                                createGlViewMethod?.isAccessible = true
                                // val surfaceView =
                                createGlViewMethod?.invoke(unityPlayer) as SurfaceView*/

                                /* val glViewField =
                                    unityPlayer?.javaClass?.getDeclaredField("mGlView")
                                glViewField?.isAccessible = true
                                glViewField?.set(unityPlayer, surfaceView) */

                                frameLayout.removeAllViewsInLayout()
                                frameLayout.addView(
                                    unityPlayer,
                                    0,
                                    ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                )

                                unityPlayer?.resume()
                                unityPlayer?.windowFocusChanged(true)
                                unityPlayer?.requestFocus()
                            }
                        }
                        Lifecycle.Event.ON_PAUSE -> {
                            unityPlayer?.pause()
                        }
                        Lifecycle.Event.ON_STOP -> {
                            frameLayout.removeView(unityPlayer)
                        }
                        else -> {}
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    unityPlayer?.pause()
                    frameLayout.removeView(unityPlayer)
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }
        }
    }
}
