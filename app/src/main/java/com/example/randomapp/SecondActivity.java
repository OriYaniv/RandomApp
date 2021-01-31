package com.example.randomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private String numData;
    private Button buttonNumber;
    private EditText editTextNumber;
    private TextView textViewNumber, textViewGrade, textViewTimer;
    private int random1, random2, counterResult = 0, grade = 0;
    private boolean stopRandom = false, num = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initUI();
        initListeners();
    }

    private void initUI() {
        buttonNumber = findViewById(R.id.buttonNumber);
        editTextNumber = findViewById(R.id.editTextNumber);
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewGrade = findViewById(R.id.textViewGrade);
        textViewTimer = findViewById(R.id.textViewTimer);

        numData = getIntent().getStringExtra("num");

        randomNumbers();

        textViewNumber.setText(random1 + " x " + random2 + " = ");

        startCountDownTimer();
    }

    private void initListeners() {
        buttonNumber.setOnClickListener(this);
    }

    private void startCountDownTimer() {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textViewTimer.setText("");
                textViewGrade.setText(String.valueOf(grade));
            }
        }.start();
    }

    private void randomNumbers() {
        if (!stopRandom) {
            random1 = new Random().nextInt(Integer.parseInt(numData)) + 1;
            random2 = new Random().nextInt(Integer.parseInt(numData)) + 1;
            stopRandom = true;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNumber) {
            if (editTextNumber.getText().toString().matches("")) {
                editTextNumber.setError("You must write something!");
            } else {
                if (random1 * random2 == Integer.parseInt(editTextNumber.getText().toString())) {
                    stopRandom = false;

                    if (num) {
                        grade += 20;
                    }

                    num = true;
                    counterResult++;

                    if (counterResult == 5) {
                        textViewGrade.setText(String.valueOf(grade));
                    }

                    randomNumbers();

                    textViewNumber.setText(random1 + " x " + random2 + " = ");

                    editTextNumber.setText("");
                } else {
                    Toast.makeText(this, "Your answer is wrong", Toast.LENGTH_LONG).show();

                    num = false;
                }
            }
        }
    }

}
