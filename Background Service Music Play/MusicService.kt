package com.example.registrationform

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MusicService : Service() {

    // MediaPlayer to handle audio playback
    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Use built-in Android ringtone as music source
        // RingtoneManager gives us a system ringtone URI
        val uri = android.media.RingtoneManager.getDefaultUri(
            android.media.RingtoneManager.TYPE_RINGTONE
        )

        // Create MediaPlayer with the ringtone URI
        mediaPlayer = MediaPlayer.create(this, uri)

        // Loop the music continuously
        mediaPlayer?.isLooping = true

        // Start playing
        mediaPlayer?.start()

        Toast.makeText(this, "Music Started", Toast.LENGTH_SHORT).show()

        // START_STICKY: restart service if killed by system
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop and release MediaPlayer when service is destroyed
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show()
    }

    // Not a bound service so return null
    override fun onBind(intent: Intent?): IBinder? = null
}
