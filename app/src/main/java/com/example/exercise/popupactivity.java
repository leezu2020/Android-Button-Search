package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class popupactivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_popupactivity);

        Intent intent = getIntent();
        editText = findViewById(R.id.editWord);
        editText.setText(intent.getStringExtra("srchword"));

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
           RadioButton selectedRd = findViewById(radioGroup.getCheckedRadioButtonId());
           String selectedValue ="http:/m.search.naver.com/search.naver?query=";

            if(i== R.id.naver){
                //naver 눌렀을때 button의 text값을 naver 링크로
                selectedValue = "http:/m.search.naver.com/search.naver?query=";
            }
            else if(i == R.id.daum){
                //daum 눌렀을때 button의 text값을 daum 링크로
                selectedValue = "http:/m.search.daum.com/search.daum?query=";
            }

            Intent intent = new Intent(popupactivity.this, activity_setting.class);
            intent.putExtra("srchEngine",selectedValue);
        }
    };
}
