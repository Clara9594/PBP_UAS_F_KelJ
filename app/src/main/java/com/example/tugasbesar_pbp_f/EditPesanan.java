package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPesanan extends AppCompatActivity {
    private ImageButton ibBack;
    private EditText etPickDate, etDropDate, etPickTime, etDropTime;
    private MaterialTextView etAlamat;
    private String sPickDate, sDropDate, sPickTime, sDropTime, sDriverAge,sPlatNomor,sAlamat;
    private MaterialButton mb2, mb3, mb4,btnEdit;
    private Bundle mBundle;
    private int sIdBooking;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    private MaterialTextView driverAgeText;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    public Date satu,dua;
    public int Total,sHarga;
    private long elapsedDays,elapsedHours,elapsedMinutes,elapsedSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pesanan);
        mBundle = getIntent().getBundleExtra("dataEdit");
        sIdBooking = mBundle.getInt("ID");
        loadBookingById(sIdBooking);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        ibBack = findViewById(R.id.back);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        etPickDate = findViewById(R.id.pickDateInput);
        etDropDate = findViewById(R.id.dropDateInput);
        etPickTime = findViewById(R.id.pickTimesInput);
        etDropTime = findViewById(R.id.dropTimesInput);
        driverAgeText = findViewById(R.id.text);
        etAlamat    = findViewById(R.id.materialTextView2);
        mb2 = findViewById(R.id.materialButton2);
        mb3 = findViewById(R.id.materialButton3);
        mb4 = findViewById(R.id.materialButton4);
        btnEdit = findViewById(R.id.btnEdit);

        etPickDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EditPesanan.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        etPickDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        etDropDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EditPesanan.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        etDropDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                datePickerDialog.updateDate(year, month,day);
                datePickerDialog.show();
            }
        });

        etPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditPesanan.this, new TimePickerDialog.OnTimeSetListener() {
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
                            etPickTime.setText(f12Hours.format(date));
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

        etDropTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditPesanan.this, new TimePickerDialog.OnTimeSetListener() {
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
                            etDropTime.setText(f12Hours.format(date));
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
                driverAgeText.setText("18 - 29");

            }
        });

        mb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                driverAgeText.setText("30 - 40");

            }
        });

        mb4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                driverAgeText.setText("40+");
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

                String a = etPickDate.getText().toString();
                String b = etDropDate.getText().toString();
                String c = etPickTime.getText().toString();
                String d = etDropTime.getText().toString();
                String date1 = a + " " + c;
                String date2 = b + " " + d;

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

                if(etPickDate.getText().toString().isEmpty() || etDropDate.getText().toString().isEmpty()
                        || etPickTime.getText().toString().isEmpty() || etDropTime.getText().toString().isEmpty()
                        || driverAgeText.getText().toString().isEmpty()) {
                    Toast.makeText(EditPesanan.this, "Fill the Empty Fields", Toast.LENGTH_SHORT).show();
                } else if (dua.getTime() < satu.getTime()) {
                    Toast.makeText(EditPesanan.this, "Drop-out Date Greater than Pick-up Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    int hari = (int) elapsedDays;
                    int jam = (int) elapsedHours;
                    if(hari==0 && jam!=0) {
                        //a2.setText("Rp. "+String.valueOf(harga));
                        Toast.makeText(EditPesanan.this, "Harga Tidak Berubah", Toast.LENGTH_SHORT).show();
                    }
                    else if(hari!=0 && jam!=0){

                        Total = sHarga * (hari+1);
                        //a2.setText("Rp. "+String.valueOf(harga));
                    }

                    else if(hari!=0 && jam==0)
                    {
                        Total = sHarga * hari;
                        //a2.setText("Rp. "+String.valueOf(harga));
                    }
                    saveBooking();
                }
            }
        });
    }

    private void saveBooking(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> add = apiService.updateBooking(String.valueOf(sIdBooking),etPickDate.getText().toString(),
                etDropDate.getText().toString(), etPickTime.getText().toString(), etDropTime.getText().toString(), driverAgeText.getText().toString(),Total);
        add.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
//                Toast.makeText(EditPesanan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
                Intent i= new Intent(EditPesanan.this,MainActivity.class);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(EditPesanan.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }

    private void loadBookingById(int id){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> add = apiService.getBookingById(String.valueOf(id),"data");

        add.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                sPickDate = response.body().getBookings().get(0).getPick_Up_Date();
                sDropDate = response.body().getBookings().get(0).getDrop_Off_Date();
                sPickTime = response.body().getBookings().get(0).getPick_Up_Time();
                sDropTime = response.body().getBookings().get(0).getDrop_Off_Time();
                sDriverAge = response.body().getBookings().get(0).getDriver_Age();
                sPlatNomor = response.body().getBookings().get(0).getPlat_nomor();
                sAlamat     = response.body().getBookings().get(0).getPick_Up_Location();

                etPickDate.setText(sPickDate);
                etDropDate.setText(sDropDate);
                etPickTime.setText(sPickTime);
                etDropTime.setText(sDropTime);
                driverAgeText.setText(sDriverAge);
                etAlamat.setText(sAlamat);

                loadCarByPlat(sPlatNomor);
                //String date1 = sPickDate + " " + sPickTime;
                //String date2 = sDropDate + " " + sDropTime;
                //simpleDateFormat
                //progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(EditPesanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }

    private void loadCarByPlat(String plat){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CarResponse> add = apiService.getCarByPlat(plat,"data");

        add.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                sHarga = response.body().getCars().get(0).getHarga();
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                Toast.makeText(EditPesanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }
}