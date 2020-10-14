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

import androidx.appcompat.app.AppCompatActivity;

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
    TextInputEditText pickDate,dropDate, pickTime, dropTime;
    TextInputLayout tvDate;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener mDate;
    private SimpleDateFormat dateFormatter;
    ImageButton btn;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    MaterialButton mb2, mb3, mb4,btnSearch;
    MaterialTextView text;

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
        btnSearch = findViewById(R.id.search);

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
                        SimpleDateFormat f24Hours = new SimpleDateFormat(
                                "HH:mm"
                        );
                        try{
                            Date date = f24Hours.parse(time);
                            //Initialize 12 hours time format
                            SimpleDateFormat f12Hours = new SimpleDateFormat(
                                    "hh:mm aa"
                            );
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
                        SimpleDateFormat f24Hours = new SimpleDateFormat(
                                "HH:mm"
                        );
                        try{
                            Date date = f24Hours.parse(time);
                            //Initialize 12 hours time format
                            SimpleDateFormat f12Hours = new SimpleDateFormat(
                                    "hh:mm aa"
                            );
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
            }
        });

        mb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                text.setText("30 - 40");
            }
        });

        mb4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                text.setText("40+");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(DateActivity.this,PickCar.class);
                startActivity(search);
            }
        });
    }
}