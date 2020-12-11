package com.example.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int CODE = 1;
    private Button srchbtn1;
    private Button srchbtn2;
    private Button srchbtn3;
    private static String srchEngine1 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine2 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine3 = "http:/m.search.naver.com/search.naver?query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srchbtn1 = findViewById(R.id.searchBtn1);
        srchbtn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                //버튼 값 넘겨주기
                intent.putExtra("srchbtn1",srchbtn1.getText().toString());
                startActivityForResult(intent, CODE);
                return true;
            }
        });

        //브라우저 검색
        srchbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchEngine1 + srchbtn1.getText().toString()));
                startActivity(browserIntent);
            }
        });

        srchbtn2 = findViewById(R.id.searchBtn2);
        srchbtn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                startActivityForResult(intent, CODE);

                return true;
            }
        });

        //브라우저 검색
        srchbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchEngine2 + srchbtn2.getText().toString()));
                startActivity(browserIntent);
            }
        });

        srchbtn3 = findViewById(R.id.searchBtn3);
        srchbtn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                startActivityForResult(intent, CODE);

                return true;
            }
        });

        //브라우저 검색
        srchbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchEngine3 + srchbtn3.getText().toString()));
                startActivity(browserIntent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Toast.makeText(getApplicationContext(), "변경 완료",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "수신 실패",Toast.LENGTH_SHORT).show();
        }

        if(requestCode == CODE){
            srchbtn1.setText(data.getStringExtra("srchword1"));
            srchEngine1 = data.getStringExtra("srchEngine1");
        }
    }
}
