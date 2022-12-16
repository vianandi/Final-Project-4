package com.mbkm.project4.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_travel";
    public static final String TABLE_USER = "tb_user";
    public static final String COL_FULLNAME = "fullname";
    public static final String COL_USERNAME = "username";
    public static final String COL_PHONE = "phone";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String TABLE_BOOK = "tb_book";
    public static final String COL_ID_BOOK = "id_book";
    public static final String COL_ASAL = "asal";
    public static final String COL_TUJUAN = "tujuan";
    public static final String COL_TANGGAL = "tanggal";
    public static final String COL_DEWASA = "dewasa";
    public static final String COL_ANAK = "anak";
    public static final String COL_BUS = "bus";
    public static final String TABLE_HARGA = "tb_harga";
    public static final String COL_HARGA_DEWASA = "harga_dewasa";
    public static final String COL_HARGA_ANAK = "harga_anak";
    public static final String COL_HARGA_TOTAL = "harga_total";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_USER + " ("+ COL_FULLNAME + " TEXT, " + COL_USERNAME + " TEXT PRIMARY KEY,"+ COL_PHONE + " TEXT, " + COL_EMAIL + " TEXT, " + COL_PASSWORD + " TEXT)");
        db.execSQL("create table " + TABLE_BOOK + " (" + COL_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ASAL + " TEXT, " + COL_TUJUAN + " TEXT, " + COL_TANGGAL + " TEXT, " + COL_DEWASA + " TEXT, " + COL_ANAK + " TEXT,"+ COL_BUS + " TEXT)");
        db.execSQL("create table " + TABLE_HARGA + " (" + COL_USERNAME + " TEXT, " + COL_ID_BOOK + " INTEGER, " + COL_HARGA_DEWASA + " TEXT, " + COL_HARGA_ANAK + " TEXT, " + COL_HARGA_TOTAL + " TEXT, FOREIGN KEY(" + COL_USERNAME + ") REFERENCES " + TABLE_USER + ", FOREIGN KEY(" + COL_ID_BOOK + ") REFERENCES " + TABLE_BOOK + ")");
//        db.execSQL("insert into " + TABLE_USER + " values ('hzafran30@gmail.com','hilmi','hilmizaf');");
//        db.execSQL("insert into " + TABLE_USER + " values ('vian','vian','08644533224','vian@gmail.com','vian');");
        db.execSQL("insert into " + TABLE_USER + " values ('user','user','081234567890','user@gmail.com','user');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String fullname, String username, String phone, String email, String password) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_FULLNAME + ", " + COL_USERNAME + ", " + COL_PHONE + ", " + COL_EMAIL + ", " + COL_PASSWORD + ") VALUES (?,?,?,?,?)", new String[]{fullname, username, phone, email, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }


    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}