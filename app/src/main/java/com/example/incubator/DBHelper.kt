import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "DBNotes", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE Notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Notes")
        onCreate(db)
    }

    // Insert a note
    fun insertNote(title: String, content: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)
        val result = db.insert("Notes", null, values)
        db.close()
        return result != -1L
    }

    // Retrieve all notes
    fun getAllNotes(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM Notes", null)
    }
}