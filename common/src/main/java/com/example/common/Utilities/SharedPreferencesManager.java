package com.example.common.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.common.Treatment;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferencesManager {

    private static volatile SharedPreferencesManager instance = null;
    private SharedPreferences sharedPref;
    private Gson gson;



    private SharedPreferencesManager(Context context) {
        this.sharedPref = context.getSharedPreferences("COMMON_PREFS", Context.MODE_PRIVATE);
        this.gson = new Gson();

    }

    public static void init(Context context) {
        synchronized (SharedPreferencesManager.class) {
            if (instance == null) {
                instance = new SharedPreferencesManager(context);
            }
        }
    }

    public static SharedPreferencesManager getInstance() {
        return instance;
    }

    public void putTreatmentList(String key, ArrayList<Treatment> list) {
        String json = gson.toJson(list);
        sharedPref.edit().putString(key, json).apply();
    }

    public ArrayList<Treatment> getTreatmentList(String key) {
        String json = sharedPref.getString(key, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<ArrayList<Treatment>>() {}.getType();
        return gson.fromJson(json, type);
    }


}
