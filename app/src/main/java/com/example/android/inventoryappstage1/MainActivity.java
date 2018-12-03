package com.example.android.inventoryappstage1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.inventoryappstage1.data.BookContract.BookEntry;
import com.example.android.inventoryappstage1.data.BookDbHelper;

public class MainActivity extends AppCompatActivity {
    // Database helper that will provide us access to the database
    private BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new BookDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        displayDatabaseInfo();
    }

    // Temporary helper method to display information in TextView about the state of the books database.
    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_BOOK_NAME,
                BookEntry.COLUMN_BOOK_PRICE,
                BookEntry.COLUMN_BOOK_QUANTITY,
                BookEntry.COLUMN_BOOK_SUPPLIER_NAME,
                BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor = db.query(
                BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        TextView displayView = findViewById(R.id.book_list);
        try {
            displayView.setText("The books table contains " + cursor.getCount() + " books.\n\n");
            displayView.append(BookEntry._ID + " - " +
                    BookEntry.COLUMN_BOOK_NAME + " - " +
                    BookEntry.COLUMN_BOOK_PRICE + " - " +
                    BookEntry.COLUMN_BOOK_QUANTITY + " - " +
                    BookEntry.COLUMN_BOOK_SUPPLIER_NAME + " - " +
                    BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER + "\n");
            int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER);
            // While loop to iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String bookName = cursor.getString(nameColumnIndex);
                int bookPrice = cursor.getInt(priceColumnIndex);
                int bookQuantity = cursor.getInt(quantityColumnIndex);
                String bookSuplierName = cursor.getString(supplierNameColumnIndex);
                String bookSuplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        bookName + " - " +
                        bookPrice + " - " +
                        bookQuantity + " - " +
                        bookSuplierName + " - " +
                        bookSuplierPhoneNumber));
            }
        } finally {
            cursor.close();
        }
    }

    // Helper method to insert book into the database.
    private void insertBook() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_BOOK_NAME, "Dummy Book");
        values.put(BookEntry.COLUMN_BOOK_PRICE, 100);
        values.put(BookEntry.COLUMN_BOOK_QUANTITY, 8);
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_NAME, "John Doe");
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER, "1234567890");
        // Insert a new row in the database, returning the ID of the new row.
        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "New raw ID " + newRowId);
    }

    // Helper method to delete all book entries from the database.
    private void deleteBook() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(BookEntry.TABLE_NAME, null, null);
        // Insert a new row in the database, returning the ID of the new row.
        Log.v("MainActivity", "All entries deleted.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertBook();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete_all_entries:
                deleteBook();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
