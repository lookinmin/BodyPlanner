package com.BodyPlanner.bodyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Page4 extends AppCompatActivity {
    private TextView body_plan;
    private TextView all_kcal;
    private TextView carbo;
    private TextView protein;
    private TextView fat;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Intent intent = getIntent();

        String plan=intent.getStringExtra("plan");
        String user_cal =intent.getStringExtra("cal");
        String user_fat =intent.getStringExtra("fat");
        String user_pro =intent.getStringExtra("pro");
        String user_carbo =intent.getStringExtra("carbo");

        body_plan = (TextView)findViewById(R.id.body_plan);
        all_kcal = (TextView)findViewById(R.id.all_kcal);
        carbo = (TextView)findViewById(R.id.carbo);
        protein = (TextView)findViewById(R.id.protein);
        fat = (TextView)findViewById(R.id.fat);
        btn_start = (Button)findViewById(R.id.btn_start);


        body_plan.setText(plan);
        all_kcal.setText(user_cal);
        carbo.setText(user_carbo);
        fat.setText(user_fat);
        protein.setText(user_pro);

        Intent x =new Intent(Page4.this, Page5.class);



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(x);
            }
        });

    }
}