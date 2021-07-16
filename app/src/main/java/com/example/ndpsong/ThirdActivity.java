package com.example.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etSinger, etYear;
    Button btnUpdate, btnRemove, btnCancel;
    RadioGroup RG;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRemove = findViewById(R.id.btnRemove);
        radio1 = findViewById(R.id.radioButton1);
        radio2 = findViewById(R.id.radioButton2);
        radio3 = findViewById(R.id.radioButton3);
        radio4 = findViewById(R.id.radioButton4);
        radio5 = findViewById(R.id.radioButton5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(data.getId() + "");
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear() + "");
        if (data.getStars() == 1) {
            radio1.setChecked(true);
        } else if (data.getStars() == 2) {
            radio2.setChecked(true);
        } else if (data.getStars() == 3) {
            radio3.setChecked(true);
        } else if (data.getStars() == 4) {
            radio4.setChecked(true);
        } else if (data.getStars() == 5) {
            radio5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setSingers(etSinger.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.setYear(Integer.valueOf(etYear.getText().toString()));
                if (radio1.isChecked()) {
                    data.setStars(1);
                } else if (radio2.isChecked()) {
                    data.setStars(2);
                } else if (radio3.isChecked()) {
                    data.setStars(3);
                } else if (radio4.isChecked()) {
                    data.setStars(4);
                } else if (radio5.isChecked()) {
                    data.setStars(5);
                }
                dbh.updateSong(data);
                Toast.makeText(ThirdActivity.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                Toast.makeText(ThirdActivity.this, "Delete successful",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}