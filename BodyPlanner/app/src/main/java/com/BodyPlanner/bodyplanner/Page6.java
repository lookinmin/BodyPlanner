package com.BodyPlanner.bodyplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Page6 extends AppCompatActivity {

    private Button BACK;
    private Spinner meal;
    private Spinner kinds;
    private EditText count;
    private Button meal_save;
    private Button help;

    private ArrayAdapter adapter_meal;
    private ArrayList<String> items_meal = new ArrayList<>();

    private TextView eat_kcal;
    private TextView eat_carbo;
    private TextView eat_protein;
    private TextView eat_fat;

    private Button clear;

    private TextView lack_kcal;
    private TextView lack_carbo;
    private TextView lack_protein;
    private TextView lack_fat;

    private EditText Mname;
    private EditText MKcal;
    private EditText Mcarbo;
    private EditText Mpro;
    private EditText Mfat;

    private ListView meal_show;

    ArrayList<String> eaten_list = new ArrayList<>();

    ArrayList<String> food_list = new ArrayList<>();

    double tmp_kcal;
    double tmp_carbo;
    double tmp_pro;
    double tmp_fat;
    String meal_kind;
    String inputMeal;

    String print_cal;
    String print_carbo;
    String print_pro;
    String print_fat;

    String eaten_count="0";
    ArrayList<String> kcal=new ArrayList<>();
    ArrayList<String> carbo=new ArrayList<>();
    ArrayList<String> pro=new ArrayList<>();
    ArrayList<String> fat=new ArrayList<>();

    ArrayList<Double> num_kcal=new ArrayList<>();
    ArrayList<Double> num_carbo=new ArrayList<>();
    ArrayList<Double> num_pro=new ArrayList<>();
    ArrayList<Double> num_fat=new ArrayList<>();

    int c_num;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Intent o = getIntent();
        String filecal=o.getStringExtra("File_cal");

        BACK = (Button)findViewById(R.id.BACK);
        meal = (Spinner)findViewById(R.id.meal);     //탄, 단, 지 중 택 1
        meal.setPrompt("영양군");
        kinds = (Spinner)findViewById(R.id.kinds);   //해당 음식군에서 종류 선택
        kinds.setPrompt("음식 선택");
        count = (EditText) findViewById(R.id.count);//몇개 먹었는 지 개수 선택

        meal_save = (Button)findViewById(R.id.meal_save);    //해당 선택군을 TextView에 저장
        help = (Button)findViewById(R.id.help);

        meal_show = (ListView)findViewById(R.id.meal_show);
        adapter_meal = new ArrayAdapter(this,R.layout.simpleitem, items_meal);
        meal_show.setAdapter(adapter_meal);

        eat_kcal = (TextView)findViewById(R.id.eat_kcal);
        eat_carbo = (TextView)findViewById(R.id.eat_carbo);
        eat_protein = (TextView)findViewById(R.id.eat_protein);
        eat_fat = (TextView)findViewById(R.id.eat_fat);

        lack_kcal = (TextView)findViewById(R.id.lack_kcal);
        lack_carbo = (TextView)findViewById(R.id.lack_carbo);
        lack_protein = (TextView)findViewById(R.id.lack_protein);
        lack_fat = (TextView)findViewById(R.id.lack_fat);

        Mname = (EditText)findViewById(R.id.Mname);
        MKcal = (EditText)findViewById(R.id.MKcal);
        Mcarbo = (EditText)findViewById(R.id.Mcarbo);
        Mpro = (EditText)findViewById(R.id.Mpro);
        Mfat = (EditText)findViewById(R.id.Mfat);

        clear = (Button)findViewById(R.id.clear);

        String File1 = o.getStringExtra("File1");

        String[] meal_list=getResources().getStringArray(R.array.영양군);
        String[] carbo_list=getResources().getStringArray(R.array.탄수화물식단);
        String[] pro_list=getResources().getStringArray(R.array.단백질식단);
        String[] fat_list=getResources().getStringArray(R.array.지방식단);
        String[] other_list=getResources().getStringArray(R.array.기타식단);
        String[] tmp_list=new String[]{"식단"};

        String loadAGE = "inputAGE";
        String loadHEIGHT = "inputHEIGHT";
        String loadWEIGHT = "inputWEIGHT";
        String loadSelect = "Select";
        String loadSex = "SEX";
        String loadCheck = "Check";

        String AGE_Value = "";
        String HEIGHT_Value = "";
        String WEIGHT_Value = "";
        String Select_Value = "";
        String SEX_Value = "";
        String Check_Value = "";
        String defaultValue = "";
        String amount;

        SharedPreferences loadShared_age = getSharedPreferences("age", MODE_PRIVATE);
        AGE_Value = loadShared_age.getString(loadAGE,defaultValue);

        SharedPreferences loadShared_weight = getSharedPreferences("weight", MODE_PRIVATE);
        WEIGHT_Value = loadShared_weight.getString(loadWEIGHT,defaultValue);

        SharedPreferences loadShared_height = getSharedPreferences("height", MODE_PRIVATE);
        HEIGHT_Value = loadShared_height.getString(loadHEIGHT,defaultValue);

        SharedPreferences loadShared_sex = getSharedPreferences("sex", MODE_PRIVATE);
        SEX_Value = loadShared_sex.getString(loadSex,defaultValue);

        SharedPreferences loadShared_check = getSharedPreferences("check", MODE_PRIVATE);
        Check_Value = loadShared_check.getString(loadCheck,defaultValue);

        double harr = cal_harris(SEX_Value,WEIGHT_Value,HEIGHT_Value,AGE_Value);

        amount = cal_amount(Check_Value,harr);

        SharedPreferences saveSelection = getSharedPreferences("Select",MODE_PRIVATE);
        Select_Value = saveSelection.getString(loadSelect, defaultValue);

        String filecarbo=o.getStringExtra("File_carbo");
        String filepro=o.getStringExtra("File_pro");
        String filefat=o.getStringExtra("File_fat");

        SharedPreferences share_cal=getSharedPreferences(filecal, MODE_PRIVATE);
        String strcal=share_cal.getString("edit_cal", "0");

        SharedPreferences share_carbo=getSharedPreferences(filecarbo, MODE_PRIVATE);
        String strcarbo=share_carbo.getString("edit_carbo", "0");

        SharedPreferences share_pro=getSharedPreferences(filepro, MODE_PRIVATE);
        String strpro=share_pro.getString("edit_pro", "0");

        SharedPreferences share_fat=getSharedPreferences(filefat, MODE_PRIVATE);
        String strfat=share_fat.getString("edit_fat", "0");

        eat_kcal.setText(strcal);
        eat_carbo.setText(strcarbo);
        eat_protein.setText(strpro);
        eat_fat.setText(strfat);

        String final_kcal = cal_kcal(Select_Value,amount);
        String final_protein = cal_protein(Select_Value,WEIGHT_Value);
        String final_fat = cal_fat(Select_Value,final_kcal);
        String final_carbo = cal_carbo(Select_Value,final_kcal,final_protein,final_fat);

        lack_kcal.setText(final_kcal);
        lack_carbo.setText(final_carbo);
        lack_protein.setText(final_protein);
        lack_fat.setText(final_fat);

        if(Double.parseDouble(strcal)>Double.parseDouble(final_kcal)){
            eat_kcal.setTextColor(Color.RED);
        }
        if(Double.parseDouble(strcarbo)>Double.parseDouble(final_carbo)){
            eat_carbo.setTextColor(Color.RED);
        }
        if(Double.parseDouble(strpro)>Double.parseDouble(final_protein)){
            eat_protein.setTextColor(Color.RED);
        }
        if(Double.parseDouble(strfat)>Double.parseDouble(final_fat)){
            eat_fat.setTextColor(Color.RED);
        }

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p9 = new Intent(Page6.this,Page9.class);
                startActivity(p9);
            }
        });

        final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.spinner_selected, meal_list);

        adapter.setDropDownViewResource(R.layout.spinner_item);
        meal.setAdapter(adapter);
        final ArrayAdapter<String> adapter_tmp= new ArrayAdapter<String>(this,R.layout.spinner_selected,tmp_list);
        kinds.setAdapter(adapter_tmp);
        meal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getItem(position).equals("탄수화물")) {
                    meal_kind="탄수화물";
                    final ArrayAdapter<String> adapter_carbo = new ArrayAdapter<String>(Page6.this, R.layout.spinner_selected, carbo_list);
                    adapter_carbo.setDropDownViewResource(R.layout.spinner_item);
                    kinds.setAdapter(adapter_carbo);
                    kinds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            eaten_list.add(kinds.getSelectedItem().toString());
                            if(adapter_carbo.getItem(position).equals("현미밥")){
                                tmp_kcal=195;
                                tmp_carbo=43;
                                tmp_pro=4;
                                tmp_fat=1.4;
                            }
                            else if(adapter_carbo.getItem(position).equals("흰쌀밥")){
                                tmp_kcal=310;
                                tmp_carbo=70;
                                tmp_pro=5;
                                tmp_fat=1.5;
                            }
                            else if(adapter_carbo.getItem(position).equals("통밀빵")){
                                tmp_kcal=247;
                                tmp_carbo=41;
                                tmp_pro=13;
                                tmp_fat=4;
                            }
                            else if(adapter_carbo.getItem(position).equals("식빵")){
                                tmp_kcal=80;
                                tmp_carbo=15.2;
                                tmp_pro=2.3;
                                tmp_fat=1;
                            }
                            else if(adapter_carbo.getItem(position).equals("고구마")){
                                tmp_kcal=155;
                                tmp_carbo=37;
                                tmp_pro=1.4;
                                tmp_fat=0;
                            }
                            else if(adapter_carbo.getItem(position).equals("감자")){
                                tmp_kcal=86;
                                tmp_carbo=20;
                                tmp_pro=1.9;
                                tmp_fat=0.1;
                            }
                            else if(adapter_carbo.getItem(position).equals("바나나"))
                            {
                                tmp_kcal=105;
                                tmp_carbo=27;
                                tmp_pro=1.3;
                                tmp_fat=0.4;
                            }
                            else if(adapter_carbo.getItem(position).equals("단호박")){
                                tmp_kcal=49;
                                tmp_carbo=11.7;
                                tmp_pro=1.4;
                                tmp_fat=0.4;
                            }
                            else if(adapter_carbo.getItem(position).equals("오트밀")){
                                tmp_kcal=110;
                                tmp_carbo=21;
                                tmp_pro=3;
                                tmp_fat=2;
                            }

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adapter.getItem(position).equals("단백질")) {
                    meal_kind="단백질";
                    final ArrayAdapter<String> adapter_pro = new ArrayAdapter<String>(Page6.this, R.layout.spinner_selected, pro_list);
                    adapter_pro.setDropDownViewResource(R.layout.spinner_item);
                    kinds.setAdapter(adapter_pro);
                    kinds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            eaten_list.add(kinds.getSelectedItem().toString());
                            if(adapter_pro.getItem(position).equals("닭가슴살"))
                            {
                                tmp_kcal=109;
                                tmp_carbo=0;
                                tmp_pro=23;
                                tmp_fat=1.2;
                            }
                            else if(adapter_pro.getItem(position).equals("훈제 닭가슴살"))
                            {
                                tmp_kcal=141;
                                tmp_carbo=1;
                                tmp_pro=25;
                                tmp_fat=2;
                            }
                            else if(adapter_pro.getItem(position).equals("닭가슴살 스테이크"))
                            {
                                tmp_kcal=142.3;
                                tmp_carbo=8.2;
                                tmp_pro=22.5;
                                tmp_fat=2.2;
                            }
                            else if(adapter_pro.getItem(position).equals("닭가슴살 샐러드"))
                            {
                                tmp_kcal=140;
                                tmp_carbo=20;
                                tmp_pro=14;
                                tmp_fat=0;
                            }
                            else if(adapter_pro.getItem(position).equals("소 등심"))
                            {
                                tmp_kcal=428;
                                tmp_carbo=0;
                                tmp_pro=40;
                                tmp_fat=28.6;
                            }
                            else if(adapter_pro.getItem(position).equals("소 부채살"))
                            {
                                tmp_kcal=544;
                                tmp_carbo=1;
                                tmp_pro=53;
                                tmp_fat=35.4;
                            }
                            else if(adapter_pro.getItem(position).equals("돼지 목살"))
                            {
                                tmp_kcal=528;
                                tmp_carbo=1.3;
                                tmp_pro=34.2;
                                tmp_fat=40.2;
                            }
                            else if(adapter_pro.getItem(position).equals("돼지 삼겹살"))
                            {
                                tmp_kcal=661;
                                tmp_carbo=1.2;
                                tmp_pro=34.5;
                                tmp_fat=56.4;
                            }
                            else if(adapter_pro.getItem(position).equals("계란"))
                            {
                                tmp_kcal=68;
                                tmp_carbo=0.5;
                                tmp_pro=6;
                                tmp_fat=5;
                            }
                            else if(adapter_pro.getItem(position).equals("훈제 연어"))
                            {
                                tmp_kcal=110;
                                tmp_carbo=0;
                                tmp_pro=20;
                                tmp_fat=4;
                            }
                            else if(adapter_pro.getItem(position).equals("프로틴"))
                            {
                                tmp_kcal=100;
                                tmp_carbo=1.5;
                                tmp_pro=21;
                                tmp_fat=2;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (adapter.getItem(position).equals("지방")) {
                    meal_kind="지방";
                    final ArrayAdapter<String> adapter_fat = new ArrayAdapter<String>(Page6.this, R.layout.spinner_selected, fat_list);
                    adapter_fat.setDropDownViewResource(R.layout.spinner_item);
                    kinds.setAdapter(adapter_fat);
                    kinds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            eaten_list.add(kinds.getSelectedItem().toString());
                            if(adapter_fat.getItem(position).equals("아보카도"))
                            {
                                tmp_kcal=161;
                                tmp_carbo=8.6;
                                tmp_pro=2;
                                tmp_fat=15;
                            }
                            else if(adapter_fat.getItem(position).equals("슬라이스 치즈")){
                                tmp_kcal=50;
                                tmp_carbo=1;
                                tmp_pro=2;
                                tmp_fat=4;
                            }
                            else if(adapter_fat.getItem(position).equals("그릭 요거트")){
                                tmp_kcal=50;
                                tmp_carbo=1;
                                tmp_pro=2;
                                tmp_fat=4;
                            }
                            else if(adapter_fat.getItem(position).equals("우유"))
                            {
                                tmp_kcal=130;
                                tmp_carbo=9;
                                tmp_pro=69;
                                tmp_fat=8;
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adapter.getItem(position).equals("기타")) {
                    meal_kind="기타";
                    final ArrayAdapter<String> adapter_other = new ArrayAdapter<String>(Page6.this, R.layout.spinner_selected, other_list);
                    adapter_other.setDropDownViewResource(R.layout.spinner_item);
                    kinds.setAdapter(adapter_other);
                    kinds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            eaten_list.add(kinds.getSelectedItem().toString());
                            if(adapter_other.getItem(position).equals("견과류"))
                            {
                                tmp_kcal=85;
                                tmp_carbo=11;
                                tmp_pro=3;
                                tmp_fat=4;
                            }
                            else if(adapter_other.getItem(position).equals("사과"))
                            {
                                tmp_kcal=72;
                                tmp_carbo=19;
                                tmp_pro=0.3;
                                tmp_fat=0.2;
                            }
                            else if(adapter_other.getItem(position).equals("에너지바"))
                            {
                                tmp_kcal=188;
                                tmp_carbo=20;
                                tmp_pro=6;
                                tmp_fat=10;
                            }
                            else if(adapter_other.getItem(position).equals("프로틴바"))
                            {
                                tmp_kcal=249;
                                tmp_carbo=21;
                                tmp_pro=12;
                                tmp_fat=13;
                            }
                            else if(adapter_other.getItem(position).equals("김치"))
                            {
                                tmp_kcal=8;
                                tmp_carbo=1.6;
                                tmp_pro=0.7;
                                tmp_fat=0.1;
                            }
                            else if(adapter_other.getItem(position).equals("BCAA"))
                            {
                                tmp_kcal=20;
                                tmp_carbo=0;
                                tmp_pro=4;
                                tmp_fat=0;
                            }
                            else if(adapter_other.getItem(position).equals("아메리카노"))
                            {
                                tmp_kcal=10;
                                tmp_carbo=0;
                                tmp_pro=1;
                                tmp_fat=0;
                            }

                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        loadItemsFromFile(File1, items_meal);

        meal_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                int num = 0;
                eaten_count = count.getText().toString();
                if (eaten_count.length() == 0) {
                    if (Mname.getText().toString().length() != 0) {
                        inputMeal = Mname.getText().toString();
                        num = 1;
                        eaten_list.add(inputMeal);
                        String mkcal = MKcal.getText().toString();
                        String mcarbo = Mcarbo.getText().toString();
                        String mpro = Mpro.getText().toString();
                        String mfat = Mfat.getText().toString();
                        tmp_kcal = Double.parseDouble(mkcal);
                        tmp_carbo = Double.parseDouble(mcarbo);
                        tmp_pro = Double.parseDouble(mpro);
                        tmp_fat = Double.parseDouble(mfat);

                        CalorieCalculate(num);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "개수를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    num = Integer.parseInt(eaten_count);
                    CalorieCalculate(num);
                }
            }

            private void CalorieCalculate(int num) {
                SharedPreferences spcal = getSharedPreferences(filecal, MODE_PRIVATE);
                SharedPreferences spcarbo = getSharedPreferences(filecarbo, MODE_PRIVATE);
                SharedPreferences sppro = getSharedPreferences(filepro, MODE_PRIVATE);
                SharedPreferences spfat = getSharedPreferences(filefat, MODE_PRIVATE);

                String saved_cal = spcal.getString("edit_cal", "0");
                String saved_carbo = spcarbo.getString("edit_carbo", "0");
                String saved_pro = sppro.getString("edit_pro", "0");
                String saved_fat = spfat.getString("edit_fat", "0");

                double savedcal = Double.parseDouble(saved_cal);
                double savedcarbo = Double.parseDouble(saved_carbo);
                double savedpro = Double.parseDouble(saved_pro);
                double savedfat = Double.parseDouble(saved_fat);

                print_cal = String.format("%.1f", (tmp_kcal * num + savedcal));
                print_carbo = String.format("%.1f", (tmp_carbo * num + savedcarbo));
                print_pro = String.format("%.1f", (tmp_pro * num + savedpro));
                print_fat = String.format("%.1f", (tmp_fat * num + savedfat));

                SharedPreferences.Editor editorcal = spcal.edit();
                SharedPreferences.Editor editorcarbo = spcarbo.edit();
                SharedPreferences.Editor editorpro = sppro.edit();
                SharedPreferences.Editor editorfat = spfat.edit();

                editorcal.putString("edit_cal", print_cal);
                editorcarbo.putString("edit_carbo", print_carbo);
                editorpro.putString("edit_pro", print_pro);
                editorfat.putString("edit_fat", print_fat);

                editorcal.apply();
                editorcarbo.apply();
                editorpro.apply();
                editorfat.apply();

                String todaycal = spcal.getString("edit_cal", "m");
                String todaycarbo = spcarbo.getString("edit_carbo", "m");
                String todaypro = sppro.getString("edit_pro", "m");
                String todayfat = spfat.getString("edit_fat", "m");

                eat_kcal.setText(todaycal);
                eat_carbo.setText(todaycarbo);
                eat_protein.setText(todaypro);
                eat_fat.setText(todayfat);

                if (Double.parseDouble(print_cal) > Double.parseDouble(final_kcal)) {
                    eat_kcal.setTextColor(Color.RED);
                }
                if (Double.parseDouble(print_carbo) > Double.parseDouble(final_carbo)) {
                    eat_carbo.setTextColor(Color.RED);
                }
                if (Double.parseDouble(print_pro) > Double.parseDouble(final_protein)) {
                    eat_protein.setTextColor(Color.RED);
                }
                if (Double.parseDouble(print_fat) > Double.parseDouble(final_fat)) {
                    eat_fat.setTextColor(Color.RED);
                }

                items_meal.add(writeFileMeal(eaten_list.get(eaten_list.size() - 1) + ": ", String.format("%.1f", tmp_kcal * num),
                        String.format("%.1f", tmp_carbo * num), String.format("%.1f", tmp_pro * num), String.format("%.1f", tmp_fat * num)));

                String rFile1 = readFile(File1);
                if (rFile1 != null)
                    adapter_meal.notifyDataSetChanged();
                saveItemsToFile(File1, items_meal);

                num_kcal.add(tmp_kcal * num);
                num_carbo.add(tmp_carbo * num);
                num_pro.add(tmp_pro * num);
                num_fat.add(tmp_fat * num);
                food_list.add(eaten_list.get(eaten_list.size() - 1));

                count.getText().clear();
                Mname.getText().clear();
                MKcal.getText().clear();
                Mcarbo.getText().clear();
                Mpro.getText().clear();
                Mfat.getText().clear();

            }
        });
        adapter_meal.notifyDataSetChanged();

        Intent B = new Intent(Page6.this, Page5.class);
        meal_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag=!flag;
                if(flag){
                    meal_show.setSelector(new PaintDrawable(Color.rgb(63, 191, 207)));
                }
                else
                    meal_show.setSelector(new PaintDrawable(Color.TRANSPARENT));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count;
                int checkedIndex;
                count = adapter_meal.getCount();
                if (count > 0) {
                    checkedIndex = meal_show.getCheckedItemPosition();
                    c_num = checkedIndex;
                    if (checkedIndex > -1 && checkedIndex <= count) {
                        items_meal.remove(checkedIndex);
                        meal_show.clearChoices();
                        adapter_meal.notifyDataSetChanged();
                        saveItemsToFile(File1, items_meal);

                        SharedPreferences Share_cal=getSharedPreferences(filecal, MODE_PRIVATE);
                        String total_cal=Share_cal.getString("edit_cal", "0");

                        SharedPreferences Share_carbo=getSharedPreferences(filecarbo, MODE_PRIVATE);
                        String total_carbo=Share_carbo.getString("edit_carbo", "0");

                        SharedPreferences Share_pro=getSharedPreferences(filepro, MODE_PRIVATE);
                        String total_pro=Share_pro.getString("edit_pro", "0");

                        SharedPreferences Share_fat=getSharedPreferences(filefat, MODE_PRIVATE);
                        String total_fat=Share_fat.getString("edit_fat", "0");

                        double num_total_kcal = Double.parseDouble(total_cal);
                        num_total_kcal = num_total_kcal - num_kcal.get(c_num);
                        SharedPreferences.Editor edit_kcal = Share_cal.edit();
                        edit_kcal.putString("edit_cal", String.valueOf(num_total_kcal));
                        edit_kcal.apply();

                        double num_total_carbo = Double.parseDouble(total_carbo);
                        num_total_carbo = num_total_carbo - num_carbo.get(c_num);
                        SharedPreferences.Editor edit_carbo = Share_carbo.edit();
                        edit_carbo.putString("edit_carbo", String.valueOf(num_total_carbo));
                        edit_carbo.apply();

                        double num_total_pro = Double.parseDouble(total_pro);
                        num_total_pro = num_total_pro - num_pro.get(c_num);
                        SharedPreferences.Editor edit_pro = Share_pro.edit();
                        edit_pro.putString("edit_pro", String.valueOf(num_total_pro));
                        edit_pro.apply();

                        double num_total_fat = Double.parseDouble(total_fat);
                        num_total_fat = num_total_fat - num_fat.get(c_num);
                        SharedPreferences.Editor edit_fat = Share_fat.edit();
                        edit_fat.putString("edit_fat", String.valueOf(num_total_fat));
                        edit_fat.apply();

                        eat_kcal.setText(String.format("%.1f", num_total_kcal));
                        eat_carbo.setText(String.format("%.1f", num_total_carbo));
                        eat_protein.setText(String.format("%.1f", num_total_pro));
                        eat_fat.setText(String.format("%.1f", num_total_fat));

                        if(Double.parseDouble(String.format("%.1f", num_total_kcal))>Double.parseDouble(final_kcal)){
                            eat_kcal.setTextColor(Color.RED);
                        }
                        else
                            eat_kcal.setTextColor(Color.rgb(135, 131, 131));
                        if(Double.parseDouble(String.format("%.1f", num_total_carbo))>Double.parseDouble(final_carbo)){
                            eat_carbo.setTextColor(Color.RED);
                        }
                        else
                            eat_carbo.setTextColor(Color.rgb(135, 131, 131));
                        if(Double.parseDouble(String.format("%.1f", num_total_pro))>Double.parseDouble(final_protein)){
                            eat_protein.setTextColor(Color.RED);
                        }
                        else
                            eat_protein.setTextColor(Color.rgb(135, 131, 131));
                        if(Double.parseDouble(String.format("%.1f", num_total_fat))>Double.parseDouble(final_fat)){
                            eat_fat.setTextColor(Color.RED);
                        }
                        else
                            eat_fat.setTextColor(Color.rgb(135, 131, 131));

                        Toast.makeText(getApplicationContext(), food_list.get(c_num)+"이(가) 삭제되었습니다", Toast.LENGTH_SHORT).show();
                        food_list.remove(c_num);
                        num_kcal.remove(c_num);
                        num_carbo.remove(c_num);
                        num_pro.remove(c_num);
                        num_fat.remove(c_num);
                        meal_show.setSelector(new PaintDrawable(Color.TRANSPARENT));
                        flag=!flag;
                    }
                }
            }
        });

        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(B);
            }
        });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void SplitNumber(String text, ArrayList<Double>tmp_num) {
        String str=text.replaceAll("[^\\d]", "");
        double num=Double.parseDouble(str)/10;
        String str2=String.format("%.1f", num);
        tmp_num.add(Double.parseDouble(str2));
    }

    private String writeFileMeal(String meal_name, String print_cal, String print_carbo, String print_pro, String print_fat) {
        String result;
        result = (meal_name+" "+print_cal+"kcal 탄 "+print_carbo+"g 단 "+print_pro+"g 지 "+print_fat+"g");
        return result;
    }

    private double MaleHarris(String weight, String height, String age) {
        double w = Double.parseDouble(weight);
        double h = Double.parseDouble(height);
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

    private Double cal_harris(String sex, String weight, String height, String age){
        double result = 0;
        if(sex.equals("male")){
            result =  MaleHarris(weight, height, age);
        }
        else if(sex.equals("female")){
            result =  FemaleHarris(weight, height, age);
        }
        return result;
    }

    private String cal_amount(String m, Double n){
        String result = null;
        if(m.equals("1")){
            result =  num1(n);
        }
        else if(m.equals("2")){
            result =  num2(n);
        }
        else if(m.equals("3")){
            result =  num3(n);
        }
        else if(m.equals("4")){
            result =  num4(n);
        }
        else if(m.equals("5")){
            result =  num5(n);
        }
        return result;
    }

    private String cal_kcal(String mode, String amount){
        String result = null;
        Double leo = Double.parseDouble(amount);
        Double Ann = 0.0;
        if(mode.equals("Bulk")){
            Ann = (leo + 600);
            result = String.format("%.0f",Ann);
        }
        else if(mode.equals("MassUp")){
            Ann = (leo + 200);
            result = String.format("%.0f",Ann);
        }
        else if(mode.equals("Diet")){
            Ann = (leo - 450);
            result = String.format("%.0f",Ann);
        }
        return  result;
    }

    private String cal_protein(String mode, String weight){
        String result = null;
        Double leo = Double.parseDouble(weight);
        Double Ann = 0.0;
        if(mode.equals("Bulk")){
            Ann = (leo*2.5);
            result = String.format("%.0f",Ann);
        }
        else if(mode.equals("MassUp")){
            Ann = (leo*1.7);
            result = String.format("%.0f",Ann);

        }
        else if(mode.equals("Diet")){
            Ann = (leo*1.4);
            result = String.format("%.0f",Ann);
        }
        return  result;
    }

    private String cal_fat(String mode, String kcal){
        String result = null;
        Double leo = Double.parseDouble(kcal);
        Double Ann = 0.0;
        if(mode.equals("Bulk")){
            Ann = ((leo*0.245)/9.1);
            result = String.format("%.0f",Ann);
        }
        else if(mode.equals("MassUp")){
            Ann = ((leo*0.23)/9.1);
            result = String.format("%.0f",Ann);

        }
        else if(mode.equals("Diet")){
            Ann = ((leo*0.2)/9.1);
            result = String.format("%.0f",Ann);
        }
        return  result;
    }

    private String cal_carbo(String mode, String kcal, String protein, String fat){
        String result = null;
        Double leo = Double.parseDouble(kcal);
        Double prey = Double.parseDouble(protein);
        Double Dry = Double.parseDouble(fat);
        Double Ann = 0.0;
        Ann = ((leo-(prey*4.1+Dry*9.1))/4.1);
        if(mode.equals("Bulk")){
            result = String.format("%.0f",Ann);
        }
        else if(mode.equals("MassUp")){
            result = String.format("%.0f",Ann);

        }
        else if(mode.equals("Diet")){
            result = String.format("%.0f",Ann);
        }
        return  result;
    }

    private String readFile(String fname1) {
        String fileContents="";
        try {
            InputStream iStream = openFileInput(fname1);
            if(iStream!=null){
                InputStreamReader iStreamReader=new InputStreamReader(iStream);
                BufferedReader bufferedReader=new BufferedReader(iStreamReader);
                String tmp="";
                StringBuffer sBuffer=new StringBuffer();
                while((tmp=bufferedReader.readLine())!=null){
                    sBuffer.append(tmp+"\n");
                }
                iStream.close();
                fileContents=sBuffer.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    private void saveItemsToFile(String fname, ArrayList<String> item){
        File file = new File(getFilesDir(), fname);
        FileWriter fw = null;
        BufferedWriter bufwr = null;
        try {
            // open file.
            fw = new FileWriter(file) ;
            bufwr = new BufferedWriter(fw) ;

            for (String str : item) {
                bufwr.write(str) ;
                bufwr.newLine() ;
            }

            // write data to the file.
            bufwr.flush() ;

        } catch (Exception e) {
            e.printStackTrace() ;
        }

        try {
            // close file.
            if (bufwr != null) {
                bufwr.close();
            }

            if (fw != null) {
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    private void loadItemsFromFile(String fname, ArrayList<String> item) {
        File file = new File(getFilesDir(), fname) ;
        FileReader fr = null ;
        BufferedReader bufrd = null ;
        String str ;

        if (file.exists()) {
            try {
                // open file.
                fr = new FileReader(file) ;
                bufrd = new BufferedReader(fr) ;
                while ((str = bufrd.readLine()) != null) {
                    item.add(str) ;
                    String[] num=str.split(("kcal|g"));
                    kcal.add(num[0]);
                    carbo.add(num[1]);
                    pro.add(num[2]);
                    fat.add(num[3]);
                    int idx=str.indexOf(":");
                    String tmp_str=str.substring(0, idx);
                    food_list.add(tmp_str);

                    SplitNumber(kcal.get(kcal.size()-1), num_kcal);
                    SplitNumber(carbo.get(carbo.size()-1), num_carbo);
                    SplitNumber(pro.get(pro.size()-1), num_pro);
                    SplitNumber(fat.get(fat.size()-1), num_fat);

                    saveItemsToFile(fname, item);
                }
                bufrd.close() ;
                fr.close() ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
    }

}