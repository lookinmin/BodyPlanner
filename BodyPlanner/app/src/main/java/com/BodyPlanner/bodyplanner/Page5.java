package com.BodyPlanner.bodyplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Page5 extends AppCompatActivity {

    private Button EDIT;
    private MaterialCalendarView calendar;

    private TextView meal_text;
    private TextView training_text;
    private TextView show_weight;
    private TextView state;


    Intent x = getIntent();
    String fname1 = null;
    private String str1 = null;

    String fname2 = null;
    private String str2 = null;

    String loadKey = "inputWEIGHT";
    String loadValue = "";
    String defaultValue = "";

    String fname3 = null;
    String fname4 = null;

    private long backKeyPressedTime = 0;
    private Toast toast;

    long now = System.currentTimeMillis();
    Date mDate = new Date(now);
    SimpleDateFormat year = new SimpleDateFormat("yyyy");
    String getYear = year.format(mDate);
    SimpleDateFormat month = new SimpleDateFormat("M");
    SimpleDateFormat print_month = new SimpleDateFormat("MM");
    String getMonth = month.format(mDate);
    int int_month=Integer.parseInt(getMonth)-1;
    String printmonth=print_month.format(mDate);
    SimpleDateFormat day = new SimpleDateFormat("d");
    SimpleDateFormat print_day = new SimpleDateFormat("dd");
    String getDay = day.format(mDate);
    String printday=print_day.format(mDate);

    ArrayList<String>tmp_year = new ArrayList<>();
    ArrayList<String>tmp_month = new ArrayList<>();
    ArrayList<String>tmp_day = new ArrayList<>();

    ArrayList<Integer>meal_year = new ArrayList<>();
    ArrayList<Integer>meal_month = new ArrayList<>();
    ArrayList<Integer>meal_day = new ArrayList<>();

    ArrayList<Integer>exer_year = new ArrayList<>();
    ArrayList<Integer>exer_month = new ArrayList<>();
    ArrayList<Integer>exer_day = new ArrayList<>();


    String mealdate="DateMeal.txt";
    String exerdate="DateExer.txt";
    ArrayList<String>filemeal = new ArrayList<>();
    ArrayList<String>fileexer = new ArrayList<>();

    String mealstr1;
    String exerstr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_page5);
        EDIT = (Button)findViewById(R.id.EDIT);      //개인정보 수정 버튼 -> 페이지 연동 필요할 듯 만들게
        calendar = (MaterialCalendarView)findViewById(R.id.calendar);
        calendar.setSelectedDate(CalendarDay.today());
        meal_text = (TextView) findViewById(R.id.meal_text);
        training_text = (TextView) findViewById(R.id.training_text);
        show_weight = (TextView)findViewById(R.id.show_weight);
        meal_text.setMovementMethod(new ScrollingMovementMethod());
        training_text.setMovementMethod(new ScrollingMovementMethod());
        calendar.setTopbarVisible(true);
        state = (TextView)findViewById(R.id.state);

        Intent o = new Intent(Page5.this, Page6.class);
        Intent edit = new Intent(Page5.this, Page7.class);
        Intent x = new Intent(Page5.this,Page8.class);

        SharedPreferences loadShared = getSharedPreferences("weight",MODE_PRIVATE);
        loadValue = loadShared.getString(loadKey,defaultValue);
        show_weight.setText(loadValue);

        String loadSelect = "Select";
        String defaultValue = "";
        String Select_Value = "";

        SharedPreferences saveSelection = getSharedPreferences("Select",MODE_PRIVATE);
        Select_Value = saveSelection.getString(loadSelect, defaultValue);
        String shown = "";

        if(Select_Value.equals("Bulk")){
            shown = "벌크업";
            state.setText(shown);
        }
        else if(Select_Value.equals("MassUp")){
            shown = "린매쓰업";
            state.setText(shown);
        }

        else if(Select_Value.equals("Diet")){
            shown = "다이어트";
            state.setText(shown);
        }


        String meal = "식단";
        String train = "운동";

        String file_cal="calCalendarDay{"+getYear+"-"+int_month+"-"+getDay+"}";
        String file_carbo="carboCalendarDay{"+getYear+"-"+int_month+"-"+getDay+"}";
        String file_pro="proCalendarDay{"+getYear+"-"+int_month+"-"+getDay+"}";
        String file_fat="fatCalendarDay{"+getYear+"-"+int_month+"-"+getDay+"}";

        o.putExtra("File_cal", file_cal);
        o.putExtra("File_carbo", file_carbo);
        o.putExtra("File_pro", file_pro);
        o.putExtra("File_fat", file_fat);

        fname1=meal+"CalendarDay{"+getYear+"-"+int_month+"-"+getDay+"}.txt";
        fname2=train+"CalendarDay{"+getYear+"-"+int_month+"-"+getDay+"}.txt";

        String today_meal=fname1;
        String today_exer=fname2;

        FirstMakeFILE(fname1, fname2);
        FirstMakeFILE(mealdate, exerdate);

        LoadFileName(mealdate, filemeal);
        LoadFileName(exerdate, fileexer);
        SeparateDateFromFile(mealdate, meal_year, meal_month, meal_day);
        SeparateDateFromFile(exerdate, exer_year, exer_month, exer_day);

        for(int i=0;i<filemeal.size();i++){
            mealstr1=readFile(filemeal.get(i));
            if(!mealstr1.equals("")){
                CalendarDay day = CalendarDay.from(meal_year.get(i), meal_month.get(i), meal_day.get(i));
                calendar.addDecorator(new EventDecorator(Color.rgb(63, 191, 207), Collections.singleton(day)));
            }
        }

        for(int i=0;i<fileexer.size();i++){
            exerstr2=readFile(fileexer.get(i));
            if(!exerstr2.equals("")){
                CalendarDay day = CalendarDay.from(exer_year.get(i), exer_month.get(i), exer_day.get(i));
                calendar.addDecorator(new EventDecorator(Color.rgb(63, 191, 207), Collections.singleton(day)));
            }
        }

        String rFile1=readFile(fname1);
        if(rFile1!=null) {
            meal_text.setText(rFile1);
            if(!rFile1.equals("")) {
                filemeal.add(fname1);
                saveItemsToFile(mealdate, filemeal);
            }
        }

        String rFile2=readFile(fname2);
        if(rFile2!=null) {
            training_text.setText(rFile2);
            if(!rFile2.equals("")) {
                fileexer.add(fname2);
                saveItemsToFile(exerdate, fileexer);
            }
        }

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(edit);
            }
        });

        calendar.addDecorators(
                new MySelectorDecorator(Page5.this),
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator()
        );
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                meal_text.setVisibility(View.VISIBLE);
                training_text.setVisibility(View.VISIBLE);
                fname3=meal+date+".txt";
                fname4=train+date+".txt";
                String file_cal="cal"+date;
                String file_carbo="carbo"+date;
                String file_pro="pro"+date;
                String file_fat="fat"+date;
                if(!today_meal.equals(fname3)) {
                    FirstMakeFILE(fname3, fname4);
                    fname1 = fname3;
                    fname2 = fname4;

                    String cal_file1 = readFile(fname3);
                    if (cal_file1 != null) {
                        filemeal.add(fname3);
                        saveItemsToFile(mealdate, filemeal);
                        meal_text.setText(cal_file1);
                    }
                    String cal_file2 = readFile(fname4);
                    if (cal_file2 != null) {
                        fileexer.add(fname4);
                        saveItemsToFile(exerdate, fileexer);
                        training_text.setText(cal_file2);
                    }
                }
                else {
                    meal_text.setText(rFile1);
                    training_text.setText(rFile2);
                    fname1=today_meal;
                    fname2=today_exer;
                }
                o.putExtra("File_cal", file_cal);
                o.putExtra("File_carbo", file_carbo);
                o.putExtra("File_pro", file_pro);
                o.putExtra("File_fat", file_fat);
            }
        });

        meal_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o.putExtra("File1",fname1);
                startActivity(o);
            }
        });

        training_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.putExtra("File2",fname2);
                startActivity(x);
            }
        });
    }

    private void saveItemsToFile(String file_name, ArrayList<String>filename) {
        File file = new File(getFilesDir(), file_name);
        FileWriter fw = null;
        BufferedWriter bufwr = null;
        try {
            // open file.
            fw = new FileWriter(file) ;
            bufwr = new BufferedWriter(fw) ;

            for (String str : filename) {
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

    private void LoadFileName(String datefile, ArrayList<String>filename) {
        File file = new File(getFilesDir(), datefile) ;
        FileReader fr = null ;
        BufferedReader bufrd = null ;
        String str ;

        if (file.exists()) {
            try {
                // open file.
                fr = new FileReader(file) ;
                bufrd = new BufferedReader(fr) ;

                while ((str = bufrd.readLine()) != null) {
                    filename.add(str) ;
                }

                bufrd.close() ;
                fr.close() ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
    }

    private void SeparateDateFromFile(String datefile, ArrayList<Integer> year,ArrayList<Integer> month, ArrayList<Integer> day ) {
        File file = new File(getFilesDir(), datefile) ;
        FileReader fr = null ;
        BufferedReader bufrd = null ;
        String str ;

        if (file.exists()) {
            try {
                fr = new FileReader(file) ;
                bufrd = new BufferedReader(fr) ;
                while ((str = bufrd.readLine()) != null) {
                    String[] num = str.split("-");
                    tmp_year.add(num[0]);
                    tmp_month.add(num[1]);
                    tmp_day.add(num[2]);

                    SplitNumber(tmp_year.get(tmp_year.size()-1), year);
                    SplitNumber(tmp_month.get(tmp_month.size()-1), month);
                    SplitNumber(tmp_day.get(tmp_day.size()-1), day);
                }
                bufrd.close() ;
                fr.close() ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }

    }

    private void SplitNumber(String s, ArrayList<Integer> arrayList) {
        String str=s.replaceAll("[^\\d]", "");
        arrayList.add(Integer.parseInt(str));
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            ActivityCompat.finishAffinity(this);
            toast.cancel();
        }
    }

    private String readFile(String fname) {
        String fileContents="";
        try {
            InputStream iStream = openFileInput(fname);
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

    public void FirstMakeFILE(String a1, String a2){
        FileOutputStream fos1 =null;
        FileOutputStream fos2 =null;

        try{
            fos1 = openFileOutput(a1, MODE_APPEND);
            fos2 = openFileOutput(a2, MODE_APPEND);

            fos1.close();
            fos2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}