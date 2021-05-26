package com.example.kosbadung;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.kosbadung.dialog.CameraDialog;
import com.example.kosbadung.dialog.GaleryActivity;
import com.example.kosbadung.dialog.GaleryDialog;
import com.example.kosbadung.server.AppController;
import com.example.kosbadung.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FormBuktiPending_activity extends AppCompatActivity {

    String id,namakos,namapemilik,namapenyewa,jumlahkamar,hargakos,tgl_kos,lamasewa;
    TextView txt_id,txt_harga;
    TextView imagename;
    ImageView imageView;
    Button buttoncamera,buttongaleri,simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bukti_pending_activity);

        txt_harga = findViewById(R.id.txt_harga);
        txt_id = findViewById(R.id.txt_id);
        imagename = findViewById(R.id.imagename);
        imageView = findViewById(R.id.imageview);
        buttoncamera = findViewById(R.id.buttoncamera);
        buttongaleri = findViewById(R.id.buttongalery);
        simpan = findViewById(R.id.simpan);

        id = getIntent().getStringExtra("id");
        txt_id.setText(id);
        hargakos = getIntent().getStringExtra("hargakos");
        txt_harga.setText(hargakos);
        namakos = getIntent().getStringExtra("namakos");
        namapemilik = getIntent().getStringExtra("namapemilik");
        namapenyewa = getIntent().getStringExtra("namapenyewa");
        jumlahkamar = getIntent().getStringExtra("jumlahkamar");
        tgl_kos = getIntent().getStringExtra("tgl_kos");
        lamasewa = getIntent().getStringExtra("lamasewa");

        buttoncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraDialog cameraDialog = new CameraDialog();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("namapenyewa", namapenyewa);
                cameraDialog.show(getSupportFragmentManager(), "example dialog");
            }
        });

        buttongaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GaleryDialog galeryDialog = new GaleryDialog();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("namapenyewa", namapenyewa);
                galeryDialog.show(getSupportFragmentManager(), "example dialog");
            }
        });
    }

}