package com.example.kosbadung.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kosbadung.R;
import com.example.kosbadung.adapter.AdapterListTransaksi;
import com.example.kosbadung.adapter.AdapterTransaksiSukses;
import com.example.kosbadung.model.Modeltransaksi;
import com.example.kosbadung.server.AppController;
import com.example.kosbadung.server.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Modeltransaksi> mItems;

    public RiwayatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_riwayat, container, false);

        mRecyclerView = view.findViewById(R.id.listrcycler);
        mItems = new ArrayList<>();

        tampilriwayat();
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterTransaksiSukses(getContext(),mItems);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
    private void tampilriwayat(){

        StringRequest selectkos = new StringRequest(Request.Method.POST, ServerAPI.URL_read_listriwayat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("volley", "response" +response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONArray array = new JSONArray(response);
                                JSONObject data = array.getJSONObject(i);

                                Modeltransaksi mk = new Modeltransaksi();
                                mk.setId(data.getString("id"));
                                mk.setId_namakos(data.getString("id_namakos"));
                                mk.setNamakos(data.getString("Namakos"));
                                mk.setId_namapemilik(data.getString("id_namapemilik"));
                                mk.setNamapemilik(data.getString("Namapemilik"));
                                mk.setId_namauser(data.getString("id_namauser"));
                                mk.setNamauser(data.getString("Namauser"));
                                mk.setId_namakamar(data.getString("id_namakamar"));
                                mk.setNamakamar(data.getString("Namakamar"));
                                mk.setBuktipembayaran(data.getString("Buktipembayaran"));
                                mk.setTglmulai(data.getString("Tglmulai"));
                                mk.setLamasewa(data.getString("Lamasewa"));
                                mk.setJumlahkamar(data.getString("Jumlahkamar"));
                                mk.setTotalharga(data.getString("Totalharga"));
                                mk.setStatus(data.getString("Status"));
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
                Toast.makeText(getContext(), "Gagal Memuat", Toast.LENGTH_SHORT).show();
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