package com.example.kosbadung.model;

public class Modeltransaksi {

    String  id,Namakos,Namapemilik,Namauser,Buktipembayaran,Tglmulai,Lamasewa,Jumlahkamar,Totalharga,Status,Pesan;

    public Modeltransaksi(){}

    public Modeltransaksi(String id, String namakos, String namapemilik, String namauser, String buktipembayaran, String tglmulai, String lamasewa, String jumlahkamar, String totalharga, String status, String pesan) {
        this.id = id;
        Namakos = namakos;
        Namapemilik = namapemilik;
        Namauser = namauser;
        Buktipembayaran = buktipembayaran;
        Tglmulai = tglmulai;
        Lamasewa = lamasewa;
        Jumlahkamar = jumlahkamar;
        Totalharga = totalharga;
        Status = status;
        Pesan = pesan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamakos() {
        return Namakos;
    }

    public void setNamakos(String namakos) {
        Namakos = namakos;
    }

    public String getNamapemilik() {
        return Namapemilik;
    }

    public void setNamapemilik(String namapemilik) {
        Namapemilik = namapemilik;
    }

    public String getNamauser() {
        return Namauser;
    }

    public void setNamauser(String namauser) {
        Namauser = namauser;
    }

    public String getBuktipembayaran() {
        return Buktipembayaran;
    }

    public void setBuktipembayaran(String buktipembayaran) {
        Buktipembayaran = buktipembayaran;
    }

    public String getTglmulai() {
        return Tglmulai;
    }

    public void setTglmulai(String tglmulai) {
        Tglmulai = tglmulai;
    }

    public String getLamasewa() {
        return Lamasewa;
    }

    public void setLamasewa(String lamasewa) {
        Lamasewa = lamasewa;
    }

    public String getJumlahkamar() {
        return Jumlahkamar;
    }

    public void setJumlahkamar(String jumlahkamar) {
        Jumlahkamar = jumlahkamar;
    }

    public String getTotalharga() {
        return Totalharga;
    }

    public void setTotalharga(String totalharga) {
        Totalharga = totalharga;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }
}
