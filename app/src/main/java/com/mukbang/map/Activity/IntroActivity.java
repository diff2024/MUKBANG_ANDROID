package com.mukbang.map.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mukbang.map.DTO.ChannelData;
import com.mukbang.map.DTO.RestaurantData;
import com.mukbang.map.R;
import com.mukbang.map.Setting.BackAPI;
import com.mukbang.map.Setting.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IntroActivity extends AppCompatActivity {
    Retrofit retrofit = RetrofitClient.getInstance();
    BackAPI backAPI = retrofit.create(BackAPI.class);
    public static String USER_ID = "";
    public static String ANDROID_ID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); //xml , java 소스 연결

        ANDROID_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        backAPI.UserID(ANDROID_ID).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response){
                if (response.isSuccessful() && response.body() != null){
                    USER_ID = response.body().toString();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent); //인트로 실행 후 바로 MainActivity로 넘어감.
                finish();
            }
        }, 1500); //1초 후 인트로 실행
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
