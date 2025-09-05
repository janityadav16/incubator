import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import com.example.incubator.R

class StopWatch : AppCompatActivity() {
    private var seconds = 0
    private var running = false
    private var wasRunning = false // To handle orientation changes
    private var handler = Handler(Looper.getMainLooper()) //A Handler is a class in Android that allows you to schedule and manage tasks (code blocks) to run on a specific thread.
    //In stopwatch, It updates the UI (TextView) every second.
    // A Looper is a message loop that runs continuously in a thread.
    // This handler post tasks (like updating the TextView) to run on the UI thread safely.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_watch)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }

        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnPause = findViewById<Button>(R.id.btnPause)
        val btnReset = findViewById<Button>(R.id.btnReset)

        // Runnable to update timer every second
        /* class MyRunnable : Runnable {
            override fun run() {
                // code here
            }
        }
        val runnable = MyRunnable()
        OR
        val runnable = object : Runnable {
            override fun run() {
                // code here
            }
        */
        val runnable = object : Runnable {
            override fun run() {
                if (running) {
                    seconds++
                    val hrs = seconds / 3600
                    val mins = (seconds % 3600) / 60
                    val secs = seconds % 60
                    tvTimer.text = String.format("%02d:%02d:%02d", hrs, mins, secs)
                }
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnable)

        btnStart.setOnClickListener {
            running = true
        }

        btnPause.setOnClickListener {
            running = false
        }

        btnReset.setOnClickListener {
            running = false
            seconds = 0
            tvTimer.text = "00:00:00"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }
}