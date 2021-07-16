package com.example.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    Button btnAdd, btnRetrieve;
    RadioGroup RG;
    RadioButton radio1, radio2, radio3, radio4, radio5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        btnAdd = findViewById(R.id.btnAdd);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        RG = findViewById(R.id.rg);
        radio1 = findViewById(R.id.radioButton1);
        radio2 = findViewById(R.id.radioButton2);
        radio3 = findViewById(R.id.radioButton3);
        radio4 = findViewById(R.id.radioButton4);
        radio5 = findViewById(R.id.radioButton5);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataTitle = etTitle.getText().toString();
                String dataSinger = etSinger.getText().toString();
                int dataYear = Integer.parseInt(etYear.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                int Star = -1;
                if (radio1.isChecked()) {
                    Star = 1;
                } else if (radio2.isChecked()) {
                    Star = 2;
                } else if (radio3.isChecked()) {
                    Star = 3;
                } else if (radio4.isChecked()) {
                    Star = 4;
                } else if (radio5.isChecked()) {
                    Star = 5;
                }
                long inserted_id = dbh.insertSong(dataTitle, dataSinger, dataYear, Star);
                if (inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }
}