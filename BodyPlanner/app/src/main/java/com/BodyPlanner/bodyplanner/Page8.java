package com.BodyPlanner.bodyplanner;

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

public class Page8 extends AppCompatActivity {
    private Spinner training;
    private Spinner part;
    private Spinner muscle;

    private EditText heft;
    private EditText reps;
    private EditText set;

    private Button help;
    private Button BACK;
    private Button clear_act;
    private Button muscle_save;
    private ListView training_show;
    private ArrayAdapter adapter_act;
    private ArrayList<String> items_act = new ArrayList<>();

    String exer_mode;
    ArrayList<String> exer_list = new ArrayList<>();
    ArrayList<String> print_list = new ArrayList<>();

    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_page8);

        BACK = (Button)findViewById(R.id.BACK);
        training = (Spinner)findViewById(R.id.training);  //유산소, 웨이트중에 택 1
        training.setPrompt("유산소/웨이트");
        part = (Spinner)findViewById(R.id.part);          // 유산소 -> 뭐할지   / 웨이트 -> 어느부분(등,가슴,...)
        part.setPrompt("운동종류");
        muscle = (Spinner)findViewById(R.id.muscle);      // 유산소 -> 빈칸     / 웨이트 -> 운동종류(랫풀다운, 풀업...)
        muscle.setPrompt("운동종목");
        heft = (EditText) findViewById(R.id.heft);                 //유산소(런닝머신,사이클) -> 속도, 총거리   / 웨이트 -> 무게
        reps = (EditText) findViewById(R.id.reps);                 //유산소(런닝머신,사이클) -> 시간   / 웨이트 -> 반복횟수
        set = (EditText) findViewById(R.id.set);                   //유산소 -> 빈칸 / 웨이트 -> 세트 수
        muscle_save = (Button)findViewById(R.id.muscle_save);       //두번째 배열 SAVE
        training_show = (ListView) findViewById(R.id.training_show);
        clear_act = (Button)findViewById(R.id.clear_act);
        help = (Button)findViewById(R.id.help);

        adapter_act = new ArrayAdapter(this, R.layout.simpleitem, items_act);
        training_show.setAdapter(adapter_act);

        String loadWEIGHT = "inputWEIGHT";
        String WEIGHT_Value = "";
        String defaultValue = "";
        SharedPreferences loadShared_weight = getSharedPreferences("weight", MODE_PRIVATE);
        WEIGHT_Value = loadShared_weight.getString(loadWEIGHT,defaultValue);

        Intent x = getIntent();
        String File2 = x.getStringExtra("File2");

        loadItemsFromFile(File2, items_act);
        adapter_act.notifyDataSetChanged();

        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Page8.this, Page5.class);
                startActivity(back);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hel = new Intent(Page8.this, Page10.class);
                startActivity(hel);
            }
        });

        String[] training_list=getResources().getStringArray(R.array.운동);
        String[] oxy_list=getResources().getStringArray(R.array.유산소부위);
        String[] muscle_list=getResources().getStringArray(R.array.웨이트부위);
        String[] back_list=getResources().getStringArray(R.array.등운동);
        String[] chest_list=getResources().getStringArray(R.array.가슴운동);
        String[] shoulder_list=getResources().getStringArray(R.array.어깨운동);
        String[] biceps_list=getResources().getStringArray(R.array.이두운동);
        String[] triceps_list=getResources().getStringArray(R.array.삼두운동);
        String[] leg_list=getResources().getStringArray(R.array.하체운동);
        String[] abs_list=getResources().getStringArray(R.array.복근운동);
        String[] list_tmp=new String[]{""};
        final ArrayAdapter<String> adapter_training = new ArrayAdapter<String>(this,R.layout.spinner_selected,training_list);
        adapter_training.setDropDownViewResource(R.layout.spinner_item);
        training.setAdapter(adapter_training);

        training_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag=!flag;
                if(flag){
                    training_show.setSelector(new PaintDrawable(Color.rgb(145, 222, 222)));
                }
                else
                    training_show.setSelector(new PaintDrawable(Color.TRANSPARENT));
            }
        });

        training.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adapter_training.getItem(position).equals("유산소")) {
                    exer_mode="유산소";
                    final ArrayAdapter<String> adapter_oxy = new ArrayAdapter<String>(Page8.this,R.layout.spinner_selected,oxy_list);
                    adapter_oxy.setDropDownViewResource(R.layout.spinner_item);
                    part.setAdapter(adapter_oxy);
                    part.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            exer_list.add(part.getSelectedItem().toString());
                            final ArrayAdapter<String> Tmp_list=new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, list_tmp);
                            Tmp_list.setDropDownViewResource(R.layout.spinner_item);
                            muscle.setAdapter(Tmp_list);
                            muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if(adapter_training.getItem(position).equals("웨이트")) {
                    exer_mode="웨이트";
                    final ArrayAdapter<String> adapter_part = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, muscle_list);
                    adapter_part.setDropDownViewResource(R.layout.spinner_item);
                    part.setAdapter(adapter_part);
                    part.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(adapter_part.getItem(position).equals("등")){
                                final ArrayAdapter<String> adapter_back = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, back_list);
                                adapter_back.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_back);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(adapter_part.getItem(position).equals("가슴")){
                                final ArrayAdapter<String> adapter_chest = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, chest_list);
                                adapter_chest.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_chest);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(adapter_part.getItem(position).equals("어깨")){
                                final ArrayAdapter<String> adapter_shoulder = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, shoulder_list);
                                adapter_shoulder.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_shoulder);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(adapter_part.getItem(position).equals("가슴")){
                                final ArrayAdapter<String> adapter_chest = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, chest_list);
                                adapter_chest.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_chest);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(adapter_part.getItem(position).equals("이두")){
                                final ArrayAdapter<String> adapter_biceps = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, biceps_list);
                                adapter_biceps.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_biceps);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(adapter_part.getItem(position).equals("삼두")){
                                final ArrayAdapter<String> adapter_triceps = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, triceps_list);
                                adapter_triceps.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_triceps);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }
                            else if(adapter_part.getItem(position).equals("하체")){
                                final ArrayAdapter<String> adapter_leg = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, leg_list);
                                adapter_leg.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_leg);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(adapter_part.getItem(position).equals("복근")){
                                final ArrayAdapter<String> adapter_abs = new ArrayAdapter<String>(Page8.this, R.layout.spinner_selected, abs_list);
                                adapter_abs.setDropDownViewResource(R.layout.spinner_item);
                                muscle.setAdapter(adapter_abs);
                                muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        exer_list.add(muscle.getSelectedItem().toString());
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
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        String finalWEIGHT_Value = WEIGHT_Value;

        muscle_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exer_mode.equals("유산소")) {
                    String oxy_velo = heft.getText().toString();
                    String oxy_time = reps.getText().toString();
                    String oxy_cal = "";
                    if (oxy_velo.length() == 0 || oxy_time.length() == 0) {
                        Toast.makeText(getApplicationContext(), "속도와 시간을 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (exer_list.get(exer_list.size() - 1).equals("런닝머신")) {
                            oxy_cal = String.format("%.2f", (CalRunning(oxy_velo, finalWEIGHT_Value, oxy_time) / 100));
                        } else if (exer_list.get(exer_list.size() - 1).equals("사이클")) {
                            oxy_cal = String.format("%.2f", CalCycle(oxy_velo, finalWEIGHT_Value, oxy_time));
                        } else if (exer_list.get(exer_list.size() - 1).equals("스탭퍼")) {
                            oxy_cal = String.format("%.2f", CalStep(finalWEIGHT_Value, oxy_time));
                        }
                        items_act.add(writeFileOxyExer(exer_list.get(exer_list.size() - 1), oxy_velo, oxy_time, oxy_cal));
                    }
                }
                else if(exer_mode.equals("웨이트")) {
                    String muscle_heft = heft.getText().toString();
                    String muscle_reps = reps.getText().toString();
                    String muscle_set = set.getText().toString();
                    if (muscle_heft.length() == 0 || muscle_reps.length() == 0||muscle_set.length()==0) {
                        Toast.makeText(getApplicationContext(), "무게, 횟수와 세트수를 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    else
                        items_act.add(writeFileMuscleExer(exer_list.get(exer_list.size()-1),muscle_heft,muscle_reps,muscle_set));
                }
                print_list.add(exer_list.get(exer_list.size()-1));
                adapter_act.notifyDataSetChanged();
                saveItemsToFile(File2,items_act);
                heft.getText().clear();
                reps.getText().clear();
                set.getText().clear();

                String rFile2=readFile(File2);
                if(rFile2!=null)
                    adapter_act.notifyDataSetChanged();
            }
        });

        clear_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count;
                int checkedIndex;
                int num;
                count = adapter_act.getCount();
                if(count > 0){
                    checkedIndex = training_show.getCheckedItemPosition();
                    num=checkedIndex;
                    if(checkedIndex > -1 && checkedIndex <= count){
                        Toast.makeText(getApplicationContext(),  print_list.get(num)+"이(가) 삭제되었습니다", Toast.LENGTH_SHORT).show();
                        print_list.remove(num);
                        items_act.remove(checkedIndex);
                        training_show.clearChoices();
                        adapter_act.notifyDataSetChanged();
                        saveItemsToFile(File2,items_act);
                        training_show.setSelector(new PaintDrawable(Color.TRANSPARENT));
                        flag=!flag;
                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private String writeFileMuscleExer(String exer_name, String muscle_heft, String muscle_reps, String muscle_set) {
        String result;
        result = exer_name+": "+muscle_heft+"kg "+muscle_reps+"ea "+muscle_set+"sets";
        return  result;
    }

    private String writeFileOxyExer(String exer_name, String oxy_velo, String oxy_time, String oxy_cal) {
        String result;
        result = exer_name+": "+oxy_velo+"km/h "+oxy_time+"min "+oxy_cal+"kcal";
        return result;
    }


    private double CalStep(String weight_value, String oxy_time) {
        double w=Double.parseDouble(weight_value);
        double t=Double.parseDouble(oxy_time);
        double result=w*0.07*t;
        return result;
    }

    private double CalCycle(String oxy_velo, String weight_value, String oxy_time) {
        double d=Double.parseDouble(oxy_velo);
        double t=Double.parseDouble(oxy_time);
        double m=Double.parseDouble(weight_value);
        double v=d/(t/60);
        double result=0;
        if(v<13){
            result = 0.05*m*t;
        }
        else if(13<=v&&v<16){
            result = 0.065*m*t;
        }
        else if(16<=v&&v<19){
            result = 0.0783*m*t;
        }
        else if(19<=v&&v<22){
            result = 0.0939*m*t;

        }
        else if(22<=v&&v<24){
            result = 0.113*m*t;
        }
        else if(24<=v&&v<26){
            result = 0.124*m*t;
        }
        else if(26<=v&&v<27){
            result = 0.136*m*t;
        }
        else if(27<=v&&v<29){
            result = 0.149*m*t;
        }
        else if(29<=v&&v<31){
            result = 0.163*m*t;
        }
        else if(31<=v&&v<32){
            result = 0.176*m*t;
        }
        else if(32<=v&&v<34){
            result = 0.196*m*t;
        }
        else if(34<=v&&v<37){
            result = 0.215*m*t;
        }
        else if(37<=v&&v<40){
            result = 0.259*m*t;
        }
        else if(40<=v){
            result = 0.311*m*t;
        }
        return result;
    }

    private double CalRunning(String oxy_velo, String WEIGHT_Value, String oxy_time) {
        double v=Double.parseDouble(oxy_velo);
        double w=Double.parseDouble(WEIGHT_Value);
        double t=Double.parseDouble(oxy_time);
        double result=0;
        if(v>=8.1){
            result = 0.0175*((0.2*(v*16.667) + 3.5)/3.5)*(w*100)*t;
        }
        else {
            result = 0.0175 * ((0.1 * (v * 16.667) + 3.5) / 3.5) * (w * 100) * t;
        }
        return result;
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
        File file = new File(getFilesDir(), fname);
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
                    int idx=str.indexOf(":");
                    String tmp_str=str.substring(0, idx);
                    print_list.add(tmp_str);
                }
                bufrd.close() ;
                fr.close() ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
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

}