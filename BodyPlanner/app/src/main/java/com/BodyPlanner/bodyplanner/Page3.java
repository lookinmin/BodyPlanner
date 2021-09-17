package com.BodyPlanner.bodyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Page3 extends AppCompatActivity {
    private TextView name_sub1;
    private TextView amount;
    private Button Bulk;
    private Button Lean;
    private Button Diet;
    private Button btn_move;

    boolean b_check=false;
    boolean l_check=false;
    boolean d_check=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        name_sub1 = (TextView)findViewById(R.id.name_sub1);
        amount = (TextView)findViewById(R.id.amount);
        Bulk = (Button)findViewById(R.id.Bulk);
        Lean = (Button)findViewById(R.id.Lean);
        Diet = (Button)findViewById(R.id.Diet);
        btn_move = (Button)findViewById(R.id.btn_move);

        Intent ui = getIntent();
        String str = ui.getStringExtra("name");
        name_sub1.setText(str);

        String harris = ui.getStringExtra("amount");
        amount.setText(harris);

        String weight = ui.getStringExtra("weight");

        double admin=Double.parseDouble(harris);
        double w=Double.parseDouble(weight);

        Intent to_page4 = new Intent(Page3.this, Page4.class);

        SharedPreferences saveSelection = getSharedPreferences("Select", MODE_PRIVATE);
        SharedPreferences.Editor mode_select = saveSelection.edit();

        Bulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_check=!b_check;
                mode_select.putString("Select","Bulk");
                if(b_check==true){
                    Bulk.setBackgroundColor(Color.rgb(63, 191, 207));
                    double bulk_cal=admin+600;
                    double bulk_pro=w*2.5;
                    double bulk_fat=(bulk_cal*0.245)/9.1;
                    double bulk_carbo=(bulk_cal-(bulk_pro*4.1+bulk_fat*9.1))/4.1;

                    String bulkcal=String.format("%.0f", bulk_cal);
                    String bulkpro=String.format("%.0f", bulk_pro);
                    String bulkfat=String.format("%.0f", bulk_fat);
                    String bulkcarbo=String.format("%.0f", bulk_carbo);

                    to_page4.putExtra("plan", "벌크업");
                    to_page4.putExtra("cal", bulkcal);
                    to_page4.putExtra("pro", bulkpro);
                    to_page4.putExtra("fat", bulkfat);
                    to_page4.putExtra("carbo", bulkcarbo);
                    Toast.makeText(getApplicationContext(),"벌크업을 선택했습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                    Bulk.setBackgroundColor(Color.rgb(33, 150, 243));
            }
        });

        Lean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l_check=!l_check;
                mode_select.putString("Select","MassUp");
                if(l_check==true){
                    Lean.setBackgroundColor(Color.rgb(63,191, 207));
                    double lean_cal = admin+200;
                    double lean_pro = w*1.7;
                    double lean_fat = (lean_cal*0.23)/9.1;
                    double lean_carbo = (lean_cal-(lean_pro*4.1+lean_fat*9.1))/4.1;

                    String leancal=String.format("%.0f", lean_cal);
                    String leanpro=String.format("%.0f", lean_pro);
                    String leanfat=String.format("%.0f", lean_fat);
                    String leancarbo=String.format("%.0f", lean_carbo);

                    to_page4.putExtra("plan", "린매쓰업");
                    to_page4.putExtra("cal", leancal);
                    to_page4.putExtra("pro", leanpro);
                    to_page4.putExtra("fat", leanfat);
                    to_page4.putExtra("carbo", leancarbo);
                    Toast.makeText(getApplicationContext(),"린매쓰업을 선택했습니다.", Toast.LENGTH_SHORT).show();                }
                else
                    Lean.setBackgroundColor(Color.rgb(33, 150, 243));
            }
        });

        Diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d_check=!d_check;
                mode_select.putString("Select","Diet");
                if(d_check==true) {
                    Diet.setBackgroundColor(Color.rgb(63, 191, 207));
                    double diet_cal = admin - 450;
                    double diet_pro = w * 1.4;
                    double diet_fat = (diet_cal * 0.2) / 9.1;
                    double diet_carbo = (diet_cal - (diet_pro * 4.1 + diet_fat * 9.1)) / 4.1;

                    String dietcal = String.format("%.0f", diet_cal);
                    String dietpro = String.format("%.0f", diet_pro);
                    String dietfat = String.format("%.0f", diet_fat);
                    String dietcarbo = String.format("%.0f", diet_carbo);

                    to_page4.putExtra("plan", "다이어트");
                    to_page4.putExtra("cal", dietcal);
                    to_page4.putExtra("pro", dietpro);
                    to_page4.putExtra("fat", dietfat);
                    to_page4.putExtra("carbo", dietcarbo);
                    Toast.makeText(getApplicationContext(), "다이어트를 선택했습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                    Diet.setBackgroundColor(Color.rgb(33, 150, 243));
            }
        });



        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(to_page4);
                mode_select.apply();
            }
        });
    }
}