package com.example.ordernotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
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

public class AddNotes extends AppCompatActivity {
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
    Button btnAdd;
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
    String barang = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
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
        btnAdd = findViewById(R.id.btnAdd);
        dbHelper = new DBHelper(this);
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        locale = new Locale("in", "INDONESIA");
        getMonthName(month, locale);
        getYear(year);





        tvTrxSelMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(AddNotes.this, new MonthPickerDialog.OnDateSetListener() {
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
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(AddNotes.this, new MonthPickerDialog.OnDateSetListener() {
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
                    Toast.makeText(AddNotes.this, "Masukkan jumlah barang", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(s.toString().equals("")){
                    Toast.makeText(AddNotes.this, "Masukkan harga barang", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddNotes.this, "Masukkan harga barang", Toast.LENGTH_SHORT).show();
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNotes.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNotes.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNotes.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNotes.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNotes.this, new DatePickerDialog.OnDateSetListener() {
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String invoice = etInvoiceNo.getText().toString()+"";
                int pc = rgSelPlatform.getCheckedRadioButtonId();
                if(pc == -1){
                    Toast.makeText(AddNotes.this, "Pilih platform", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddNotes.this, "Pilih status", Toast.LENGTH_SHORT).show();
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
                Log.d("AddNotes", "ps " + ps);
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
//                Toast.makeText(AddNotes.this, "tgl kirim " + tglKirim, Toast.LENGTH_SHORT).show();
                if(!invoice.equals("") && !namaPembeli.equals("") && !hpPembeli.equals("") && !alamatPembeli.equals("")
                        && !kurir.equals("") && !lol.equals("") && !hargaaa.equals("")
                        && !barangg.equals("") && !tglTrx.equals("")){
                    lQty = Integer.parseInt(lol);
                    lHarga = Integer.parseInt(hargaaa);
                    if(hargah.equals("") || dm.equals("") || nomi.equals("")){
                        sHarga = 0;
                        danaMasuk = 0;
                        tarikRp = 0;
                    }
                    else{
                        sHarga = Integer.parseInt(hargah);
                        danaMasuk = Integer.parseInt(dm);
                        tarikRp = Integer.parseInt(nomi);
                    }


                }
                else {
                    Toast.makeText(AddNotes.this, "Pastikan semua kolom terisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("Main", "nama pembeli " + namaPembeli);
                NotesData notesData = new NotesData(selMonth, selYear, tglTrx, invoice, platform,
                        namaPembeli, hpPembeli, alamatPembeli, barangg, lQty, lHarga, pAsuransi,
                        pOngkir, kurir, tglKirim, tglSampai, tglSelesai, danaMasuk, tarikRp,
                        tglTarikSaldo, sHarga, sOngkir, status, cat);
//                NotesDataDB.notesData.add(notesData);
                boolean isAdded = dbHelper.insertData(notesData);
                if(isAdded){
                    Toast.makeText(AddNotes.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddNotes.this, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                }
//                dbHelper.deleteAll();
                clearFills();
                Intent toMain = new Intent(AddNotes.this, MainActivity.class);
                startActivity(toMain);
                finish();

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

    public void clearFills(){
        etTglTrx.getText().clear();
        etInvoiceNo.getText().clear();
        buyerName.getText().clear();
        buyerPhone.getText().clear();
        buyerAddress.getText().clear();
        itemName.getText().clear();
        tvTotal.setText("");
        etQty.setText("0");
        etPrice.setText("0");
        tvHargaxQty.setText("Qty x Harga");
        tvResHargaxQty.setText("");
        etAsuransi.setText("0");
        etOngkir.setText("0");
        etKurir.getText().clear();
        etTglTarikSaldo.getText().clear();
        etTglSelesai.getText().clear();
        etTglSampai.getText().clear();
        etTglKirim.getText().clear();
        etDanaMasuk.getText().clear();
        etTarikRp.getText().clear();
        tvTotalPemasok.setText("");
        tvResPemasok.setText("");
        pemasokHargaxQty.setText("Qty x Harga");
        etHargaPemasok.setText("0");
        ongkirPemasok.setText("0");
        etNotes.getText().clear();
        rgSelPlatform.clearCheck();
        rgStatus.clearCheck();
    }
}
