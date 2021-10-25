package com.whatnow.controllers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseController {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://whatnowback.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
