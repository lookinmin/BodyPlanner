package com.BodyPlanner.bodyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Page2 extends AppCompatActivity {

    private TextView name_sub;
    private TextView harris_sub;
    private CheckBox act_1;
    private CheckBox act_2;
    private CheckBox act_3;
    private CheckBox act_4;
    private CheckBox act_5;
    private Button btn_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        name_sub = (TextView)findViewById(R.id.name_sub);
        harris_sub = (TextView)findViewById(R.id.harris_sub);
        act_1 = (CheckBox)findViewById(R.id.act_1);
        act_2 = (CheckBox)findViewById(R.id.act_2);
        act_3 = (CheckBox)findViewById(R.id.act_3);
        act_4 = (CheckBox)findViewById(R.id.act_4);
        act_5 = (CheckBox)findViewById(R.id.act_5);
        btn_move = (Button)findViewById(R.id.btn_move);

        Intent i = getIntent();

        String name = i.getStringExtra("name");
        name_sub.setText(name);

        String harris = i.getStringExtra("harris");
        harris_sub.setText(harris);

        String weight=i.getStringExtra("weight");

        Intent ui = new Intent(Page2.this, Page3.class);

        Double hallo = Double.parseDouble(harris_sub.getText().toString());

        SharedPreferences sharedCheck = getSharedPreferences("check", MODE_PRIVATE);
        SharedPreferences.Editor edit_check = sharedCheck.edit();



        act_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act_1.isChecked()){
                    act_2.setChecked(false);
                    act_3.setChecked(false);
                    act_4.setChecked(false);
                    act_5.setChecked(false);
                }

                String amount = num1(hallo);
                ui.putExtra("amount", amount);

                String Check = "1";
                edit_check.putString("Check", Check);
            }
        });

        act_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act_2.isChecked()){
                    act_1.setChecked(false);
                    act_3.setChecked(false);
                    act_4.setChecked(false);
                    act_5.setChecked(false);
                }
                String amount = num2(hallo);
                ui.putExtra("amount", amount);

                String Check = "2";
                edit_check.putString("Check", Check);
            }
        });

        act_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act_3.isChecked()){
                    act_1.setChecked(false);
                    act_2.setChecked(false);
                    act_4.setChecked(false);
                    act_5.setChecked(false);
                }
                String amount = num3(hallo);
                ui.putExtra("amount", amount);
                String Check = "3";
                edit_check.putString("Check", Check);

            }
        });

        act_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act_4.isChecked()){
                    act_2.setChecked(false);
                    act_3.setChecked(false);
                    act_1.setChecked(false);
                    act_5.setChecked(false);
                }
                String amount = num4(hallo);
                ui.putExtra("amount", amount);
                String Check = "4";
                edit_check.putString("Check", Check);
            }
        });

        act_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act_5.isChecked()){
                    act_2.setChecked(false);
                    act_3.setChecked(false);
                    act_4.setChecked(false);
                    act_1.setChecked(false);
                }
                String amount = num5(hallo);
                ui.putExtra("amount", amount);
                String Check = "5";
                edit_check.putString("Check", Check);
            }
        });



        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_check.apply();
                String str = name_sub.getText().toString();
                ui.putExtra("name", str);
                ui.putExtra("weight", weight);
                startActivity(ui);

            }
        });
    }

    private String num1(Double n){
        double v = n * 0.2 + n;
        return String.format("%.0f", v);
    }

    private String num2(Double n){
        double v = n * 0.375 + n;
        return String.format("%.0f", v);
    }
    private String num3(Double n){
        double v = n * 0.555 + n;
        return String.format("%.0f", v);
    }
    private String num4(Double n){
        double v = n * 0.725 + n;
        return String.format("%.0f", v);
    }
    private String num5(Double n){
        double v = n * 0.895 + n;
        return String.format("%.0f", v);
    }
}