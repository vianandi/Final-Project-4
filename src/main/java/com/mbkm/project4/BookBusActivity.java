package com.mbkm.project4;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mbkm.project4.database.DatabaseHelper;
import com.mbkm.project4.session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class BookBusActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Spinner spinAsal, spinTujuan, spinDewasa, spinAnak, spinBus;
    SessionManager session;
    String email;
    int id_book;
    public String sAsal, sTujuan, sTanggal, sDewasa, sAnak, sBus;
    int jmlDewasa, jmlAnak;
    int hargaDewasa, hargaAnak;
    int hargaTotalDewasa, hargaTotalAnak, hargaTotal;
    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus);

        dbHelper = new DatabaseHelper(BookBusActivity.this);
        db = dbHelper.getReadableDatabase();

        final String[] asal = {"Jakarta", "Bandung", "Semarang", "Yogyakarta", "Malang"};
        final String[] tujuan = {"Jakarta", "Bandung", "Semarang", "Yogyakarta", "Malang"};
        final String[] dewasa = {"0", "1", "2", "3", "4", "5"};
        final String[] anak = {"0", "1", "2", "3"};
        final String[] bus = {"PO haryanto", "PO Nusantara", "PO Shantika", "PO Agra Mas", "PO Bejeu", "PO Pahala Kencana"};

        spinAsal = findViewById(R.id.asal);
        spinTujuan = findViewById(R.id.tujuan);
        spinDewasa = findViewById(R.id.dewasa);
        spinAnak = findViewById(R.id.anak);
        spinBus = findViewById(R.id.bus);

        ArrayAdapter<CharSequence> adapterAsal = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, asal);
        adapterAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAsal.setAdapter(adapterAsal);

        ArrayAdapter<CharSequence> adapterTujuan = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, tujuan);
        adapterTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTujuan.setAdapter(adapterTujuan);

        ArrayAdapter<CharSequence> adapterDewasa = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, dewasa);
        adapterDewasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDewasa.setAdapter(adapterDewasa);

        ArrayAdapter<CharSequence> adapterAnak = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, anak);
        adapterAnak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAnak.setAdapter(adapterAnak);

        ArrayAdapter<CharSequence> adapterBus = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, bus);
        adapterBus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBus.setAdapter(adapterBus);

        spinAsal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sAsal = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sTujuan = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinDewasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sDewasa = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinAnak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sAnak = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinBus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sBus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnBook = findViewById(R.id.book);

        etTanggal = findViewById(R.id.tanggal_berangkat);
        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.requestFocus();
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);
        setDateTimeField();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perhitunganHarga();
                if (sAsal != null && sTujuan != null && sTanggal != null && sDewasa != null) {
                    if ((sAsal.equalsIgnoreCase("jakarta") && sTujuan.equalsIgnoreCase("jakarta"))
                            || (sAsal.equalsIgnoreCase("bandung") && sTujuan.equalsIgnoreCase("bandung"))
                            || (sAsal.equalsIgnoreCase("semarang") && sTujuan.equalsIgnoreCase("semarang"))
                            || (sAsal.equalsIgnoreCase("yogyakarta") && sTujuan.equalsIgnoreCase("yogyakarta"))
                            || (sAsal.equalsIgnoreCase("malang") && sTujuan.equalsIgnoreCase("malang"))) {
                        Toast.makeText(BookBusActivity.this, "Asal dan Tujuan tidak boleh sama !", Toast.LENGTH_LONG).show();
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(BookBusActivity.this)
                                .setTitle("Ingin booking bus sekarang?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            db.execSQL("INSERT INTO TB_BOOK (asal, tujuan, tanggal, dewasa, anak, bus) VALUES ('" +
                                                    sAsal + "','" +
                                                    sTujuan + "','" +
                                                    sTanggal + "','" +
                                                    sDewasa + "','" +
                                                    sAnak + "','" +
                                                    sBus + "');");
                                            cursor = db.rawQuery("SELECT id_book FROM TB_BOOK ORDER BY id_book DESC", null);
                                            cursor.moveToLast();
                                            if (cursor.getCount() > 0) {
                                                cursor.moveToPosition(0);
                                                id_book = cursor.getInt(0);
                                            }
                                            db.execSQL("INSERT INTO TB_HARGA (username, id_book, harga_dewasa, harga_anak, harga_total) VALUES ('" +
                                                    email + "','" +
                                                    id_book + "','" +
                                                    hargaTotalDewasa + "','" +
                                                    hargaTotalAnak + "','" +
                                                    hargaTotal + "');");
                                            Toast.makeText(BookBusActivity.this, "Booking berhasil", Toast.LENGTH_LONG).show();
                                            finish();
                                        } catch (Exception e) {
                                            Toast.makeText(BookBusActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Tidak", null)
                                .create();
                        dialog.show();
                    }
                } else {
                    Toast.makeText(BookBusActivity.this, "Mohon lengkapi data pemesanan!", Toast.LENGTH_LONG).show();
                }
            }
        });

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbKrl);
        toolbar.setTitle("Form Booking");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void perhitunganHarga() {
        if (sAsal.equalsIgnoreCase("jakarta") && sTujuan.equalsIgnoreCase("bandung")) {
            hargaDewasa = 100000;
            hargaAnak = 70000;
        } else if (sAsal.equalsIgnoreCase("jakarta") && sTujuan.equalsIgnoreCase("malang")) {
            hargaDewasa = 200000;
            hargaAnak = 150000;
        } else if (sAsal.equalsIgnoreCase("jakarta") && sTujuan.equalsIgnoreCase("semarang")) {
            hargaDewasa = 150000;
            hargaAnak = 120000;
        } else if (sAsal.equalsIgnoreCase("jakarta") && sTujuan.equalsIgnoreCase("yogyakarta")) {
            hargaDewasa = 180000;
            hargaAnak = 140000;
        } else if (sAsal.equalsIgnoreCase("bandung") && sTujuan.equalsIgnoreCase("jakarta")) {
            hargaDewasa = 100000;
            hargaAnak = 70000;
        } else if (sAsal.equalsIgnoreCase("bandung") && sTujuan.equalsIgnoreCase("malang")) {
            hargaDewasa = 120000;
            hargaAnak = 100000;
        } else if (sAsal.equalsIgnoreCase("bandung") && sTujuan.equalsIgnoreCase("semarang")) {
            hargaDewasa = 120000;
            hargaAnak = 90000;
        } else if (sAsal.equalsIgnoreCase("bandung") && sTujuan.equalsIgnoreCase("yogyakarta")) {
            hargaDewasa = 190000;
            hargaAnak = 160000;
        } else if (sAsal.equalsIgnoreCase("malang") && sTujuan.equalsIgnoreCase("jakarta")) {
            hargaDewasa = 200000;
            hargaAnak = 150000;
        } else if (sAsal.equalsIgnoreCase("malang") && sTujuan.equalsIgnoreCase("bandung")) {
            hargaDewasa = 120000;
            hargaAnak = 100000;
        } else if (sAsal.equalsIgnoreCase("malang") && sTujuan.equalsIgnoreCase("semarang")) {
            hargaDewasa = 170000;
            hargaAnak = 130000;
        } else if (sAsal.equalsIgnoreCase("malang") && sTujuan.equalsIgnoreCase("yogyakarta")) {
            hargaDewasa = 180000;
            hargaAnak = 150000;
        } else if (sAsal.equalsIgnoreCase("semarang") && sTujuan.equalsIgnoreCase("jakarta")) {
            hargaDewasa = 150000;
            hargaAnak = 120000;
        } else if (sAsal.equalsIgnoreCase("semarang") && sTujuan.equalsIgnoreCase("bandung")) {
            hargaDewasa = 120000;
            hargaAnak = 90000;
        } else if (sAsal.equalsIgnoreCase("semarang") && sTujuan.equalsIgnoreCase("yogyakarta")) {
            hargaDewasa = 80000;
            hargaAnak = 40000;
        } else if (sAsal.equalsIgnoreCase("semarang") && sTujuan.equalsIgnoreCase("malang")) {
            hargaDewasa = 170000;
            hargaAnak = 130000;
        } else if (sAsal.equalsIgnoreCase("yogyakarta") && sTujuan.equalsIgnoreCase("jakarta")) {
            hargaDewasa = 180000;
            hargaAnak = 140000;
        } else if (sAsal.equalsIgnoreCase("yogyakarta") && sTujuan.equalsIgnoreCase("bandung")) {
            hargaDewasa = 190000;
            hargaAnak = 160000;
        } else if (sAsal.equalsIgnoreCase("yogyakarta") && sTujuan.equalsIgnoreCase("semarang")) {
            hargaDewasa = 80000;
            hargaAnak = 40000;
        } else if (sAsal.equalsIgnoreCase("yogyakarta") && sTujuan.equalsIgnoreCase("malang")) {
            hargaDewasa = 180000;
            hargaAnak = 150000;
        }

        jmlDewasa = Integer.parseInt(sDewasa);
        jmlAnak = Integer.parseInt(sAnak);

        hargaTotalDewasa = jmlDewasa * hargaDewasa;
        hargaTotalAnak = jmlAnak * hargaAnak;
        hargaTotal = hargaTotalDewasa + hargaTotalAnak;
    }

    private void setDateTimeField() {
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal.show();
            }
        });

        dpTanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                sTanggal = dayOfMonth + " " + bulan[monthOfYear] + " " + year;
                etTanggal.setText(sTanggal);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}