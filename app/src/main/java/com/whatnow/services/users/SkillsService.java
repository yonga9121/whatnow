package com.whatnow.services.users;

import com.whatnow.models.Skill;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SkillsService {

    @GET("api/users/skills")
    Call<ResponseBody> index(@Header("X-WHATNOW-TOKEN") String token, @Query("term") String term);
}
