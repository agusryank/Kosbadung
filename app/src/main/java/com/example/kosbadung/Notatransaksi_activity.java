package com.example.kosbadung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kosbadung.server.ServerAPI;

public class Notatransaksi_activity extends AppCompatActivity {

    TextView id_transaksi, nama_kos, pemilik_kos, lama_sewa, total_pembayaran,tv_image;
    String id,namakos,pemilikkos,lamasewa,totalpembayaran,image;
    ImageView img_bukti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notatransaksi);

        id_transaksi = findViewById(R.id.id_transaksi);
        nama_kos = findViewById(R.id.nama_kos);
        pemilik_kos = findViewById(R.id.pemilik_kos);
        lama_sewa = findViewById(R.id.lama_sewa);
        total_pembayaran = findViewById(R.id.total_pembayaran);
        img_bukti = findViewById(R.id.img_bukti);
        tv_image = findViewById(R.id.tv_image);

        id = getIntent().getStringExtra("id");
        id_transaksi.setText(id);
        namakos = getIntent().getStringExtra("namakos");
        nama_kos.setText(namakos);
        pemilikkos = getIntent().getStringExtra("namapemilik");
        pemilik_kos.setText(pemilikkos);
        lamasewa = getIntent().getStringExtra("lamasewa");
        lama_sewa.setText(lamasewa);
        totalpembayaran = getIntent().getStringExtra("hargakos");
        total_pembayaran.setText(totalpembayaran);
        image = getIntent().getStringExtra("bukti");

        String img = ServerAPI.URL_IMAGEBUKTI + image;
        Glide.with(Notatransaksi_activity.this).load(img).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(img_bukti);

    }
}