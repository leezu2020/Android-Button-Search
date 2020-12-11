package com.example.exercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_setting extends AppCompatActivity {

    private Button backbtn;
    final static int CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);

        backbtn = findViewById(R.id.back_main);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_setting.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void GetSearchEdit(View view){

        switch (view.getId()){
            case R.id.searchBtn1:
                Button btn = findViewById(R.id.searchBtn1);
                Intent intent = new Intent(activity_setting.this, popupactivity.class);
                intent.putExtra("srchword", btn.getText().toString());
                startActivity(intent);

                break;
        }
/*        AlertDialog.Builder alert = new AlertDialog.Builder(activity_setting.this);

        alert.setTitle("검색어 변경");
        alert.setMessage("새로운 검색어를 입력해주세요.");

        final EditText search = new EditText(this);
        alert.setView(search);

        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String srchbtn = search.getText().toString();
            }
        });

        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });*/


    }

}
