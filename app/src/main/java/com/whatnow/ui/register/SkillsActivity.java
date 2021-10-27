package com.whatnow.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;

import com.whatnow.MainActivity;
import com.whatnow.R;
import com.whatnow.models.Career;
import com.whatnow.models.College;
import com.whatnow.models.Skill;

import java.util.ArrayList;
import java.util.Date;

public class SkillsActivity extends AppCompatActivity {

    Fragment_Skills_1 fragment_skills_1;
    Fragment_Skills_2 fragment_skills_2;
    FragmentRegisterCareers fragmentRegisterCareers;
    FragmentRegisterColleges fragmentReisterColleges;
    Fragment_Skills_3 fragment_skills_3;
    Fragment_Skills_4 fragment_skills_4;
    Button btnFragments, btnFragments2, btnFragments3, btnFragments4, btnFragments5, btnFragments6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

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


                System.out.println(degree_date);
                for(String s : skill_ids) {
                    System.out.println(s);
                }

                for(String s : soft_skill_ids) {
                    System.out.println(s);
                }

                for(String s : career_ids) {
                    System.out.println(s);
                }
                for(String s : college_ids) {
                    System.out.println(s);
                }
                System.out.println(degree_date.toString());



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
}