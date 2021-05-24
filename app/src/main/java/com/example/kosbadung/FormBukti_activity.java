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
import com.example.kosbadung.server.AppController;
import com.example.kosbadung.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FormBukti_activity extends AppCompatActivity {

    String id,namakos,namapemilik,namapenyewa,jumlahkamar,hargakos,tgl_kos,lamasewa;
    TextView txt_jumlahkamar,txt_harga;
    TextView imagename;
    ImageView imageView;
    Button buttoncamera,buttongaleri,simpan;
    public static Bitmap bitmap_bukti;
    private static final int IMAGE_PICK_CODE = 1;
    private static final int PERMISSION_CODE = 2;
    public static final int CAMERA_REQUEST_CODE  = 1;
    Uri imgaeuri;
    String foto_bukti="kosong";


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
        namakos = getIntent().getStringExtra("namakos");
        namapemilik = getIntent().getStringExtra("namapemilik");
        namapenyewa = getIntent().getStringExtra("namapenyewa");
        jumlahkamar = getIntent().getStringExtra("jumlahkamar");
        tgl_kos = getIntent().getStringExtra("tgl_kos");
        lamasewa = getIntent().getStringExtra("lamasewa");

        buttoncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
            }
        });

        buttongaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(FormBukti_activity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        ImagePick();
                    }
                }
                else{
                    ImagePick();
                }
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan_data();
            }
        });
    }
    private void BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
        byte[]arr=baos.toByteArray();
        foto_bukti = Base64.encodeToString(arr,Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case(CAMERA_REQUEST_CODE) :
                if(resultCode == Activity.RESULT_OK){
                    bitmap_bukti = (Bitmap) data.getExtras().get("data");
                    BitMapToString(bitmap_bukti);
                    imageView.setImageBitmap(bitmap_bukti);
                    imagename.setText("Upload Image Sucess");
                }
                break;
        }
    }

    private void simpan_data(){
        StringRequest insertData = new StringRequest(Request.Method.POST, ServerAPI.URL_transaksi_langsung, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "response insert to db : " + response.toString());
                try {
                    JSONObject data = new JSONObject(response);
                    String status_respon;
                    status_respon = data.getString("status");

                    if (status_respon.equals("berhasil")) {
                        Toast.makeText(FormBukti_activity.this, "Berhasil Menyimpan Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FormBukti_activity.this, BerandaActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d("volley", "error insert tb : "+response.toString());
                        Toast.makeText(FormBukti_activity.this, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error insert tb : "+error.getMessage());
                Toast.makeText(FormBukti_activity.this, "Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
            Map<String, String> params = new HashMap<>();
            params.put("id",id);
            params.put("namakos",namakos);
            params.put("namapemilik",namapemilik);
            params.put("namapenyewa",namapenyewa);
            params.put("bukti",foto_bukti);
            params.put("tgl_kos",tgl_kos);
            params.put("lamasewa",lamasewa);
            params.put("jumlahkamar",jumlahkamar);
            params.put("hargakos",hargakos);
            return params;
            }
        };
        AppController.getInstance().addToRequestQueue(insertData);
    }
    private void ImagePick(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ImagePick();
                } else {
                    Toast.makeText(FormBukti_activity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}