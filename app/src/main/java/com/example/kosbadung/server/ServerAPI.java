package com.example.kosbadung.server;

public class ServerAPI {

    //Url
    public static final String URL = "kosbadung.xyz";

    //Phpnative
    public static final String URL_read_listkos ="https://"+URL+"/androidAPI/read_listkos.php";
    public static final String URL_read_listkos_by_kecamatan ="https://"+URL+"/androidAPI/read_listkos_by_kecamatan.php";
    public static final String URL_read_listkamar ="https://"+URL+"/androidAPI/read_listkamar.php";
    public static final String URL_read_listcheckout ="https://"+URL+"/androidAPI/read_listcheckout.php";
    public static final String URL_read_listriwayat ="https://"+URL+"/androidAPI/read_listriwayat.php";
    public static  final String URL_login = "https://"+URL+"/androidAPI/login.php";
    public static  final String URL_read_detailkos = "https://"+URL+"/androidAPI/detail_kos.php";
    public static  final String URL_read_detailkamar = "https://"+URL+"/androidAPI/detail_kamar.php";
    public static  final String URL_transaksi_langsung = "https://"+URL+"/androidAPI/transaksi_langsung.php";
    public static  final String URL_bayar_pending = "https://"+URL+"/androidAPI/bayar_pending.php";
    public static  final String URL_transaksi_pending = "https://"+URL+"/androidAPI/transaksi_pending.php";
    public static  final String URL_register = "https://"+URL+"/androidAPI/register.php";

    // Image
    public static  final String URL_IMAGEKOS = "https://"+URL+"/androidAPI/Image/FotoKos/";
    public static  final String URL_IMAGEKAMAR = "https://"+URL+"/androidAPI/Image/FotoKamarKos/";
    public static  final String URL_IMAGEBUKTI = "https://"+URL+"/androidAPI/Image/BuktiPembayaran/";

}
