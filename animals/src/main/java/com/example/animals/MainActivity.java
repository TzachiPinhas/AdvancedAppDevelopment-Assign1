package com.example.animals;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.common.MainActivityBase;

public class MainActivity extends MainActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getListName() {
        return "ANIMAL_TREATMENT_LIST";
    }

    protected String getNameHint() {
        return "Please enter the animal's name:";
    }

    @Override
    protected String getDateHint() {
        return "Please enter the date:";
    }

    @Override
    protected String getTreatmentHint() {
        return "What treatment did the animal receive?";
    }

    @Override
    protected String getPriceHint() {
        return "Please enter the treatment price:";
    }
}