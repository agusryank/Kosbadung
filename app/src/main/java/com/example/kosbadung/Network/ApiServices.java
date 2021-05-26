package com.example.kosbadung.Network;
import com.example.kosbadung.adapter.ResponseGetKost;
import com.example.kosbadung.adapter.ResponseListKost;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @FormUrlEncoded
    @POST("getArroundKost")
    Call<List<ResponseGetKost>> get_kost(
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("radius") int radius
    );
    @FormUrlEncoded
    @POST("getKostByDistricts")
    Call<List<ResponseGetKost>> get_by_districts(
            @Field("kecamatan") String kecamatan
    );
}
