import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.incubator.R

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val name = intent.getStringExtra("NAME")
        val email = intent.getStringExtra("EMAIL")
        val phone = intent.getStringExtra("PHONE")

        tvName.text = "Name: $name"
        tvEmail.text = "Email: $email"
        tvPhone.text = "Phone: $phone"

        btnBack.setOnClickListener {
            finish() // closes DisplayActivity and goes back to MainActivity
        }
    }
}