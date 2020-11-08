package com.example.ordernotes;

public class NotesViewedAtRV {
    String invoiceNo;
    String namaPembeli;
    String namaBarang;
    String platform;
    String tglOrder;
    String status;


    public NotesViewedAtRV(String invoiceNo, String namaPembeli, String namaBarang, String platform, String tglOrder, String status) {
        this.invoiceNo = invoiceNo;
        this.namaPembeli = namaPembeli;
        this.namaBarang = namaBarang;
        this.platform = platform;
        this.tglOrder = tglOrder;
        this.status = status;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTglOrder() {
        return tglOrder;
    }

    public void setTglOrder(String tglOrder) {
        this.tglOrder = tglOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
