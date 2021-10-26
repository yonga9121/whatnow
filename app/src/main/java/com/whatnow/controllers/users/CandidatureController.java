package com.whatnow.controllers.users;

import com.whatnow.controllers.BaseController;
import com.whatnow.services.users.CandidatureService;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class CandidatureController extends BaseController {

    public static final CandidatureService candidatureService = retrofit.create(CandidatureService.class);
    public static final int ON_REVIEW = 0;
    public static final int ON_SELECTION = 1;
    public static final int ON_EVALUATION = 2;
    public static final int ON_SUCCESS = 100;
    public static final int ON_FAILED = 300;


    public static final Call<ResponseBody> onReview(String token, int page) {
        return candidatureService.index(token, ON_REVIEW, page);
    }

    public static final Call<ResponseBody> onSelection(String token, int page) {
        return candidatureService.index(token, ON_SELECTION, page);
    }

    public static final Call<ResponseBody> onEvaluation(String token, int page) {
        return candidatureService.index(token, ON_EVALUATION, page);
    }

    public static final Call<ResponseBody> onSuccess(String token, int page) {
        return candidatureService.index(token, ON_SUCCESS, page);
    }

    public static final Call<ResponseBody> onFailed(String token, int page) {
        return candidatureService.index(token, ON_FAILED, page);
    }
}
