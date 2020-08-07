package com.arifpurnama.onlinelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.arifpurnama.onlinelearning.model.Category;
import com.arifpurnama.onlinelearning.retrofit.ApiInterface;
import com.arifpurnama.onlinelearning.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup retrofit for network call fething data

        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<List<Category>> call = apiInterface.getAllCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categoryList = response.body();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Response from server", Toast.LENGTH_SHORT).show();
            }
        });

        //lets run this app and check server is responding or not
        
    }
}