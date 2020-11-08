package com.example.ordernotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class EditNotes extends AppCompatActivity {
    TextView tvTrxSelMonth;
    TextView tvTrxSelYear;
    EditText etTglTrx;
    EditText etInvoiceNo;
    RadioGroup rgSelPlatform;
    EditText namaPlatformLainnya;
    EditText buyerName;
    EditText buyerPhone;
    EditText buyerAddress;
    EditText itemName;
    EditText etQty;
    EditText etPrice;
    TextView tvHargaxQty;
    TextView tvResHargaxQty;
    EditText etAsuransi;
    EditText etOngkir;
    TextView tvTotal;
    EditText etKurir;
    EditText etTglKirim;
    EditText etTglSampai;
    EditText etTglSelesai;
    EditText etDanaMasuk;
    EditText etTarikRp;
    EditText etTglTarikSaldo;
    EditText etHargaPemasok;
    TextView pemasokHargaxQty;
    TextView tvResPemasok;
    EditText ongkirPemasok;
    TextView tvTotalPemasok;
    RadioGroup rgStatus;
    EditText etNotes;
    Button btnEdit;
    Calendar calendar;
    DBHelper dbHelper;

    Locale locale;
    int leHargaTotal = 0;
    int leSupplierTotal = 0;
    int month = 0;
    int year = 0;
    int day = 0;
    String selMonth = "";
    String selYear = "";
    int pAsuransi = 0;
    int pOngkir = 0;
    int sOngkir = 0;
    int lQty = 0;
    int lHarga = 0;
    int danaMasuk = 0;
    int tarikRp = 0;
    int sHarga = 0;
    String tglTrx;
    String tglKirim;
    String tglSampai;
    String tglSelesai;
    String tglTarikSaldo;
    String platform = "";
    String status = "";
    String id = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder alert = new AlertDialog.Builder(EditNotes.this);
        alert.setMessage("Hapus data?").setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("EditNotes", "id " + id);
                int del = dbHelper.deleteData(id);
                if(del > 0){
                    Toast.makeText(EditNotes.this, "Data dihapus", Toast.LENGTH_LONG).show();
                    Intent toMain = new Intent(EditNotes.this, MainActivity.class);
                    startActivity(toMain);
                    finish();
                }
                else{
                    Toast.makeText(EditNotes.this, "Data gagal dihapus", Toast.LENGTH_LONG).show();
                }
            }
        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.setTitle("Hapus Data");
        alertDialog.show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        tvTrxSelMonth = findViewById(R.id.tvTrxSelMonth);
        tvTrxSelYear = findViewById(R.id.tvTrxSelYear);
        etTglTrx = findViewById(R.id.etTglTrx);
        etInvoiceNo = findViewById(R.id.etInvoiceNo);
        rgSelPlatform = findViewById(R.id.rgSelPlatform);
//        namaPlatformLainnya = findViewById(R.id.namaPlatformLainnya);
        buyerName = findViewById(R.id.etBuyerName);
        buyerPhone = findViewById(R.id.etBuyerPhone);
        buyerAddress = findViewById(R.id.etBuyerAddress);
        itemName = findViewById(R.id.etItemName);
        etQty = findViewById(R.id.etQty);
        etPrice = findViewById(R.id.etPrice);
        tvHargaxQty = findViewById(R.id.tvHargaxQty);
        tvResHargaxQty = findViewById(R.id.tvResHargaxQty);
        etAsuransi = findViewById(R.id.etAsuransi);
        etOngkir = findViewById(R.id.etOngkir);
        tvTotal = findViewById(R.id.tvTotal);
        etKurir = findViewById(R.id.etKurir);
        etTglKirim = findViewById(R.id.etTglKirim);
        etTglSampai = findViewById(R.id.etTglSampai);
        etTglSelesai = findViewById(R.id.etTglSelesai);
        etDanaMasuk = findViewById(R.id.etDanaMasuk);
        etTarikRp = findViewById(R.id.etTarikRp);
        etTglTarikSaldo = findViewById(R.id.etTglTarikSaldo);
        etHargaPemasok = findViewById(R.id.etHargaPemasok);
        pemasokHargaxQty = findViewById(R.id.pemasokHargaxQty);
        tvResPemasok = findViewById(R.id.tvResPemasok);
        ongkirPemasok = findViewById(R.id.etOngkirPemasok);
        tvTotalPemasok = findViewById(R.id.tvTotalPemasok);
        rgStatus = findViewById(R.id.rgStatus);
        etNotes = findViewById(R.id.etNotes);
        btnEdit = findViewById(R.id.btnEdit);
        dbHelper = new DBHelper(this);
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        locale = new Locale("in", "INDONESIA");
        getMonthName(month, locale);
        getYear(year);

        Intent fromAdapter = getIntent();
        id = fromAdapter.getStringExtra("leID");
