package ke.co.ablabs.simplenote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyNotesDatabaseManager extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "com_example_simple_note.db";
    public static final String TABLE_NAME = "notes_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE = "note_column";
    private static final String TAG = "MyActivity";

    public MyNotesDatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NOTE + " TEXT" + ")";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNote(MyNotesDatabaseModel myNotesDatabaseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE, myNotesDatabaseModel.getNotes());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    void deleteNote(String note_){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NOTE + " =\"" + note_+ "\"");
        db.close();
    }
    public ArrayList<String> viewAllNotes(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            arrayList.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return arrayList;
    }
}