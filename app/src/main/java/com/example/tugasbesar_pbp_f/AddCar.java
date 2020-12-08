package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCar extends AppCompatActivity {
    private EditText inCarName,inCarType, inPassanger,inBags,inFuel,inTotal,inCarPlat;
    private MaterialButton btnCreate, btnUnggah;
    private String filePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        if(ContextCompat.checkSelfPermission(AddCar.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddCar.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    100);

        }

        inCarName = findViewById(R.id.inCarName);
        inCarType = findViewById(R.id.inCarType);
        inPassanger = findViewById(R.id.inPassanger);
        inBags = findViewById(R.id.inBags);
        inFuel = findViewById(R.id.inFuel);
        inTotal = findViewById(R.id.inTotal);
        inCarPlat = findViewById(R.id.inCarPlat);
        btnCreate = findViewById(R.id.btnSaveCar);
        btnUnggah = findViewById(R.id.btnGaleri);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCar();
            }
        });

        btnUnggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent();
               i.setType("image/*");
               i.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(i,"Select Car Picture"),9544);
            }
        });
    }

    private void saveCar(){


        RequestBody carNamePart = RequestBody.create(MultipartBody.FORM,inCarName.getText().toString());
        RequestBody carTypePart = RequestBody.create(MultipartBody.FORM,inCarType.getText().toString());
        RequestBody carPassengerPart = RequestBody.create(MultipartBody.FORM,inPassanger.getText().toString());
        RequestBody carBagsPart = RequestBody.create(MultipartBody.FORM,inBags.getText().toString());
        RequestBody carFuelPart = RequestBody.create(MultipartBody.FORM,inFuel.getText().toString());
        RequestBody carTotalPart = RequestBody.create(MultipartBody.FORM,inTotal.getText().toString());
        RequestBody carPlatPart = RequestBody.create(MultipartBody.FORM,inCarPlat.getText().toString());

        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),file);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("imgURL",file.getName(),requestBody);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CarResponse> add = apiService.createCar(carNamePart,
                carTypePart, carPassengerPart, carBagsPart,carFuelPart,
                carTotalPart,partImage,carPlatPart);
        add.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                Toast.makeText(AddCar.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                Toast.makeText(AddCar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if(requestCode == 9544)
            {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage,imageprojection,null,null,null);

                if (cursor != null)
                {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    filePath = cursor.getString(indexImage);

                    if(filePath != null)
                    {
                        File image = new File(filePath);
                        //imgHolder.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                    }
                }
            }
        }
    }


}