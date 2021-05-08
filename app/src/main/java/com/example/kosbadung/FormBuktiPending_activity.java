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
    Button buttonimage,simpan;
    public static Bitmap bitmap_bukti;
    public static final int CAMERA_REQUEST_CODE  = 1;
    String foto_bukti="kosong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bukti_pending_activity);

        txt_harga = findViewById(R.id.txt_harga);
        txt_id = findViewById(R.id.txt_id);
        imagename = findViewById(R.id.imagename);
        imageView = findViewById(R.id.imageview);
        buttonimage = findViewById(R.id.buttonimage);
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

        buttonimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_data();
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

    private void update_data(){
        StringRequest insertData = new StringRequest(Request.Method.POST, ServerAPI.URL_bayar_pending, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "response insert to db : " + response.toString());
                try {
                    JSONObject data = new JSONObject(response);
                    String status_respon;
                    status_respon = data.getString("status");

                    if (status_respon.equals("berhasil")) {
                        Toast.makeText(FormBuktiPending_activity.this, "Berhasil Menyimpan Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FormBuktiPending_activity.this, BerandaActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d("volley", "error insert tb : "+response.toString());
                        Toast.makeText(FormBuktiPending_activity.this, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error insert tb : "+error.getMessage());
                Toast.makeText(FormBuktiPending_activity.this, "Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("namapenyewa",namapenyewa);
                params.put("bukti",foto_bukti);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(insertData);
    }
}