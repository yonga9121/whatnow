package com.whatnow.services.users;

import com.whatnow.models.Session;
import com.whatnow.models.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersService {

    @POST("api/users/signup")
    Call<ResponseBody> signup(@Body RequestBody requestBody);

}
