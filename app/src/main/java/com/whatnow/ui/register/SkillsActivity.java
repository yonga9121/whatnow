package com.whatnow.ui.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.whatnow.MainActivity;
import com.whatnow.R;
import com.whatnow.controllers.users.UsersController;
import com.whatnow.models.Career;
import com.whatnow.models.College;
import com.whatnow.models.Skill;
import com.whatnow.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Logger logger = Logger.getLogger(TAG);
    private String token;

    Fragment_Skills_1 fragment_skills_1;
    Fragment_Skills_2 fragment_skills_2;
    FragmentRegisterCareers fragmentRegisterCareers;
    FragmentRegisterColleges fragmentReisterColleges;
    Fragment_Skills_3 fragment_skills_3;
    Fragment_Skills_4 fragment_skills_4;
    Button btnFragments, btnFragments2, btnFragments3, btnFragments4, btnFragments5, btnFragments6;

    //Definir el id del permiso
    private final int CAMERA_PERMISSION_ID = 101;
    private final int GALLERY_PERMISSION_ID = 102;
    String cameraPerm = Manifest.permission.CAMERA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = Utils.getString("token", getBaseContext());
        setContentView(R.layout.activity_skills);

        requestPermission(this, cameraPerm, "Permiso para utilizar la camara", CAMERA_PERMISSION_ID);
        initView();

        btnFragments = findViewById(R.id.btnFragments);
        btnFragments2 = findViewById(R.id.btnFragments2);
        btnFragments3 = findViewById(R.id.btnFragments3);
        btnFragments4 = findViewById(R.id.btnFragments4);
        btnFragments5 = findViewById(R.id.btnFragments5);
        btnFragments6 = findViewById(R.id.btnFragments6);
        btnFragments2.setVisibility(View.GONE);
        btnFragments3.setVisibility(View.GONE);
        btnFragments4.setVisibility(View.GONE);
        btnFragments5.setVisibility(View.GONE);
        btnFragments6.setVisibility(View.GONE);

        fragment_skills_1 = new Fragment_Skills_1();
        fragment_skills_2 = new Fragment_Skills_2();
        fragmentRegisterCareers = new FragmentRegisterCareers();
        fragmentReisterColleges = new FragmentRegisterColleges();
        fragment_skills_3 = new Fragment_Skills_3();
        fragment_skills_4 = new Fragment_Skills_4();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,fragment_skills_1).commit();
        btnFragments.setOnClickListener(new View.OnClickListener() {
            // AQUI ESTAN LOS BOTONES SIQUIENTE
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.contenedor, fragment_skills_2);
                fragmentTransaction.commit();
                btnFragments.setVisibility(View.GONE);
                btnFragments2.setVisibility(View.VISIBLE);
            }
        });
        btnFragments2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                btnFragments2.setVisibility(View.GONE);
                btnFragments3.setVisibility(View.VISIBLE);
                fragmentTransaction.remove(fragment_skills_2);
                fragmentTransaction.replace(R.id.contenedor, fragmentRegisterCareers);
                fragmentTransaction.commit();
            }
        });
        btnFragments3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                btnFragments3.setVisibility(View.GONE);
                btnFragments4.setVisibility(View.VISIBLE);
                fragmentTransaction.remove(fragmentRegisterCareers);
                fragmentTransaction.replace(R.id.contenedor, fragmentReisterColleges);
                fragmentTransaction.commit();
            }
        });

        btnFragments4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                btnFragments4.setVisibility(View.GONE);
                btnFragments5.setVisibility(View.VISIBLE);
                fragmentTransaction.remove(fragmentReisterColleges);
                fragmentTransaction.replace(R.id.contenedor, fragment_skills_3);
                fragmentTransaction.commit();
            }
        });

        btnFragments5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                btnFragments5.setVisibility(View.GONE);
                btnFragments6.setVisibility(View.VISIBLE);
                fragmentTransaction.remove(fragment_skills_3);
                fragmentTransaction.replace(R.id.contenedor, fragment_skills_4);
                fragmentTransaction.commit();
            }
        });

        btnFragments6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(fragment_skills_4);
                fragmentTransaction.commit();*/

                ArrayList<String> skill_ids = new ArrayList<>();
                ArrayList<String> soft_skill_ids = new ArrayList<>();
                ArrayList<String> career_ids = new ArrayList<>();
                ArrayList<String> college_ids = new ArrayList<>();
                String desc_video_url = null;
                Date degree_date = new Date();


                for(Skill s :fragment_skills_1.selectedSkills.values()){
                    skill_ids.add(s.getId());
                }
                for(Skill s :fragment_skills_2.selectedSkills.values()){
                    soft_skill_ids.add(s.getId());
                }
                for(Career c :fragmentRegisterCareers.selectedCareers.values()){
                    career_ids.add(c.getId());
                }
                for(College c :fragmentReisterColleges.selectedColleges.values()){
                    college_ids.add(c.getId());
                }

                degree_date = new Date(fragment_skills_3.dateGraduacion.getDate());

                desc_video_url = fragment_skills_4.imageUrl;

                UsersController.completeProfile(
                        token,
                        skill_ids,
                        soft_skill_ids,
                        career_ids,
                        college_ids,
                        desc_video_url,
                        degree_date
                ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200){
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        });


        /*btnFragments4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.remove(fragment_skills_2);
                //fragmentTransaction.replace(R.id.contenedor, fragment_skills_1);
                fragmentTransaction.commit();
                btnFragments4.setVisibility(View.GONE);
                //btnFragments.setVisibility(View.VISIBLE);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_ID){
            initView();
        }
    }

    private void requestPermission(Activity context, String permission, String justification, int id){

        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(context, justification, Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(context, new String[]{permission}, id);
        }
    }

    private void initView() {
        if (ContextCompat.checkSelfPermission(this, cameraPerm)
                != PackageManager.PERMISSION_GRANTED) {
            logger.warning("Failed to getting the permission :(");
        } else {
            logger.info("Success getting the permission :)");
        }
    }




}