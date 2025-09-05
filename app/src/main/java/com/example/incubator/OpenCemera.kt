import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.incubator.R

class OpenCemera : AppCompatActivity() {
    private lateinit var imageView: ImageView

    // Modern Activity Result API
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val photo = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cemera)
        imageView = findViewById(R.id.imageView)
        val btnOpenCamera = findViewById<Button>(R.id.btnOpenCamera)

        btnOpenCamera.setOnClickListener {
            val intent = android.content.Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(intent)
        }
    }
}