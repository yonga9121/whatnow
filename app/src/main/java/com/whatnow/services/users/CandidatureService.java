package com.whatnow.services.users;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CandidatureService {

    @GET("api/users/candidatures")
    Call<ResponseBody> index(@Header("X-WHATNOW-TOKEN") String token, @Query("status_cd") int statusCd, @Query("page") int page);
}
