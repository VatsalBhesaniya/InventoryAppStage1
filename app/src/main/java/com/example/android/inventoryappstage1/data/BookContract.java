package com.example.android.inventoryappstage1.data;

import android.provider.BaseColumns;

public final class BookContract {
    // To prevent instantiating the contract class, creating an empty constructor.
    public BookContract() {
    }

    public static final class BookEntry implements BaseColumns {
        public final static String TABLE_NAME = "books";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_BOOK_NAME = "productName";
        public final static String COLUMN_BOOK_PRICE = "price";
        public final static String COLUMN_BOOK_QUANTITY = "quantity";
        public final static String COLUMN_BOOK_SUPPLIER_NAME = "supplierName";
        public final static String COLUMN_BOOK_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumber";
    }
}
