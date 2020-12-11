package com.example.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class activity_setting extends AppCompatActivity {

    private RadioGroup rg;
    private EditText srchword;
    private Button backbtn;
    private RadioButton rb;
    static String srchEngine;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);

        rg = findViewById(R.id.rg);
        srchword = findViewById(R.id.editsrchword);
        backbtn = findViewById(R.id.back_main);
        srchEngine = "http:/m.search.naver.com/search.naver?query=";

        Intent intent_get = getIntent();
        srchword.setText(intent_get.getStringExtra("srchbtn1"));
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("srchword1",srchword.getText().toString());
                intent.putExtra("srchEngine1", srchEngine);
                setResult(RESULT_OK, intent);
                finish(); // 현재 액티비티 종료
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb = findViewById(i);
                switch (i) {
                    case R.id.naverbtn:
                        srchEngine = "http:/m.search.naver.com/search.naver?query=";
                        break;
                    case R.id.daumbtn:
                        srchEngine = "http:/m.search.daum.net/search?w=tot&q=";
                        break;
                }
            }
        });
    }


}
