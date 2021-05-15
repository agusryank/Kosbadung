package com.example.kosbadung.Util;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.kosbadung.Network.ApiServices;
import com.example.kosbadung.Network.InitLibrary;
import com.example.kosbadung.adapter.ResponseGetKost;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetKostByDistricts {
    private static final String TAG = "GetKost";

    public interface Listener {
        void success(List<ResponseGetKost> response);
        void failed(String msg);
    }
    ApiServices api = InitLibrary.getInstance();
    public GetKostByDistricts(String kecamatan, Listener up) {
        Log.i(TAG, "GetKost: kecamatan : "+kecamatan);
        Call<List<ResponseGetKost>> call = api.get_by_districts(kecamatan);
        call.enqueue(new Callback<List<ResponseGetKost>>() {
            @Override
            public void onResponse(@NonNull Call<List<ResponseGetKost>> call, @NonNull Response<List<ResponseGetKost>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: "+response.raw());
                    up.success(response.body());
                } else {
                    try {
                        Log.e(TAG, "onResponse: error : "+response.message());
                        assert response.errorBody() != null;
                        up.failed(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ResponseGetKost>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                up.failed(t.getMessage());
            }
        });
    }
}
