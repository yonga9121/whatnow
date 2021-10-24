package com.whatnow.controllers.users;

import com.google.gson.Gson;
import com.whatnow.controllers.BaseController;
import com.whatnow.models.Session;
import com.whatnow.models.User;
import com.whatnow.services.users.UsersService;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersController extends BaseController {

    public static final UsersService usersService = retrofit.create(UsersService.class);

    public static final Call<ResponseBody> signup(String email, String password, String passwordConfirmation){
        JSONObject body = new JSONObject();
        JSONObject bodyParams = new JSONObject();
        try{
            bodyParams.put("email", email);
            bodyParams.put("password", password);
            bodyParams.put("password_confirmation", passwordConfirmation);
            body.put("user", bodyParams);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body.toString());
        return usersService.signup(requestBody);
    }

    public static final Call<ResponseBody> signin(String email, String password){
        JSONObject body = new JSONObject();
        try{
            body.put("email", email);
            body.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body.toString());
        return usersService.signin(requestBody);

    }
}
