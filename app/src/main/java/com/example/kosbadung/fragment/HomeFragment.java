package com.example.kosbadung.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kosbadung.R;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Spinner sp_kecamatan;
    private String[] list = {"Kuta Selatan","Kuta Utara","Kuta Tengah","Petang","Kuta"};
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Modelkos> mItems;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_home, container, false);
        sp_kecamatan = view.findViewById(R.id.sp_kecamatan);

        ArrayAdapter adapter_kecamatan= new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
        adapter_kecamatan.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_kecamatan.setAdapter(adapter_kecamatan);

        mRecyclerView = view.findViewById(R.id.listrcycler);
        mItems = new ArrayList<>();

        tampilkos();
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterListkos(getContext(),mItems);
        mRecyclerView.setAdapter(mAdapter);


        return view;
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
                                mk.setNamapemilik(data.getString("Namapemilik"));
                                mk.setLatitude(data.getString("Latitude"));
                                mk.setLongtitude(data.getString("Longtitude"));
                                mk.setJumlahkamar(data.getString("Jumlahkamar"));
                                mk.setFoto1(data.getString("foto1"));
                                mk.setFoto2(data.getString("foto2"));
                                mk.setFoto3(data.getString("foto3"));
                                mk.setHarga(data.getString("Harga"));
                                mk.setDeskripsi(data.getString("Deskripsi"));
                                mk.setKecamatan(data.getString("Kecamatan"));
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