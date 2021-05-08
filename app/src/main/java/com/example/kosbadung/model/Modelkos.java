package com.example.kosbadung.model;

public class Modelkos {
    String  id,Namakos,Namapemilik,Latitude,Longtitude,Jumlahkamar,foto1,foto2,foto3,Harga,Deskripsi,Kecamatan;

    public Modelkos(){}

    public Modelkos(String id, String namakos, String namapemilik, String latitude, String longtitude, String jumlahkamar, String foto1, String foto2, String foto3, String harga, String deskripsi, String kecamatan) {
        this.id = id;
        this.Namakos = namakos;
        this.Namapemilik = namapemilik;
        this.Latitude = latitude;
        this.Longtitude = longtitude;
        this.Jumlahkamar = jumlahkamar;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.Harga = harga;
        this.Deskripsi = deskripsi;
        this.Kecamatan = kecamatan;
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

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }

    public String getJumlahkamar() {
        return Jumlahkamar;
    }

    public void setJumlahkamar(String jumlahkamar) {
        Jumlahkamar = jumlahkamar;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getKecamatan() {
        return Kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }
}
