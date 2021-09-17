package com.BodyPlanner.bodyplanner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Page1 extends AppCompatActivity {

    private EditText et_name;
    private Button btn_move;
    private String name;
    private Double harris = 0.0;

    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private RadioGroup choose_gender;
    private RadioButton male;
    private RadioButton female;

    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        CheckFirstPageExecute();

        et_name = (EditText) findViewById(R.id.et_name);
        btn_move = (Button) findViewById((R.id.btn_move));
        et_age = (EditText) findViewById(R.id.et_age);
        et_height = (EditText) findViewById(R.id.et_height);
        et_weight = (EditText) findViewById(R.id.et_weight);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        choose_gender = (RadioGroup) findViewById(R.id.choose_gender);


        Intent i = new Intent(Page1.this, Page2.class);

        SharedPreferences sharedSEX = getSharedPreferences("sex", MODE_PRIVATE);   //나이 환경변수 저장
        SharedPreferences.Editor edit_sex = sharedSEX.edit();                               //나이를 제어할 editor

        choose_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imm.hideSoftInputFromWindow(choose_gender.getWindowToken(), 0);
                if(checkedId==R.id.male){
                    String age = et_age.getText().toString();
                    String weight = et_weight.getText().toString();
                    String  height = et_height.getText().toString();
                    harris = MaleHarris(weight, height, age);
                    String harr=String.format("%.0f", harris);
                    i.putExtra("harris", harr);
                    i.putExtra("weight", weight);

                    edit_sex.putString("SEX", "male");
                }
                else if(checkedId==R.id.female){
                    String age = et_age.getText().toString();
                    String weight = et_weight.getText().toString();
                    String height = et_height.getText().toString();
                    harris = FemaleHarris(weight, height, age);
                    String harr2=String.format("%.0f", harris);
                    i.putExtra("harris", harr2);
                    i.putExtra("weight", weight);

                    edit_sex.putString("SEX", "female");
                }
            }
        });

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedAGE = getSharedPreferences("age", MODE_PRIVATE);   //나이 환경변수 저장
                SharedPreferences.Editor edit_age = sharedAGE.edit();                               //나이를 제어할 editor
                edit_age.putString("inputAGE", et_age.getText().toString());

                SharedPreferences sharedWEIGHT = getSharedPreferences("weight", MODE_PRIVATE);   //체중 환경변수 저장
                SharedPreferences.Editor edit_weight = sharedWEIGHT.edit();                               //이름을 제어할 editor
                edit_weight.putString("inputWEIGHT", et_weight.getText().toString());

                SharedPreferences sharedHEIGHT = getSharedPreferences("height", MODE_PRIVATE);   //키 환경변수 저장
                SharedPreferences.Editor edit_height = sharedHEIGHT.edit();                               //키를 제어할 editor
                edit_height.putString("inputHEIGHT", et_height.getText().toString());

                edit_age.apply();
                edit_weight.apply();
                edit_height.apply();
                edit_sex.apply();

                String name = et_name.getText().toString();
                i.putExtra("name", name);
                startActivity(i);
            }
        });
    }

    private void CheckFirstPageExecute() {
        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean first = pref.getBoolean("isFirst", false);
        if(first==false){
            Log.d("Is first Time?", "first");
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst",true);
            editor.apply();
            //앱 최초 실행시 하고 싶은 작업
        }else{
            Log.d("Is first Time?", "not first");
            Intent i = new Intent(Page1.this, Page5.class);
            startActivity(i);
        }
    }

    private double MaleHarris(String weight, String height, String age) {
        double w=Double.parseDouble(weight);
        double h=Double.parseDouble(height);
        int a =Integer.parseInt(age);
        double result = (66.47 + (13.75*w) + (5*h) - (6.76*a));
        return result;
    }

    private double FemaleHarris(String weight, String height, String age) {
        double w = Double.parseDouble(weight);
        double h = Double.parseDouble(height);
        int a = Integer.parseInt(age);
        double result = (655.1 + (9.56*w) + (1.85*h) - (4.68*a));
        return result;
    }

}