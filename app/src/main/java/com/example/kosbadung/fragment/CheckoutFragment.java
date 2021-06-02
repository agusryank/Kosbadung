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
import com.example.kosbadung.adapter.AdapterListkos;
import com.example.kosbadung.model.Modelkos;
import com.example.kosbadung.model.Modeltransaksi;
import com.example.kosbadung.server.AppController;
import com.example.kosbadung.server.ServerAPI;
import com.example.kosbadung.session.SessionManager;

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
public class CheckoutFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    SessionManager sessionManager;
    String nama;
    List<Modeltransaksi> mItems;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();

        nama = user.get(SessionManager.NAMA_USER);

        mRecyclerView = view.findViewById(R.id.listrcycler);
        mItems = new ArrayList<>();

        tampilcheckout();
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterListTransaksi(getContext(),mItems);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
    private void tampilcheckout(){

        StringRequest selectkos = new StringRequest(Request.Method.POST, ServerAPI.URL_read_listcheckout,
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
                                mk.setId_kos(data.getString("id_kos"));
                                mk.setNamakos(data.getString("Namakos"));
                                mk.setId_pemilik(data.getString("id_pemilik"));
                                mk.setNamapemilik(data.getString("Namapemilik"));
                                mk.setId_user(data.getString("id_user"));
                                mk.setNamauser(data.getString("Namauser"));
                                mk.setId_kamar(data.getString("id_kamar"));
                                mk.setNamakamar(data.getString("Namakamar"));
                                mk.setBuktipembayaran(data.getString("Buktipembayaran"));
                                mk.setTglmulai(data.getString("Tglmulai"));
                                mk.setLamasewa(data.getString("Lamasewa"));
                                mk.setJumlahkamar(data.getString("Jumlahkamar"));
                                mk.setTotalharga(data.getString("Totalharga"));
                                mk.setTgl_transaksi(data.getString("Tgl_transaksi"));
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
                params.put("nama", nama);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(selectkos);
    }
}