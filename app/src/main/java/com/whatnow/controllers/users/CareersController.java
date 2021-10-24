package com.whatnow.controllers.users;

import com.whatnow.controllers.BaseController;
import com.whatnow.services.users.CareersService;
import com.whatnow.services.users.SkillsService;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class CareersController extends BaseController {

    public static final CareersService careersService = retrofit.create(CareersService.class);

    public static final Call<ResponseBody> index(String token, String term){
        return careersService.index(token, term);
    }
}
