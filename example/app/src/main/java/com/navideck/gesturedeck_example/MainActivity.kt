package com.navideck.gesturedeck_example

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.navideck.gesturedeck_android.GesturedeckMedia
import com.navideck.gesturedeck_android.GesturedeckMediaOverlay
import com.navideck.gesturedeckexample.R

class MainActivity : AppCompatActivity() {
    private lateinit var gesturedeck: GesturedeckMedia
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Gesturedeck Example"

        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)

        gesturedeck = GesturedeckMedia(
            this,
            gesturedeckMediaOverlay = GesturedeckMediaOverlay(
                this,
                tintColor = ContextCompat.getColor(applicationContext, R.color.primary),
            ),
        )

        updateGesturedeckState(isEnabled = true)

        btnStart.setOnClickListener {
            gesturedeck.start()
            updateGesturedeckState(isEnabled = true)
        }

        btnStop.setOnClickListener {
            gesturedeck.stop()
            updateGesturedeckState(isEnabled = false)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { gesturedeck.onTouchEvent(it) }
        return super.onTouchEvent(event)
    }

    private fun updateGesturedeckState(isEnabled: Boolean) {
        btnStart.isEnabled = !isEnabled
        btnStop.isEnabled = isEnabled
    }
}