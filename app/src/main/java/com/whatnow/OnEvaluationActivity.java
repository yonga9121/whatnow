package com.whatnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.whatnow.controllers.users.CandidatureController;
import com.whatnow.models.Candidature;
import com.whatnow.ui.OnSwipeTouchListener;
import com.whatnow.ui.adapters.CandidatureItemAdapter;
import com.whatnow.utils.Utils;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnEvaluationActivity extends AppCompatActivity {
    private String token;
    private int page = 1;
    private ArrayList<Candidature> candidatures = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ConstraintLayout layout;
        token = Utils.getString("token", getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_evaluation);
        layout = findViewById(R.id.containerOnEvaluation);
        ListView list = findViewById(R.id.candidaturesListOnEvaluation);
        list.setOnTouchListener(new OnSwipeTouchListener(OnEvaluationActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                System.out.println("SWIPE LEFT");
                Intent intent = new Intent(getBaseContext(), OnSelectionActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                System.out.println("SWIPE RIGHT");
                Intent intent = new Intent(getBaseContext(),OnSuccessActivity.class);
                startActivity(intent);
                finish();
            }
        });
        layout.setOnTouchListener(new OnSwipeTouchListener(OnEvaluationActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                System.out.println("SWIPE LEFT");
                Intent intent = new Intent(getBaseContext(), OnSelectionActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                System.out.println("SWIPE RIGHT");
                Intent intent = new Intent(getBaseContext(),OnSuccessActivity.class);
                startActivity(intent);
                finish();
            }
        });
        initCandidatures();
    }

    private void initCandidatures(){
        System.out.println("InitCandidatures");
        CandidatureController.onEvaluation(token, page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("OnResponse Callback");
                Gson gson = new Gson();
                try{
                    String auxResponse = response.body().string();
                    System.out.println("CANDIDATURES DUDE");
                    System.out.println(auxResponse);
                    JsonParser parser = new JsonParser();
                    JsonElement jsonArr = parser.parse(auxResponse);
                    for(JsonElement jsonElement : (JsonArray)jsonArr){
                        candidatures.add( gson.fromJson(jsonElement, Candidature.class) );
                    }

                    CandidatureItemAdapter adapter = new CandidatureItemAdapter(getBaseContext(), candidatures, token);
                    ListView candidatureList  = (ListView) findViewById(R.id.candidaturesListOnEvaluation);
                    candidatureList.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}