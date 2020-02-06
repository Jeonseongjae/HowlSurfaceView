package com.techtown.howlsurfaceview

import android.media.MediaPlayer
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),SurfaceHolder.Callback,MediaPlayer.OnPreparedListener {
    var streamingUrl = "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"
    var surfaceHolder : SurfaceHolder? = null
    var mediaPlayer : MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surfaceHolder = howl_surfaceview.holder
        surfaceHolder?.addCallback(this)

        back_button.setOnClickListener{
            var position = mediaPlayer!!.currentPosition - 5000
            mediaPlayer?.seekTo(position)
        }
        next_button.setOnClickListener{
            var position = mediaPlayer!!.currentPosition + 5000
            mediaPlayer?.seekTo(position)
        }
        stop_button.setOnClickListener{
            mediaPlayer?.pause()
        }
        start_button.setOnClickListener{
            mediaPlayer?.start()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDisplay(holder)
        mediaPlayer?.setDataSource(streamingUrl)
        mediaPlayer?.prepare()
        mediaPlayer?.setOnPreparedListener(this)
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }
}
