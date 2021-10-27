package com.whatnow.controllers;

import com.whatnow.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseController {

    public static final Retrofit retrofit = new Retrofit.Builder()
            //.baseUrl(String.valueOf(R.string.UrlBackend)
            .baseUrl("https://whatnowback.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
