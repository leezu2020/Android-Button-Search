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
    private RadioButton rb, rb_n, rb_d;
    static String srchEngine;
    private static final int REQUEST_CODE = 1;
    private static String number = "";

    // 확인버튼 안누르고 뒤로가기 눌렀을때, 값 저장
    @Override
    public void onBackPressed() {
        srchword = findViewById(R.id.editsrchword);
        if(srchword.getText().toString().isEmpty()){
            Toast.makeText(activity_setting.this,"검색어를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "변경 완료",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("srchword"+number,srchword.getText().toString());
            intent.putExtra("srchEngine"+number, srchEngine);
            intent.putExtra("number",number);
            setResult(RESULT_OK, intent);
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);

        rg = findViewById(R.id.rg);
        srchword = findViewById(R.id.editsrchword);
        backbtn = findViewById(R.id.back_main);

        Intent intent_get = getIntent();
        number = intent_get.getStringExtra("num");
        srchword.setText(intent_get.getStringExtra("srchbtn"+number));
        rb_n = findViewById(R.id.naverbtn);
        rb_d = findViewById(R.id.daumbtn);
        switch (intent_get.getStringExtra("srchEngine"+number)){
            case "naver":
                srchEngine = "http:/m.search.naver.com/search.naver?query=";
                rg.check(rb_n.getId());
                break;
            case "daum":
                srchEngine = "http:/m.search.daum.net/search?w=tot&q=";
                rg.check(rb_d.getId());
                break;
        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("srchword"+number,srchword.getText().toString());
                intent.putExtra("srchEngine"+number, srchEngine);
                intent.putExtra("number",number);
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
