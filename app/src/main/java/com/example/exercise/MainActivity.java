package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button settingbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingbtn = findViewById(R.id.settingBtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_setting.class);
                startActivity(intent);
            }
        });
    }

    public void GetSearch1(View view){
        Button btn = (Button)findViewById(R.id.searchBtn1);
        String srchString = btn.getText().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:/m.search.naver.com/search.naver?query="+srchString));
        startActivity(browserIntent);
    }
    public void GetSearch2(View view){
        Button btn = (Button)findViewById(R.id.searchBtn2);
        String srchString = btn.getText().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:/m.search.naver.com/search.naver?query="+srchString));
        startActivity(browserIntent);
    }
    public void GetSearch3(View view){
        Button btn = (Button)findViewById(R.id.searchBtn3);
        String srchString = btn.getText().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:/m.search.naver.com/search.naver?query="+srchString));
        startActivity(browserIntent);
    }
    public void GetSearch4(View view){
        Button btn = (Button)findViewById(R.id.searchBtn4);
        String srchString = btn.getText().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:/m.search.naver.com/search.naver?query="+srchString));
        startActivity(browserIntent);
    }

}
