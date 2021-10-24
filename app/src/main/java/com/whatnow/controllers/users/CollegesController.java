package com.whatnow.controllers.users;

import com.whatnow.controllers.BaseController;
import com.whatnow.services.users.CollegesService;
import com.whatnow.services.users.SkillsService;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class CollegesController extends BaseController {

    public static final CollegesService collegesService= retrofit.create(CollegesService.class);

    public static final Call<ResponseBody> index(String token, String term){
        return collegesService.index(token, term);
    }
}
