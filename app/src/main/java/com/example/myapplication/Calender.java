package com.example.myapplication;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;

@RequiresApi(api = Build.VERSION_CODES.O)
public final class Calender {
    public static final int TIME_SPEED = 6000;

    private static Instant start = Instant.now();

    public Calender() {
    }

    public static Instant now() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Instant.ofEpochMilli(start.toEpochMilli() + (Instant.now().toEpochMilli() - start.toEpochMilli()) * TIME_SPEED);
        }
        return null;
    }

}
