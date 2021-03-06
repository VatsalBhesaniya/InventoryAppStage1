package com.example.android.inventoryappstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryappstage1.data.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = BookDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "bookStore.db";
    private static final int DATABASE_VERSION = 1;

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a String that contains the SQL statement to create the books table
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BookEntry.TABLE_NAME + " ("
                + BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL, "
                + BookEntry.COLUMN_BOOK_PRICE + " INTEGER NOT NULL, "
                + BookEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL, "
                + BookEntry.COLUMN_BOOK_SUPPLIER_NAME + " TEXT NOT NULL, "
                + BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // The database is at version 1, so there's nothing to do be done here.
    }
}
