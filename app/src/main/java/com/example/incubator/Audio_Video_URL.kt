import android.content.Intent
import android.media.MediaPlayer

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.incubator.R

class Audio_Video_URL : AppCompatActivity() {
    private var audioPlayer: MediaPlayer? = null
    private lateinit var videoView: VideoView
    private lateinit var mediaController: MediaController
    private lateinit var videoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_video_url)

        val btnPlayAudio = findViewById<Button>(R.id.btnPlayAudio)
        val btnPauseAudio = findViewById<Button>(R.id.btnPauseAudio)
        val btnStopAudio = findViewById<Button>(R.id.btnStopAudio)
        val buttongoogle = findViewById<Button>(R.id.buttonGoogle)

        btnPlayAudio.setOnClickListener {
            // (Re)create player only if needed
            if (audioPlayer == null) {
                audioPlayer = MediaPlayer.create(this, R.raw.audio_sample)
            }
            audioPlayer?.start()
        }

        btnPauseAudio.setOnClickListener {
            if (audioPlayer?.isPlaying == true) {
                audioPlayer?.pause()
            }
        }

        btnStopAudio.setOnClickListener {
            audioPlayer?.stop()
            audioPlayer?.release()
            audioPlayer = null
        }

        // --- VIDEO ---
        videoView = findViewById(R.id.videoView)
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        // Build a URI that points to res/raw/video_sample.mp4
        videoUri = Uri.parse("android.resource://${packageName}/${R.raw.video_sample}")

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoUri)

        val btnPlayVideo = findViewById<Button>(R.id.btnPlayVideo)
        val btnPauseVideo = findViewById<Button>(R.id.btnPauseVideo)
        val btnStopVideo = findViewById<Button>(R.id.btnStopVideo)

        btnPlayVideo.setOnClickListener {
            videoView.start()
        }

        btnPauseVideo.setOnClickListener {
            if (videoView.isPlaying)
                videoView.pause()
        }

        btnStopVideo.setOnClickListener {
            // Stop playback and reset the source so it can play from the start again
            videoView.stopPlayback()
            videoView.setVideoURI(videoUri)
        }

        buttongoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com")
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        audioPlayer?.release()
        audioPlayer = null
        videoView.stopPlayback()
    }
}