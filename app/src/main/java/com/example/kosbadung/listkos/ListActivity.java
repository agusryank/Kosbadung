package com.example.kosbadung.listkos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kosbadung.R;
import com.example.kosbadung.ResultKos;
import com.example.kosbadung.adapter.AdapterListkos;
import com.example.kosbadung.model.Modelkos;
import com.example.kosbadung.server.AppController;
import com.example.kosbadung.server.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    private Spinner sp_kecamatan;
    private String[] list = {"Semua","Kuta Selatan","Kuta Utara","Petang","Abiansemal","Kuta","Mengwi"};
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Modelkos> mItems;
    TextView tv_kecamatan;
    String Skecamatan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        sp_kecamatan = findViewById(R.id.sp_kecamatan);
        tv_kecamatan = findViewById(R.id.tv_kecamatan);
        pd = new ProgressDialog(ListActivity.this);
        ArrayAdapter adapter_kecamatan= new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter_kecamatan.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_kecamatan.setAdapter(adapter_kecamatan);

        mRecyclerView = findViewById(R.id.listrcycler);
        mItems = new ArrayList<>();

        tampilkos();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterListkos(this,mItems);
        mRecyclerView.setAdapter(mAdapter);

        sp_kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_kecamatan.setText(list[i]);
                Skecamatan = tv_kecamatan.getText().toString().trim();
                if(tv_kecamatan.getText().toString().equals("Kuta Selatan")) {
                    pd.setMessage("Memuat Data Kos" + Skecamatan);
                    pd.setCancelable(false);
                    pd.show();
                    Intent intent = new Intent(ListActivity.this, ResultKos.class);
                    intent.putExtra("kecamatan",Skecamatan);
                    startActivity(intent);
                    finish();
                }else if (tv_kecamatan.getText().toString().equals("Kuta Utara")){
                    pd.setMessage("Memuat Data Kos"+Skecamatan);
                    pd.setCancelable(false);
                    pd.show();
                    Intent intent = new Intent(ListActivity.this, ResultKos.class);
                    intent.putExtra("kecamatan",Skecamatan);
                    startActivity(intent);
                    finish();
                }else if (tv_kecamatan.getText().toString().equals("Petang")){
                    pd.setMessage("Memuat Data Kos"+Skecamatan);
                    pd.setCancelable(false);
                    pd.show();
                    Intent intent = new Intent(ListActivity.this, ResultKos.class);
                    intent.putExtra("kecamatan",Skecamatan);
                    startActivity(intent);
                    finish();
                }else if (tv_kecamatan.getText().toString().equals("Abiansemal")){
                    pd.setMessage("Memuat Data Kos"+Skecamatan);
                    pd.setCancelable(false);
                    pd.show();
                    Intent intent = new Intent(ListActivity.this, ResultKos.class);
                    intent.putExtra("kecamatan",Skecamatan);
                    startActivity(intent);
                    finish();
                }else if (tv_kecamatan.getText().toString().equals("Kuta")){
                    pd.setMessage("Memuat Data Kos"+Skecamatan);
                    pd.setCancelable(false);
                    pd.show();
                    Intent intent = new Intent(ListActivity.this, ResultKos.class);
                    intent.putExtra("kecamatan",Skecamatan);
                    startActivity(intent);
                    finish();
                }else if (tv_kecamatan.getText().toString().equals("Mengwi")){
                    pd.setMessage("Memuat Data Kos"+Skecamatan);
                    pd.setCancelable(false);
                    pd.show();
                    Intent intent = new Intent(ListActivity.this, ResultKos.class);
                    intent.putExtra("kecamatan",Skecamatan);
                    startActivity(intent);
                    finish();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv_kecamatan.setText("Semua");
            }
        });
    }

    private void tampilkos(){

        StringRequest selectkos = new StringRequest(Request.Method.POST, ServerAPI.URL_read_listkos,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("volley", "response" +response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONArray array = new JSONArray(response);
                                JSONObject data = array.getJSONObject(i);

                                Modelkos mk = new Modelkos();
                                mk.setId(data.getString("id"));
                                mk.setNamakos(data.getString("Namakos"));
                                mk.setId_pemilik(data.getString("id_pemilik"));
                                mk.setNamapemilik(data.getString("Namapemilik"));
                                mk.setNo_telp(data.getString("No_telp"));
                                mk.setLatitude(data.getString("Latitude"));
                                mk.setLongtitude(data.getString("Longtitude"));
                                mk.setFoto1(data.getString("foto1"));
                                mk.setFoto2(data.getString("foto2"));
                                mk.setFoto3(data.getString("foto3"));
                                mk.setFoto4(data.getString("foto4"));
                                mk.setDeskripsi(data.getString("Deskripsi"));
                                mk.setKecamatan(data.getString("Kecamatan"));
                                mk.setStatus(data.getString("Status"));
                                mk.setAktif(data.getString("Aktif"));
                                mItems.add(mk);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error : " +error.getMessage());
                Toast.makeText(ListActivity.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(selectkos);
    }
}