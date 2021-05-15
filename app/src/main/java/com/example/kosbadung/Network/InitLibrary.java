package com.example.kosbadung.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitLibrary {
    private static final Gson gson  = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit = null;
    public static Retrofit setInit(){
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.7/kosbadung/api/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
            return retrofit;
    }

    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
