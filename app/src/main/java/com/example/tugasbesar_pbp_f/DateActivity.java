package com.example.tugasbesar_pbp_f;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {
    private TextInputEditText pickDate,dropDate, pickTime, dropTime;
    private TextInputLayout tvDate;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener mDate;
    private SimpleDateFormat dateFormatter;
    private ImageButton btn;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    private MaterialButton mb2, mb3, mb4,btnSearch;
    private MaterialTextView text,textAlamat;
    public Bundle mBundle;
    public String tampilAlamat;
    public Date satu, dua;
    public static long elapsedDays,elapsedHours,elapsedMinutes,elapsedSeconds;
    public static String pick_Up_Location="", pick_Up_date="",drop_Off_Date="",
            pick_Up_Time="",drop_Off_Time="",driver_Age="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDate = findViewById(R.id.pickDate_layout);
        pickDate = findViewById(R.id.pickDateInput);
        dropDate = findViewById(R.id.dropDateInput);
        pickTime = findViewById(R.id.pickTimesInput);
        dropTime = findViewById(R.id.dropTimesInput);
        btn = findViewById(R.id.back);
        mb2 = findViewById(R.id.materialButton2);
        mb3 = findViewById(R.id.materialButton3);
        mb4 = findViewById(R.id.materialButton4);
        text = findViewById(R.id.text);
        textAlamat = findViewById(R.id.materialTextView2);
        btnSearch = findViewById(R.id.search);
        mBundle = getIntent().getBundleExtra("regis");
        tampilAlamat = mBundle.getString("alamat");
        textAlamat.setText(tampilAlamat);

        pick_Up_Location = mBundle.getString("alamat");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent date = new Intent(DateActivity.this, MainActivity.class);
                startActivity(date);
            }
        });

        pickDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(DateActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        pickDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        dropDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(DateActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dropDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                datePickerDialog.updateDate(year, month,day);
                datePickerDialog.show();
            }
        });

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(DateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Initialize hour and minute
                        t1Hour = hourOfDay;
                        t1Minute = minute;
                        //Store hout and minute in string
                        String time = t1Hour + ":" +t1Minute;
                        //Set hour and minute
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try{
                            Date date = f24Hours.parse(time);
                            //Initialize 12 hours time format
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                            pickTime.setText(f12Hours.format(date));
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });

        dropTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(DateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Initialize hour and minute
                        t2Hour = hourOfDay;
                        t2Minute = minute;
                        //Store hout and minute in string
                        String time = t2Hour + ":" +t2Minute;
                        //Set hour and minute
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try{
                            Date date = f24Hours.parse(time);
                            //Initialize 12 hours time format
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                            dropTime.setText(f12Hours.format(date));
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hour,t2Minute);
                timePickerDialog.show();
            }
        });

        mb2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                text.setText("18 - 29");
                driver_Age = "18 - 29";
            }
        });

        mb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                text.setText("30 - 40");
                driver_Age = "30 - 40";
            }
        });

        mb4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                text.setText("40+");
                driver_Age = "40+";
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

                String a = pickDate.getText().toString();
                String b = dropDate.getText().toString();
                String c = pickTime.getText().toString();
                String d = dropTime.getText().toString();
                String date1 = a + " " + c;
                String date2 = b + " " + d;

                pick_Up_date    =   pickDate.getText().toString();
                drop_Off_Date   =   dropDate.getText().toString();
                pick_Up_Time    =   pickTime.getText().toString();
                drop_Off_Time   =   dropTime.getText().toString();

                try {
                    satu = simpleDateFormat.parse(date1);
                    dua = simpleDateFormat.parse(date2);
                    long diff = dua.getTime() - satu.getTime();

                    //hasil.setText(String.valueOf(diff));

                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long daysInMilli = hoursInMilli * 24;

                    elapsedDays = diff / daysInMilli;
                    diff = diff % daysInMilli;

                    elapsedHours = diff / hoursInMilli;
                    diff = diff % hoursInMilli;

                    elapsedMinutes = diff / minutesInMilli;
                    diff = diff % minutesInMilli;

                    elapsedSeconds = diff / secondsInMilli;

                    //hasil2.setText(String.valueOf(elapsedDays)  + " days " + String.valueOf(elapsedHours) +" hours " + String.valueOf(elapsedMinutes) + " minutes");
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if(pickDate.getText().toString().isEmpty() || dropDate.getText().toString().isEmpty()
                        || pickTime.getText().toString().isEmpty() || dropTime.getText().toString().isEmpty()
                        || text.getText().toString().isEmpty()) {
                    Toast.makeText(DateActivity.this, "Fill the Empty Fields", Toast.LENGTH_SHORT).show();
                } else if (dua.getTime() < satu.getTime()) {
                    Toast.makeText(DateActivity.this, "Drop-out Date Greater than Pick-up Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent search = new Intent(DateActivity.this,PickCar.class);
                    Bundle mBundle = new Bundle();

                    mBundle.putLong("hari",elapsedDays);
                    mBundle.putLong("jam",elapsedHours);
//                    mBundle.putString("pick_Up_Location", tampilAlamat);
//                    mBundle.putString("pick_Up_Date",pickDate.getText().toString());
//                    mBundle.putString("drop_Off_Date",dropDate.getText().toString());
//                    mBundle.putString("pick_Up_Time",pickTime.getText().toString());
//                    mBundle.putString("drop_Off_Time",dropTime.getText().toString());


                    search.putExtra("durasi",mBundle);
                    startActivity(search);
                }



//                Intent pick = new Intent(DateActivity.this, Detail.class);
//                Bundle mBundle1 = new Bundle();
//                mBundle1.putLong("hari",elapsedDays);
//                mBundle1.putLong("jam",elapsedHours);
//                pick.putExtra("durasi1",mBundle1);
            }
        });
    }
}