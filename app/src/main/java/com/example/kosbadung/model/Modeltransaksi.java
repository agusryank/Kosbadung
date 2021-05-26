package com.example.kosbadung.model;

public class Modeltransaksi {

    String id,id_namakos,Namakos,id_namapemilik,Namapemilik,id_namauser,Namauser,id_namakamar,Namakamar,Buktipembayaran,Tglmulai,Lamasewa,Jumlahkamar,Totalharga,Status;

    public Modeltransaksi(){}

    public Modeltransaksi(String id, String id_namakos, String namakos, String id_namapemilik, String namapemilik, String id_namauser, String namauser, String id_namakamar, String namakamar, String buktipembayaran, String tglmulai, String lamasewa, String jumlahkamar, String totalharga, String status) {
        this.id = id;
        this.id_namakos = id_namakos;
        this.Namakos = namakos;
        this.id_namapemilik = id_namapemilik;
        this.Namapemilik = namapemilik;
        this.id_namauser = id_namauser;
        this.Namauser = namauser;
        this.id_namakamar = id_namakamar;
        this.Namakamar = namakamar;
        this.Buktipembayaran = buktipembayaran;
        this.Tglmulai = tglmulai;
        this.Lamasewa = lamasewa;
        this.Jumlahkamar = jumlahkamar;
        this.Totalharga = totalharga;
        this.Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_namakos() {
        return id_namakos;
    }

    public void setId_namakos(String id_namakos) {
        this.id_namakos = id_namakos;
    }

    public String getNamakos() {
        return Namakos;
    }

    public void setNamakos(String namakos) {
        Namakos = namakos;
    }

    public String getId_namapemilik() {
        return id_namapemilik;
    }

    public void setId_namapemilik(String id_namapemilik) {
        this.id_namapemilik = id_namapemilik;
    }

    public String getNamapemilik() {
        return Namapemilik;
    }

    public void setNamapemilik(String namapemilik) {
        Namapemilik = namapemilik;
    }

    public String getId_namauser() {
        return id_namauser;
    }

    public void setId_namauser(String id_namauser) {
        this.id_namauser = id_namauser;
    }

    public String getNamauser() {
        return Namauser;
    }

    public void setNamauser(String namauser) {
        Namauser = namauser;
    }

    public String getId_namakamar() {
        return id_namakamar;
    }

    public void setId_namakamar(String id_namakamar) {
        this.id_namakamar = id_namakamar;
    }

    public String getNamakamar() {
        return Namakamar;
    }

    public void setNamakamar(String namakamar) {
        Namakamar = namakamar;
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
}
