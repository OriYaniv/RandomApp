package com.example.randomapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonNumber;
    private EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();
    }

    private void initUI() {
        buttonNumber = findViewById(R.id.buttonNumber);
        editTextNumber = findViewById(R.id.editTextNumber);
    }

    private void initListeners() {
        buttonNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNumber) {
            if (editTextNumber.getText().toString().matches("")) {
                editTextNumber.setError("You must write something!");
            } else {
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("num", editTextNumber.getText().toString());
                startActivity(intent);
            }
        }
    }

}
