package app.chraz.todolistmpv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chraz on 15/03/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "todoManager",
            TODO_TABLE = "todos",
            KEY_ID = "id",
            KEY_TITLE = "title",
            KEY_DESCRIPTION = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TODO_TABLE + "("+ KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TITLE + " TEXT, "+ KEY_DESCRIPTION + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TODO_TABLE);

        onCreate(db);
    }

    public void createTodo(Todo newTodo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues todoValues = new ContentValues();

        todoValues.put(KEY_TITLE, newTodo.getTitle());
        todoValues.put(KEY_DESCRIPTION, newTodo.getDescription());

        db.insert(TODO_TABLE, null, todoValues);
        db.close();
    }

    private Todo cursorToTodo(Cursor cursor) {
        String title = cursor.getString(1);
        String description = cursor.getString(2);

        return new Todo(title, description);
    }

    public Todo getTodo(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TODO_TABLE, new String[]{
                KEY_TITLE,
                KEY_DESCRIPTION
        }, KEY_ID + "=?", new String[] {
                String.valueOf(id)
        }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursorToTodo(cursor);

    }

    public List<Todo> getTodoList() {
        List<Todo> todos = new ArrayList<Todo>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TODO_TABLE, null);
        if (cursor.moveToFirst()) {
            todos.add(cursorToTodo(cursor));
            while(cursor.moveToNext()) {
                todos.add(cursorToTodo(cursor));
            }
        }
        return todos;
    }

}
