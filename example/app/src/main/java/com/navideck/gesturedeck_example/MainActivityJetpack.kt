package com.navideck.gesturedeck_example

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.navideck.gesturedeck_android.GesturedeckMedia
import com.navideck.gesturedeck_android.GesturedeckMediaOverlay
import com.navideck.gesturedeckexample.R

class MainActivityJetpack : AppCompatActivity() {
    private lateinit var gesturedeck: GesturedeckMedia
    private val isGesturedeckStarted = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Gesturedeck Example Jetpack"

        gesturedeck = GesturedeckMedia(this)

        setContent {
            homeApp(this)
        }
    }

    @Composable
    private fun homeApp(context: Context) {
        val systemColorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
        MaterialTheme(
            colorScheme = systemColorScheme.copy(
                primary = Color(ContextCompat.getColor(context, R.color.primary))
            ),
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Button(
                            enabled = !isGesturedeckStarted.value,
                            onClick = {
                                gesturedeck.start()
                                isGesturedeckStarted.value = true
                            }) {
                            Text(text = "Start")
                        }
                        Button(
                            enabled = isGesturedeckStarted.value,
                            onClick = {
                                gesturedeck.stop()
                                isGesturedeckStarted.value = false
                            }) {
                            Text(text = "Stop")
                        }
                    }
                    Divider()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Perform Gestures",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        ev?.let { gesturedeck.onTouchEvent(it) }
        return super.dispatchTouchEvent(ev)
    }

    @Composable
    @Preview(showBackground = true)
    fun previewHomeApp() {
        homeApp(LocalContext.current)
    }
}
