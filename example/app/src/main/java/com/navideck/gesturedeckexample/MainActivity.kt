package com.navideck.gesturedeckexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.navideck.gesturedeck_android.GesturedeckMedia
import com.navideck.gesturedeck_android.GesturedeckMediaOverlay

class MainActivity : AppCompatActivity() {
    private lateinit var gesturedeck: GesturedeckMedia
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Gesturedeck Example"

        val gesturedeckEvent = findViewById<TextView>(R.id.txtEvent)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)

        gesturedeck = GesturedeckMedia(
            this,
            tapAction = {
                gesturedeckEvent.text = "Gesturedeck Event: Tap"
            },
            swipeLeftAction = {
                gesturedeckEvent.text = "Gesturedeck Event: Swipe Left"
            },
            swipeRightAction = {
                gesturedeckEvent.text = "Gesturedeck Event: Swipe Right"
            },
            panAction = { _, _, _ ->
                gesturedeckEvent.text = "Gesturedeck Event: Pan"
            },
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