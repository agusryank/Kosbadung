package com.example.kosbadung.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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
import com.example.kosbadung.MapsActivity;
import com.example.kosbadung.MapsKost;
import com.example.kosbadung.R;
import com.example.kosbadung.Util.GetKost;
import com.example.kosbadung.Util.GetLocation;
import com.example.kosbadung.adapter.AdapterListkos;
import com.example.kosbadung.adapter.ResponseGetKost;
import com.example.kosbadung.listkos.ListActivity;
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

    private static final String TAG = "HomeFragment";
    private CardView btn_list, btn_peta;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_home, container, false);

        btn_list = view.findViewById(R.id.btn_list);
        btn_peta = view.findViewById(R.id.btn_peta);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        btn_peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsKost.class);
                startActivity(intent);
            }
        });


        return view;
    }
}