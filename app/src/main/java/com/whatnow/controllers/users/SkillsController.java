package com.whatnow.controllers.users;

import com.whatnow.controllers.BaseController;
import com.whatnow.services.users.SkillsService;
import com.whatnow.services.users.UsersService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillsController extends BaseController  {


    public static final SkillsService skillsService = retrofit.create(SkillsService.class);


    public static final Call<ResponseBody> index(String token, String term){
        return skillsService.index(token, term);
    }

    public static final Call<ResponseBody> softs(String token, String term){
        return skillsService.softs(token, term);
    }

}
