package com.example.notepad_client.activity;

import android.os.Bundle;

import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Gson getGson(){
        return GsonSingleton.gson;
    }

    public static class GsonSingleton{
        private static final Gson gson = new Gson();
    }
}
