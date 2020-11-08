package com.example.ordernotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "OrderNotes";
    public static final int DB_VER = 2;

    public static final String TABLE_NOTES = "tableNotes";
    public static final String notesMonth = "bulan";
    public static final String notesYear = "tahun";
    public static final String notesTglTrx = "tanggalTransaksi";
    public static final String notesInvoice = "nomorInvoice";
    public static final String notesPlatform = "platform";
    public static final String notesNamaPembeli = "namaPembeli";
    public static final String notesHpPembeli = "hpPembeli";
    public static final String notesAlamatPembeli = "alamatPembeli";
    public static final String notesBarang = "namaBarang";
    public static final String notesQty = "qty";
    public static final String notesPriceJual = "hargaJual";
    public static final String notesAsuransi = "asuransi";
    public static final String notesOngkirPenjual = "ongkirPenjual";
    public static final String notesKurir = "kurir";
    public static final String notesTglKirim = "tglKirim";
    public static final String notesTglSampai = "tglSampai";
    public static final String notesTglSelesai = "tglSelesai";
    public static final String notesDanaMasuk = "danaMasuk";
    public static final String notesNominalTarik = "nominalTarik";
    public static final String notesTglTarikSaldo = "tglTarikSaldo";
    public static final String notesPriceSupplier = "hargaSupplier";
    public static final String notesOngkirSupplier = "ongkirSupplier";
    public static final String notesStatus = "status";
    public static final String notesCatatan = "catatanTambahan";

    public static final String createTableNotes = "CREATE TABLE " + TABLE_NOTES + " (" +
            notesMonth + " TEXT, " +
            notesYear + " TEXT, " +
            notesTglTrx + " TEXT, " +
            notesInvoice + " TEXT PRIMARY KEY, " +
            notesPlatform + " TEXT, " +
            notesNamaPembeli + " TEXT, " +
            notesHpPembeli + " TEXT, " +
            notesAlamatPembeli + " TEXT, " +
            notesBarang + " TEXT, " +
            notesQty + " INTEGER, " +
            notesPriceJual + " INTEGER, " +
            notesAsuransi + " INTEGER, " +
            notesOngkirPenjual + " INTEGER, " +
            notesKurir + " TEXT, " +
            notesTglKirim + " TEXT, " +
            notesTglSampai + " TEXT, " +
            notesTglSelesai + " TEXT, " +
            notesDanaMasuk + " INTEGER, " +
            notesNominalTarik + " INTEGER, " +
            notesTglTarikSaldo + " TEXT, " +
            notesPriceSupplier + " INTEGER, " +
            notesOngkirSupplier + " INTEGER, " +
            notesStatus + " TEXT, " +
            notesCatatan + " TEXT);";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableNotes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public boolean insertData(NotesData notesData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(notesMonth, notesData.getMonth());
        cv.put(notesYear, notesData.getYear());
        cv.put(notesTglTrx, notesData.getTglTrx());
        cv.put(notesInvoice, notesData.getInvoice());
        cv.put(notesPlatform, notesData.getPlatform());
        cv.put(notesNamaPembeli, notesData.getNamaPembeli());
        cv.put(notesHpPembeli, notesData.getHpPembeli());
        cv.put(notesAlamatPembeli, notesData.getAlamatPembeli());
        cv.put(notesBarang, notesData.getBarangYgDibeli());
        cv.put(notesQty, notesData.getQty());
        cv.put(notesPriceJual, notesData.getHarga());
        cv.put(notesAsuransi, notesData.getAsuransi());
        cv.put(notesOngkirPenjual, notesData.getOngkir());
        cv.put(notesKurir, notesData.getKurir());
        cv.put(notesTglKirim, notesData.getTglKirim());
        cv.put(notesTglSampai, notesData.getTglSampai());
        cv.put(notesTglSelesai, notesData.getTglSelesai());
        cv.put(notesDanaMasuk, notesData.getDanaMasuk());
        cv.put(notesNominalTarik, notesData.getNominalTarik());
        cv.put(notesTglTarikSaldo, notesData.getTglTarikSaldo());
        cv.put(notesPriceSupplier, notesData.getHargaPemasok());
        cv.put(notesOngkirSupplier, notesData.getOngkirPemasok());
        cv.put(notesStatus, notesData.getStatus());
        cv.put(notesCatatan, notesData.getCatatan());
        long result = db.insert(TABLE_NOTES, null, cv);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, null, null);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NOTES, null);
        return res;
    }

    public Cursor getSpecifiedData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = notesInvoice + " LIKE ?";
        String[] args = {id};
        Cursor res = db.query(TABLE_NOTES, null, selection, args, null, null, null);
        return res;
    }

    public boolean updateData(NotesData notesData, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(notesMonth, notesData.getMonth());
        cv.put(notesYear, notesData.getYear());
        cv.put(notesTglTrx, notesData.getTglTrx());
        cv.put(notesInvoice, notesData.getInvoice());
        cv.put(notesPlatform, notesData.getPlatform());
        cv.put(notesNamaPembeli, notesData.getNamaPembeli());
        cv.put(notesHpPembeli, notesData.getHpPembeli());
        cv.put(notesAlamatPembeli, notesData.getAlamatPembeli());
        cv.put(notesBarang, notesData.getBarangYgDibeli());
        cv.put(notesQty, notesData.getQty());
        cv.put(notesPriceJual, notesData.getHarga());
        cv.put(notesAsuransi, notesData.getAsuransi());
        cv.put(notesOngkirPenjual, notesData.getOngkir());
        cv.put(notesKurir, notesData.getKurir());
        cv.put(notesTglKirim, notesData.getTglKirim());
        cv.put(notesTglSampai, notesData.getTglSampai());
        cv.put(notesTglSelesai, notesData.getTglSelesai());
        cv.put(notesDanaMasuk, notesData.getDanaMasuk());
        cv.put(notesNominalTarik, notesData.getNominalTarik());
        cv.put(notesTglTarikSaldo, notesData.getTglTarikSaldo());
        cv.put(notesPriceSupplier, notesData.getHargaPemasok());
        cv.put(notesOngkirSupplier, notesData.getOngkirPemasok());
        cv.put(notesStatus, notesData.getStatus());
        cv.put(notesCatatan, notesData.getCatatan());
        String sel = notesInvoice + " LIKE ?";
        String[] args = {id};
        long result = db.update(TABLE_NOTES, cv, sel, args);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sel = notesInvoice + " LIKE ?";
        String[] args = {id};
        Integer result = db.delete(TABLE_NOTES, sel, args);
        return result;
    }
}
