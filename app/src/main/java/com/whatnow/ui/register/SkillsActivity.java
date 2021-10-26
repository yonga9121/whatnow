package com.whatnow.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.whatnow.MainActivity;
import com.whatnow.R;

public class SkillsActivity extends AppCompatActivity {

    Fragment_Skills_1 fragment_skills_1;
    Fragment_Skills_2 fragment_skills_2;
    Fragment_Skills_3 fragment_skills_3;
    Fragment_Skills_4 fragment_skills_4;
    Button btnFragments, btnFragments2, btnFragments3, btnFragments4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        btnFragments = findViewById(R.id.btnFragments);
        btnFragments2 = findViewById(R.id.btnFragments2);
        btnFragments3 = findViewById(R.id.btnFragments3);
        btnFragments4 = findViewById(R.id.btnFragments4);
        btnFragments2.setVisibility(View.GONE);
        btnFragments3.setVisibility(View.GONE);
        btnFragments4.setVisibility(View.GONE);
        fragment_skills_1 = new Fragment_Skills_1();
        fragment_skills_2 = new Fragment_Skills_2();
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
                fragmentTransaction.replace(R.id.contenedor, fragment_skills_3);
                fragmentTransaction.commit();
            }
        });
        btnFragments3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                btnFragments3.setVisibility(View.GONE);
                btnFragments4.setVisibility(View.VISIBLE);
                fragmentTransaction.remove(fragment_skills_2);
                fragmentTransaction.replace(R.id.contenedor, fragment_skills_4);
                fragmentTransaction.commit();
            }
        });
        btnFragments4.setOnClickListener(new View.OnClickListener() {
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
        });


    }
}