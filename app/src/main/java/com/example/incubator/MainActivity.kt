import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.database.Cursor
import com.example.incubator.R

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var btnSave: Button
    private lateinit var listViewNotes: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)

        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
        btnSave = findViewById(R.id.btnSave)
        listViewNotes = findViewById(R.id.listViewNotes)

        loadNotes()

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val isInserted = dbHelper.insertNote(title, content)
                if (isInserted) {
                    Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
                    etTitle.text.clear()
                    etContent.text.clear()
                    loadNotes()
                } else {
                    Toast.makeText(this, "Error saving note", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadNotes() {
        val cursor: Cursor = dbHelper.getAllNotes()
        val notesList = ArrayList<String>()

        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
                notesList.add("$title\n$content")
            } while (cursor.moveToNext())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notesList)
        listViewNotes.adapter = adapter
        cursor.close()
    }
}