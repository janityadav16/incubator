import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.incubator.R

class BMI_Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        val etHeight = findViewById<EditText>(R.id.etHeight)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvCategory = findViewById<TextView>(R.id.tvCategory)

        btnCalculate.setOnClickListener {
            val heightStr = etHeight.text.toString()
            val weightStr = etWeight.text.toString()

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val height = heightStr.toFloat() / 100 // convert cm → meters
            val weight = weightStr.toFloat()
            val bmi = weight / (height * height)

            tvResult.text = "Your BMI: %.2f".format(bmi)

            when {
                bmi < 18.5 -> {
                    tvCategory.text = "Category: Underweight"
                    tvCategory.setTextColor(Color.BLUE)
                }
                bmi in 18.5..24.9 -> {
                    tvCategory.text = "Category: Normal weight"
                    tvCategory.setTextColor(Color.GREEN)
                }
                bmi in 25.0..29.9 -> {
                    tvCategory.text = "Category: Overweight"
                    tvCategory.setTextColor(Color.parseColor("#FFA500")) // Orange
                }
                else -> {
                    tvCategory.text = "Category: Obese"
                    tvCategory.setTextColor(Color.RED)
                }
            }
        }
    }
}