//        Log.d("EditNotes", "id: " + id);

        getData(id);

        tvTrxSelMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(EditNotes.this, new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {
                        getMonthName(selectedMonth, locale);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
                builder.setActivatedMonth(month).setActivatedYear(year).setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
                        .showMonthOnly()
                        .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                            @Override
                            public void onMonthChanged(int selectedMonth) {
//                                Toast.makeText(getActivity(), "selected month: " + selectedMonth, Toast.LENGTH_LONG).show();
                                getMonthName(selectedMonth, locale);
                            }
                        }).build().show();
            }
        });

        tvTrxSelYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(EditNotes.this, new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
                builder.setActivatedMonth(month).setActivatedYear(year).setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
                        .showYearOnly()
                        .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                            @Override
                            public void onYearChanged(int year) {
                                getYear(year);
                            }
                        }).build().show();
            }
        });

        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String lol = etQty.getText().toString()+"";
                if(lol.equals("")){
                    Toast.makeText(EditNotes.this, "Masukkan jumlah barang", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(s.toString().equals("")){
                    Toast.makeText(EditNotes.this, "Masukkan harga barang", Toast.LENGTH_SHORT).show();
                    return;
                }

                int qty = Integer.parseInt(lol);

                tvHargaxQty.setText(qty + " x " + Integer.parseInt(s.toString()));
                int res = qty * Integer.parseInt(s.toString());
                tvResHargaxQty.setText(String.valueOf(res));
                tvTotal.setText(String.valueOf(res));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etAsuransi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String a = tvResHargaxQty.getText().toString()+"";
                int resBeforeAss, resAfterAss;
                if(!a.equals("") && !s.toString().equals("")){
                    resBeforeAss = Integer.parseInt(a);
                    resAfterAss = resBeforeAss + Integer.parseInt(s.toString());
                    String adaOngkirGa = etOngkir.getText().toString()+"";
                    if(adaOngkirGa.equals("")){
                        tvTotal.setText(String.valueOf(resAfterAss));
                        leHargaTotal = resAfterAss;
                        pOngkir = 0;
                    }
                    else {
                        int ongkir = Integer.parseInt(adaOngkirGa);
                        pOngkir = ongkir;
                        int total = ongkir + resAfterAss;
                        tvTotal.setText(String.valueOf(total));
                        pAsuransi = Integer.parseInt(s.toString());
                        leHargaTotal = total;
                    }
                }
                if(s.toString().equals("")){
                    tvTotal.setText(a);
                    leHargaTotal = Integer.parseInt(a);
                    pAsuransi = 0;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOngkir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String a = tvResHargaxQty.getText().toString()+"";
                int resBeforeOngkir, resAfterOngkir;
                if(!a.equals("") && !s.toString().equals("")){
                    resBeforeOngkir = Integer.parseInt(a);
                    resAfterOngkir = resBeforeOngkir + Integer.parseInt(s.toString());
                    String adaAsGa = etAsuransi.getText().toString()+"";
                    if(adaAsGa.equals("")){
                        tvTotal.setText(String.valueOf(resAfterOngkir));
                        leHargaTotal = resAfterOngkir;
                        pAsuransi = 0;
                    }
                    else{
                        int asuransi = Integer.parseInt(adaAsGa);
                        pAsuransi = asuransi;
                        int total = asuransi + resAfterOngkir;
                        tvTotal.setText(String.valueOf(total));
                        pOngkir = Integer.parseInt(s.toString());
                        leHargaTotal = total;
                    }
                }
                if(s.toString().equals("")){
                    tvTotal.setText(a);
                    pOngkir = 0;
                    leHargaTotal = Integer.parseInt(a);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etHargaPemasok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String lol = etQty.getText().toString()+"";
                Log.d("Main", "lol: " + lol);
                if(s.toString().equals("")){
                    Toast.makeText(EditNotes.this, "Masukkan harga barang", Toast.LENGTH_SHORT).show();
                    return;
                }
                int qty = Integer.parseInt(lol);
                pemasokHargaxQty.setText(qty + " x " + s.toString());
                int res = qty * Integer.parseInt(s.toString());
                tvResPemasok.setText(String.valueOf(res));
                tvTotalPemasok.setText(String.valueOf(res));
                leSupplierTotal = res;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ongkirPemasok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String a = tvResPemasok.getText().toString()+"";
                int resBeforeOngkir, resAfterOngkir;
                if(!a.equals("") && !s.toString().equals("")){
                    resBeforeOngkir = Integer.parseInt(a);
                    resAfterOngkir = resBeforeOngkir + Integer.parseInt(s.toString());
                    tvTotalPemasok.setText(String.valueOf(resAfterOngkir));
                    leSupplierTotal = resAfterOngkir;
                    sOngkir = Integer.parseInt(s.toString());
                }
                if(s.toString().equals("")){
                    tvTotalPemasok.setText(a);
                    sOngkir = 0;
                    leSupplierTotal = Integer.parseInt(a);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etTglKirim.setFocusable(false);
        etTglSampai.setFocusable(false);
        etTglSelesai.setFocusable(false);
        etTglTarikSaldo.setFocusable(false);
        etTglTrx.setFocusable(false);

        etTglTrx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditNotes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        tglTrx = year + "-" + month + "-" + day;
                        etTglTrx.setText(tglTrx);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etTglKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditNotes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        tglKirim = year + "-" + month + "-" + day;
                        etTglKirim.setText(tglKirim);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etTglSampai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditNotes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        tglSampai = year + "-" + month + "-" + day;
                        etTglSampai.setText(tglSampai);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etTglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditNotes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        tglSelesai = year + "-" + month + "-" + day;
                        etTglSelesai.setText(tglSelesai);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etTglTarikSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditNotes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        tglTarikSaldo = year + "-" + month + "-" + day;
                        etTglTarikSaldo.setText(tglTarikSaldo);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String asuransiDariPenjual = etAsuransi.getText().toString();
                String ongkirDariPenjual = etOngkir.getText().toString();
                String invoice = etInvoiceNo.getText().toString()+"";
                pAsuransi = Integer.parseInt(asuransiDariPenjual);
                pOngkir = Integer.parseInt(ongkirDariPenjual);
                String ongkirDariSupplier = ongkirPemasok.getText().toString();
                sOngkir = Integer.parseInt(ongkirDariSupplier);
                int pc = rgSelPlatform.getCheckedRadioButtonId();
                if(pc == -1){
                    Toast.makeText(EditNotes.this, "Pilih platform", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    switch(pc){
                        case(R.id.rb1):
                            platform = "Tokopedia";
                            break;
                        case(R.id.rb2):
                            platform = "Bukalapak";
                            break;
                        case(R.id.rb3):
                            platform = "Lainnya";
                            break;
                    }
                    Log.d("Add notes", "plat " + platform);
                }

                int ps = rgStatus.getCheckedRadioButtonId();
                if(ps == -1){
                    Toast.makeText(EditNotes.this, "Pilih status", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Log.d("Main", "ps " + ps);
                    switch (ps){
                        case(R.id.rbLunas):
                            status = "Lunas";
                            break;
                        case(R.id.rbBelumLunas):
                            status = "Belum Lunas";
                            break;
                    }
                }
                Log.d("EditNotes", "status " + status);
                String namaPembeli = buyerName.getText().toString()+"";
                String hpPembeli = buyerPhone.getText().toString()+"";
                String alamatPembeli = buyerAddress.getText().toString()+"";
                String barangg = itemName.getText().toString()+"";
                String lol = etQty.getText().toString()+"";
                String hargaaa = etPrice.getText().toString()+"";
                String kurir = etKurir.getText().toString()+"";
                String dm = etDanaMasuk.getText().toString()+"";
                String nomi = etTarikRp.getText().toString()+"";
                String hargah = etHargaPemasok.getText().toString()+"";
                String cat = etNotes.getText().toString()+"";
                if(!invoice.equals("") && !namaPembeli.equals("") && !hpPembeli.equals("") && !alamatPembeli.equals("")
                        && !kurir.equals("") && !lol.equals("") && !hargaaa.equals("")
                        && !barangg.equals("") && !tglTrx.equals("")){
                    lQty = Integer.parseInt(lol);
                    lHarga = Integer.parseInt(hargaaa);
                    if(hargah.equals("")){
                        sHarga = 0;
                    }
                    else{
                        sHarga = Integer.parseInt(hargah);
                    }
                    if(dm.equals("")){
                        danaMasuk = 0;
                    }
                    else{
                        danaMasuk = Integer.parseInt(dm);
                    }
                    if(nomi.equals("")){
                        tarikRp = 0;
                    }
                    else{
                        tarikRp = Integer.parseInt(nomi);
                    }

                }
                else {
                    Toast.makeText(EditNotes.this, "Pastikan semua kolom terisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("EditNotes", "harga supplier " + hargah);
                NotesData notesData = new NotesData(selMonth, selYear, tglTrx, invoice, platform,
                        namaPembeli, hpPembeli, alamatPembeli, barangg, lQty, lHarga, pAsuransi,
                        pOngkir, kurir, tglKirim, tglSampai, tglSelesai, danaMasuk, tarikRp,
                        tglTarikSaldo, sHarga, sOngkir, status, cat);
                boolean isUpdated = dbHelper.updateData(notesData, id);
                if(isUpdated){
                    Toast.makeText(EditNotes.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    Intent toMain = new Intent(EditNotes.this, MainActivity.class);
                    startActivity(toMain);
                    finish();
                }
                else{
                    Toast.makeText(EditNotes.this, "Data gagal diperbarui", Toast.LENGTH_SHORT).show();
                }
//                dbHelper.deleteAll();

            }
        });
    }

    public void getMonthName(int month, Locale locale){
        selMonth = String.valueOf(month + 1);
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] toMonth = dfs.getMonths();
        String sMonth = toMonth[month];
        tvTrxSelMonth.setText(sMonth);
        tvTrxSelMonth.setTextColor(Color.RED);
    }

    public void getYear(int year){
        String sYear = String.valueOf(year);
        selYear = sYear;
        tvTrxSelYear.setText(sYear);
        tvTrxSelYear.setTextColor(Color.RED);
    }

    public void getData(String id){
        Cursor c = dbHelper.getSpecifiedData(id);
        if(c.moveToFirst()){
            etInvoiceNo.setText(c.getString(c.getColumnIndex(DBHelper.notesInvoice)));
            month = c.getInt(c.getColumnIndex(DBHelper.notesMonth))-1;
            getMonthName(month, locale);

            tvTrxSelYear.setText(c.getString(c.getColumnIndex(DBHelper.notesYear)));
            etTglTrx.setText(c.getString(c.getColumnIndex(DBHelper.notesTglTrx)));
            tglTrx = etTglTrx.getText().toString()+"";
            String a = c.getString(c.getColumnIndex(DBHelper.notesPlatform));
            if(a.equals("Tokopedia")){
                rgSelPlatform.check(R.id.rb1);
            }
            else if(a.equals("Bukalapak")){
                rgSelPlatform.check(R.id.rb2);
            }
            else{
                rgSelPlatform.check(R.id.rb3);
            }
            String b = c.getString(c.getColumnIndex(DBHelper.notesStatus));
            Log.d("EditNotes", "b " + b);
            if(b.equals("Lunas")){
                rgStatus.check(R.id.rbLunas);
            }
            else{
                rgStatus.check(R.id.rbBelumLunas);
            }
            buyerName.setText(c.getString(c.getColumnIndex(DBHelper.notesNamaPembeli)));
            buyerPhone.setText(c.getString(c.getColumnIndex(DBHelper.notesHpPembeli)));
            buyerAddress.setText(c.getString(c.getColumnIndex(DBHelper.notesAlamatPembeli)));
            itemName.setText(c.getString(c.getColumnIndex(DBHelper.notesBarang)));
            etQty.setText(c.getString(c.getColumnIndex(DBHelper.notesQty)));
            etPrice.setText(c.getString(c.getColumnIndex(DBHelper.notesPriceJual)));
            etAsuransi.setText(c.getString(c.getColumnIndex(DBHelper.notesAsuransi)));
            etOngkir.setText(c.getString(c.getColumnIndex(DBHelper.notesOngkirPenjual)));
            etKurir.setText(c.getString(c.getColumnIndex(DBHelper.notesKurir)));
            etTglKirim.setText(c.getString(c.getColumnIndex(DBHelper.notesTglKirim)));
            tglKirim = etTglKirim.getText().toString()+"";
            etTglSampai.setText(c.getString(c.getColumnIndex(DBHelper.notesTglSampai)));
            tglSampai = etTglSampai.getText().toString()+"";
            etTglSelesai.setText(c.getString(c.getColumnIndex(DBHelper.notesTglSelesai)));
            tglSelesai = etTglSelesai.getText().toString()+"";
            etDanaMasuk.setText(c.getString(c.getColumnIndex(DBHelper.notesDanaMasuk)));
            etTarikRp.setText(c.getString(c.getColumnIndex(DBHelper.notesNominalTarik)));
            etTglTarikSaldo.setText(c.getString(c.getColumnIndex(DBHelper.notesTglTarikSaldo)));
            tglTarikSaldo = etTglTarikSaldo.getText().toString()+"";
            etHargaPemasok.setText(c.getString(c.getColumnIndex(DBHelper.notesPriceSupplier)));
            ongkirPemasok.setText(c.getString(c.getColumnIndex(DBHelper.notesOngkirSupplier)));
            etNotes.setText(c.getString(c.getColumnIndex(DBHelper.notesCatatan)));
            int aa = c.getInt(c.getColumnIndex(DBHelper.notesQty));
            int ab = c.getInt(c.getColumnIndex(DBHelper.notesPriceJual));
            int ac = c.getInt(c.getColumnIndex(DBHelper.notesAsuransi));
            int ad = c.getInt(c.getColumnIndex(DBHelper.notesOngkirPenjual));
            int ae = aa * ab;
            int af = ae + ac + ad;
            tvHargaxQty.setText(aa + " x " + ab);
            tvResHargaxQty.setText(String.valueOf(ae));
            tvTotal.setText(String.valueOf(af));
            int ba = c.getInt(c.getColumnIndex(DBHelper.notesPriceSupplier));
            int bb = c.getInt(c.getColumnIndex(DBHelper.notesOngkirSupplier));
            int bc = aa * ba;
            int bd = bc + bb;
            pemasokHargaxQty.setText(aa + " x " + ba);
            tvResPemasok.setText(String.valueOf(bc));
            tvTotalPemasok.setText(String.valueOf(bd));
            Log.d("EditNotes", "aa " + aa);
        }
    }
}
