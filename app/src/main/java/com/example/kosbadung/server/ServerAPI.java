package com.example.kosbadung.server;

public class ServerAPI {

    //Url
    public static final String URL = "192.168.100.110";

    //Phpnative
    public static final String URL_read_listkos ="http://"+URL+"/kosbadung/androidAPI/read_listkos.php";
    public static final String URL_read_listkamar ="http://"+URL+"/kosbadung/androidAPI/read_listkamar.php";
    public static final String URL_read_listcheckout ="http://"+URL+"/kosbadung/androidAPI/read_listcheckout.php";
    public static final String URL_read_listriwayat ="http://"+URL+"/kosbadung/androidAPI/read_listriwayat.php";
    public static  final String URL_login = "http://"+URL+"/kosbadung/androidAPI/login.php";
    public static  final String URL_read_detailkos = "http://"+URL+"/kosbadung/androidAPI/detail_kos.php";
    public static  final String URL_read_detailkamar = "http://"+URL+"/kosbadung/androidAPI/detail_kamar.php";
    public static  final String URL_transaksi_langsung = "http://"+URL+"/kosbadung/androidAPI/transaksi_langsung.php";
    public static  final String URL_bayar_pending = "http://"+URL+"/kosbadung/androidAPI/bayar_pending.php";
    public static  final String URL_transaksi_pending = "http://"+URL+"/kosbadung/androidAPI/transaksi_pending.php";
    public static  final String URL_register = "http://"+URL+"/kosbadung/androidAPI/register.php";

    // Image
    public static  final String URL_IMAGEKOS = "http://"+URL+"/kosbadung/androidAPI/Image/FotoKos/";
    public static  final String URL_IMAGEKAMAR = "http://"+URL+"/kosbadung/androidAPI/Image/FotoKamarKos/";
    public static  final String URL_IMAGEBUKTI = "http://"+URL+"/kosbadung/androidAPI/Image/BuktiPembayaran/";

}
