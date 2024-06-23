package com.example.common;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.Adapters.TreatmentAdapter;
import com.example.common.Utilities.SharedPreferencesManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public abstract class MainActivityBase extends AppCompatActivity {

    protected SharedPreferencesManager sharedPreferencesManager;
    private EditText enter_name;
    private EditText enter_date;
    private EditText treatmentEditText;
    private EditText enter_price;
    private MaterialButton submit_treatment;
    private ArrayList<Treatment> allTreatments;
    private RecyclerView treatmentRecyclerView;
    protected String listName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        SharedPreferencesManager.init(this);
        sharedPreferencesManager = SharedPreferencesManager.getInstance();
        listName = getListName();
        allTreatments = sharedPreferencesManager.getTreatmentList(listName);
        if (allTreatments == null)
            allTreatments = new ArrayList<>();
        setHint();

        initViews();

        hideKeyboard();

    }

    private void setHint() {
        enter_name.setHint(getNameHint());
        enter_date.setHint(getDateHint());
        treatmentEditText.setHint(getTreatmentHint());
        enter_price.setHint(getPriceHint());
    }

    private void initViews() {
        updateAdapter();
        submit_treatment.setOnClickListener(v -> {
            if (validateInputs()) {
                Treatment treatment = new Treatment()
                        .setName(enter_name.getText().toString())
                        .setDate(enter_date.getText().toString())
                        .setDescription(treatmentEditText.getText().toString())
                        .setPrice(enter_price.getText().toString());
                allTreatments.add(treatment);
                sharedPreferencesManager.putTreatmentList(listName, allTreatments);
                updateAdapter();

                enter_name.setText("");
                enter_date.setText("");
                treatmentEditText.setText("");
                enter_price.setText("");
            }
        });

        findViewById(R.id.main).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                hideKeyboard();
            }
            return false;
        });
    }


private void updateAdapter() {
    TreatmentAdapter treatmentAdapter = new TreatmentAdapter(this, allTreatments);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    treatmentRecyclerView.setLayoutManager(linearLayoutManager);
    treatmentRecyclerView.setAdapter(treatmentAdapter);
}

private void findViews() {
    enter_name = findViewById(R.id.enter_name);
    enter_date = findViewById(R.id.enter_date);
    treatmentEditText = findViewById(R.id.treatmentEditText);
    enter_price = findViewById(R.id.enter_price);
    submit_treatment = findViewById(R.id.submit_treatment);
    treatmentRecyclerView = findViewById(R.id.treatRecyclerView);
}

protected abstract String getListName();

protected abstract String getNameHint();

protected abstract String getDateHint();

protected abstract String getTreatmentHint();

protected abstract String getPriceHint();

private boolean validateInputs() {
    if (enter_name.getText().toString().trim().isEmpty()) {
        showToast("Cannot be empty");
        return false;
    }
    if (!isValidDate(enter_date.getText().toString().trim())) {
        showToast("Invalid date. Please use the format dd-MM-yyyy");
        return false;
    }
    if (treatmentEditText.getText().toString().trim().isEmpty()) {
        showToast("Treatment description cannot be empty");
        return false;
    }
    if (enter_price.getText().toString().trim().isEmpty()) {
        showToast("Price cannot be empty");
        return false;
    }
    return true;
}

private boolean isValidDate(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    sdf.setLenient(false);
    try {
        sdf.parse(date);
        return true;
    } catch (ParseException e) {
        return false;
    }
}

private void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
}

private void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

}