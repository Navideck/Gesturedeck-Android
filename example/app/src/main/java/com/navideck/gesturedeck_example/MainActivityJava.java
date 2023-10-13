package com.navideck.gesturedeck_example;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import com.navideck.gesturedeck_android.GesturedeckMedia;
import com.navideck.gesturedeck_android.GesturedeckMediaOverlay;
import com.navideck.gesturedeck_android.model.GestureState;
import com.navideck.gesturedeck_android.model.PanSensitivity;
import com.navideck.gesturedeck_android.model.SwipeDirection;
import com.navideck.gesturedeckexample.R;

public class MainActivityJava extends AppCompatActivity {
    private GesturedeckMedia gestureDeck;
    private Button btnStart;
    private Button btnStop;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("Gesturedeck Example Java");
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        gestureDeck = new GesturedeckMedia(this);

        updateGesturedeckState(true);

        btnStart.setOnClickListener(v -> {
            gestureDeck.start();
            updateGesturedeckState(true);
        });

        btnStop.setOnClickListener(v -> {
            gestureDeck.stop();
            updateGesturedeckState(false);
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event != null) gestureDeck.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void updateGesturedeckState(Boolean isEnabled) {
        btnStart.setEnabled(!isEnabled);
        btnStop.setEnabled(isEnabled);
    }
}