package com.example.cardgames

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FinishScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_screen)
        var mediaPlayer: MediaPlayer? = null
        mediaPlayer = MediaPlayer.create(this,R.raw.finish)
        mediaPlayer?.start()
    }
}