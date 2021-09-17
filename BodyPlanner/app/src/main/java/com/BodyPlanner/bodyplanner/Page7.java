package com.BodyPlanner.bodyplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Page7 extends AppCompatActivity {
    private Button BACK;
    private Button SAVE;
    private Button savee;
    private EditText edit_age;
    private EditText edit_height;
    private EditText edit_weight;
    private Button btn_age_edit;
    private Button btn_height_edit;
    private Button btn_weight_edit;
    private LinearLayout page1;
    private LinearLayout page2;

    private Button prev;
    private Button next;
    private CheckBox act_1;
    private CheckBox act_2;
    private CheckBox act_3;
    private CheckBox act_4;
    private CheckBox act_5;
    private ViewFlipper Flipper;

    private Button edit_Bulk;
    private Button edit_Lean;
    private Button edit_Diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page7);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        BACK = (Button)findViewById(R.id.BACK);
        SAVE = (Button)findViewById(R.id.SAVE);
        edit_age = (EditText)findViewById(R.id.edit_age);
        edit_weight = (EditText)findViewById(R.id.edit_weight);
        edit_height = (EditText)findViewById(R.id.edit_height);

        btn_age_edit = (Button)findViewById(R.id.btn_age_edit);
        btn_weight_edit = (Button)findViewById(R.id.btn_weight_edit);
        btn_height_edit = (Button)findViewById(R.id.btn_height_edit);

        edit_Bulk = (Button)findViewById(R.id.edit_Bulk);
        edit_Lean = (Button)findViewById(R.id.edit_Lean);
        edit_Diet = (Button)findViewById(R.id.edit_Diet);

        prev = (Button)findViewById(R.id.prev);
        next = (Button)findViewById(R.id.next);
        act_1 = (CheckBox)findViewById(R.id.act_1);
        act_2 = (CheckBox)findViewById(R.id.act_2);
        act_3 = (CheckBox)findViewById(R.id.act_3);
        act_4 = (CheckBox)findViewById(R.id.act_4);
        act_5 = (CheckBox)findViewById(R.id.act_5);
        Flipper = (ViewFlipper)findViewById(R.id.Flipper);

        savee = (Button)findViewById(R.id.savee);
        page1 = (LinearLayout)findViewById(R.id.page1);
        page2 = (LinearLayout)findViewById(R.id.page2);

        Editable age_confirm = edit_age.getText();
        Editable height_confirm = edit_height.getText();
        Editable weight_confirm = edit_weight.getText();

        Intent edit =  getIntent();

        Intent B = new Intent(Page7.this, Page5.class);

        SharedPreferences saveSelection = getSharedPreferences("Select", MODE_PRIVATE);
        SharedPreferences.Editor mode_select = saveSelection.edit();

        SharedPreferences saveCheck = getSharedPreferences("check",MODE_PRIVATE);
        SharedPreferences.Editor edit_check = saveCheck.edit();

        btn_age_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedAGE = getSharedPreferences("age", MODE_PRIVATE);   //이름 환경변수 저장
                SharedPreferences.Editor edit_age = sharedAGE.edit();                               //이름을 제어할 editor
                edit_age.putString("inputAGE", age_confirm.toString());

                edit_age.apply();
                Toast.makeText(getApplicationContext(),"나이가 수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btn_height_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedHEIGHT = getSharedPreferences("height", MODE_PRIVATE);   //이름 환경변수 저장
                SharedPreferences.Editor edit_height = sharedHEIGHT.edit();                               //이름을 제어할 editor
                edit_height.putString("inputHEIGHT", height_confirm.toString());

                edit_height.apply();
                Toast.makeText(getApplicationContext(),"신장이 수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btn_weight_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedWEIGHT = getSharedPreferences("weight", MODE_PRIVATE);   //이름 환경변수 저장
                SharedPreferences.Editor edit_weight = sharedWEIGHT.edit();                               //이름을 제어할 editor
                edit_weight.putString("inputWEIGHT", weight_confirm.toString());

                edit_weight.apply();
                Toast.makeText(getApplicationContext(),"체중이 수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        edit_Bulk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    edit_Bulk.setBackgroundColor(Color.rgb(63, 191, 207));
                    mode_select.putString("Select","Bulk");
                    Toast.makeText(getApplicationContext(),"벌크업을 선택했습니다.", Toast.LENGTH_SHORT).show();
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    edit_Bulk.setBackgroundColor(Color.rgb(33, 150, 243));
                }
                return false;
            }
        });

        edit_Lean.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    edit_Lean.setBackgroundColor(Color.rgb(63, 191, 207));
                    mode_select.putString("Select","MassUp");
                    Toast.makeText(getApplicationContext(),"린매쓰업을 선택했습니다.", Toast.LENGTH_SHORT).show();
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    edit_Lean.setBackgroundColor(Color.rgb(33, 150, 243));
                }
                return false;
            }
        });

        edit_Diet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    edit_Diet.setBackgroundColor(Color.rgb(63, 191, 207));
                    mode_select.putString("Select","Diet");
                    Toast.makeText(getApplicationContext(),"다이어트를 선택했습니다.", Toast.LENGTH_SHORT).show();
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    edit_Diet.setBackgroundColor(Color.rgb(33, 150, 243));
                }
                return false;
            }
        });

        act_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act_1.isChecked()){
                    act_2.setChecked(false);
                    act_3.setChecked(false);
                    act_4.setChecked(false);
                    act_5.setChecked(false);
                }

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

                String Check = "2";
                edit_check.putString("Check", Check);
            }
        });

        act_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act_3.isChecked()){
                    act_2.setChecked(false);
                    act_1.setChecked(false);
                    act_4.setChecked(false);
                    act_5.setChecked(false);
                }

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

                String Check = "5";
                edit_check.putString("Check", Check);
            }
        });

        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(B);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page1.isShown()){ }
                else{Flipper.showPrevious();}
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page2.isShown()){}
                else { Flipper.showNext();}
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_select.apply();
                Toast.makeText(getApplicationContext(),"저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        savee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_check.apply();
                Toast.makeText(getApplicationContext(),"저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}