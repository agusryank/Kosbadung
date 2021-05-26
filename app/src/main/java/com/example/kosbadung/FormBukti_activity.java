package com.example.kosbadung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kosbadung.dialog.CameraActivity;
import com.example.kosbadung.dialog.GaleryActivity;
import com.example.kosbadung.server.AppController;
import com.example.kosbadung.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FormBukti_activity extends AppCompatActivity {

    String id,id_kos,id_pemilik,id_penyewa,namakos,namapemilik,namapenyewa,namakamar,jumlahkamar,hargakos,tgl_kos,lamasewa;
    TextView txt_jumlahkamar,txt_harga;
    TextView imagename;
    ImageView imageView;
    Button buttoncamera,buttongaleri,simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bukti);
        txt_harga = findViewById(R.id.txt_harga);
        txt_jumlahkamar = findViewById(R.id.txt_jumlahkamar);
        imagename = findViewById(R.id.imagename);
        imageView = findViewById(R.id.imageview);
        buttoncamera = findViewById(R.id.buttoncamera);
        buttongaleri = findViewById(R.id.buttongalery);
        simpan = findViewById(R.id.simpan);

        jumlahkamar = getIntent().getStringExtra("jumlahkamar");
        txt_jumlahkamar.setText(jumlahkamar);
        hargakos = getIntent().getStringExtra("hargakos");
        txt_harga.setText(hargakos);
        id = getIntent().getStringExtra("id");
        id_kos = getIntent().getStringExtra("id_kos");
        id_pemilik = getIntent().getStringExtra("id_pemilik");
        id_penyewa = getIntent().getStringExtra("id_penyewa");
        namakamar = getIntent().getStringExtra("namakamar");
        namakos = getIntent().getStringExtra("namakos");
        namapemilik = getIntent().getStringExtra("namapemilik");
        namapenyewa = getIntent().getStringExtra("namapenyewa");
        tgl_kos = getIntent().getStringExtra("tgl_kos");
        lamasewa = getIntent().getStringExtra("lamasewa");

        buttoncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraActivity cameraActivity = new CameraActivity();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("id_kos", id_kos);
                bundle.putString("id_pemilik", id_pemilik);
                bundle.putString("id_penyewa", id_penyewa);
                bundle.putString("namakamar", namakamar);
                bundle.putString("namakos", namakos);
                bundle.putString("namapemilik", namapemilik);
                bundle.putString("namapenyewa", namapenyewa);
                bundle.putString("tgl_kos", tgl_kos);
                bundle.putString("lamasewa", lamasewa);
                bundle.putString("jumlahkamar", jumlahkamar);
                bundle.putString("hargakos", hargakos);
                cameraActivity.show(getSupportFragmentManager(), "example dialog");

            }
        });

        buttongaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GaleryActivity galeryActivity = new GaleryActivity();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("id_kos", id_kos);
                bundle.putString("id_pemilik", id_pemilik);
                bundle.putString("id_penyewa", id_penyewa);
                bundle.putString("namakamar", namakamar);
                bundle.putString("namakos", namakos);
                bundle.putString("namapemilik", namapemilik);
                bundle.putString("namapenyewa", namapenyewa);
                bundle.putString("tgl_kos", tgl_kos);
                bundle.putString("lamasewa", lamasewa);
                bundle.putString("jumlahkamar", jumlahkamar);
                bundle.putString("hargakos", hargakos);
                galeryActivity.show(getSupportFragmentManager(), "example dialog");

            }
        });
    }
}