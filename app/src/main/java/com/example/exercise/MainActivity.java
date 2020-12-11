package com.example.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button srchbtn4;
    private static String srchEngine1 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine2 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine3 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine4 = "http:/m.search.naver.com/search.naver?query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srchbtn1 = findViewById(R.id.searchBtn1);
        srchbtn2 = findViewById(R.id.searchBtn2);
        srchbtn3 = findViewById(R.id.searchBtn3);
        srchbtn4 = findViewById(R.id.searchBtn4);

        srchbtn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                //버튼 값 넘겨주기
                intent.putExtra("num","1");
                if(srchEngine1.contains("naver"))
                    intent.putExtra("srchEngine1","naver");
                else if(srchEngine1.contains("daum"))
                    intent.putExtra("srchEngine1","daum");
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

        srchbtn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                //버튼 값 넘겨주기
                intent.putExtra("num","2");
                if(srchEngine2.contains("naver"))
                    intent.putExtra("srchEngine2","naver");
                else if(srchEngine2.contains("daum"))
                    intent.putExtra("srchEngine2","daum");
                intent.putExtra("srchbtn2",srchbtn2.getText().toString());
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


        srchbtn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                //버튼 값 넘겨주기
                intent.putExtra("num","3");
                if(srchEngine3.contains("naver"))
                    intent.putExtra("srchEngine3","naver");
                else if(srchEngine3.contains("daum"))
                    intent.putExtra("srchEngine3","daum");
                intent.putExtra("srchbtn3",srchbtn3.getText().toString());
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


        srchbtn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                //버튼 값 넘겨주기
                intent.putExtra("num","4");
                if(srchEngine4.contains("naver"))
                    intent.putExtra("srchEngine4","naver");
                else if(srchEngine4.contains("daum"))
                    intent.putExtra("srchEngine4","daum");
                intent.putExtra("srchbtn4",srchbtn4.getText().toString());
                startActivityForResult(intent, CODE);

                return false;
            }
        });

        //브라우저 검색
        srchbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchEngine4 + srchbtn4.getText().toString()));
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
            switch (data.getStringExtra("number")){
                case "1":
                    srchbtn1.setText(data.getStringExtra("srchword"+data.getStringExtra("number")));
                    srchEngine1 = data.getStringExtra("srchEngine"+data.getStringExtra("number"));
                    break;
                case "2":
                    srchbtn2.setText(data.getStringExtra("srchword"+data.getStringExtra("number")));
                    srchEngine2 = data.getStringExtra("srchEngine"+data.getStringExtra("number"));
                    break;
                case "3":
                    srchbtn3.setText(data.getStringExtra("srchword"+data.getStringExtra("number")));
                    srchEngine3 = data.getStringExtra("srchEngine"+data.getStringExtra("number"));
                    break;
                case "4":
                    srchbtn4.setText(data.getStringExtra("srchword"+data.getStringExtra("number")));
                    srchEngine4 = data.getStringExtra("srchEngine"+data.getStringExtra("number"));
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
