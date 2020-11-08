package com.example.ordernotes;

public class NotesData {
    String month;
    String year;
    String tglTrx;
    String invoice;
    String platform;
    String namaPembeli;
    String hpPembeli;
    String alamatPembeli;
    String barangYgDibeli;
    int qty;
    int harga;
    int asuransi;
    int ongkir;
    String kurir;
    String tglKirim;
    String tglSampai;
    String tglSelesai;
    int danaMasuk;
    int nominalTarik;
    String tglTarikSaldo;
    int hargaPemasok;
    int ongkirPemasok;
    String status;
    String catatan;

    public NotesData(String month, String year, String tglTrx, String invoice, String platform, String namaPembeli, String hpPembeli, String alamatPembeli, String barangYgDibeli, int qty, int harga, int asuransi, int ongkir, String kurir, String tglKirim, String tglSampai, String tglSelesai, int danaMasuk, int nominalTarik, String tglTarikSaldo, int hargaPemasok, int ongkirPemasok, String status, String catatan) {
        this.month = month;
        this.year = year;
        this.tglTrx = tglTrx;
        this.invoice = invoice;
        this.platform = platform;
        this.namaPembeli = namaPembeli;
        this.hpPembeli = hpPembeli;
        this.alamatPembeli = alamatPembeli;
        this.barangYgDibeli = barangYgDibeli;
        this.qty = qty;
        this.harga = harga;
        this.asuransi = asuransi;
        this.ongkir = ongkir;
        this.kurir = kurir;
        this.tglKirim = tglKirim;
        this.tglSampai = tglSampai;
        this.tglSelesai = tglSelesai;
        this.danaMasuk = danaMasuk;
        this.nominalTarik = nominalTarik;
        this.tglTarikSaldo = tglTarikSaldo;
        this.hargaPemasok = hargaPemasok;
        this.ongkirPemasok = ongkirPemasok;
        this.status = status;
        this.catatan = catatan;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTglTrx() {
        return tglTrx;
    }

    public void setTglTrx(String tglTrx) {
        this.tglTrx = tglTrx;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getHpPembeli() {
        return hpPembeli;
    }

    public void setHpPembeli(String hpPembeli) {
        this.hpPembeli = hpPembeli;
    }

    public String getAlamatPembeli() {
        return alamatPembeli;
    }

    public void setAlamatPembeli(String alamatPembeli) {
        this.alamatPembeli = alamatPembeli;
    }

    public String getBarangYgDibeli() {
        return barangYgDibeli;
    }

    public void setBarangYgDibeli(String barangYgDibeli) {
        this.barangYgDibeli = barangYgDibeli;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(int asuransi) {
        this.asuransi = asuransi;
    }

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public String getTglKirim() {
        return tglKirim;
    }

    public void setTglKirim(String tglKirim) {
        this.tglKirim = tglKirim;
    }

    public String getTglSampai() {
        return tglSampai;
    }

    public void setTglSampai(String tglSampai) {
        this.tglSampai = tglSampai;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public int getDanaMasuk() {
        return danaMasuk;
    }

    public void setDanaMasuk(int danaMasuk) {
        this.danaMasuk = danaMasuk;
    }

    public int getNominalTarik() {
        return nominalTarik;
    }

    public void setNominalTarik(int nominalTarik) {
        this.nominalTarik = nominalTarik;
    }

    public String getTglTarikSaldo() {
        return tglTarikSaldo;
    }

    public void setTglTarikSaldo(String tglTarikSaldo) {
        this.tglTarikSaldo = tglTarikSaldo;
    }

    public int getHargaPemasok() {
        return hargaPemasok;
    }

    public void setHargaPemasok(int hargaPemasok) {
        this.hargaPemasok = hargaPemasok;
    }

    public int getOngkirPemasok() {
        return ongkirPemasok;
    }

    public void setOngkirPemasok(int ongkirPemasok) {
        this.ongkirPemasok = ongkirPemasok;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
