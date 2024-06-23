package com.example;

import android.os.Bundle;

import com.example.advancedapplicationdevelopment_assign1.R;
import com.example.common.MainActivityBase;

public class MainActivity extends MainActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected String getListName() {
        return "CAR_TREATMENT_LIST"; // Change this name for each specific application
    }

    @Override
    protected String getNameHint() {
        return "Please enter the car's plate number:";
    }

    @Override
    protected String getDateHint() {
        return "Please enter the date:";
    }

    @Override
    protected String getTreatmentHint() {
        return "What treatment did the car receive?";
    }

    @Override
    protected String getPriceHint() {
        return "Please enter the treatment price:";
    }
}
