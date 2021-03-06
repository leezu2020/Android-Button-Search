package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static int CODE = 1;
    private TextView main_txt;
    private Button srchbtn1;
    private Button srchbtn2;
    private Button srchbtn3;
    private Button srchbtn4;
    private static String srchEngine1 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine2 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine3 = "http:/m.search.naver.com/search.naver?query=";
    private static String srchEngine4 = "http:/m.search.naver.com/search.naver?query=";

    private final long FINISH_INTERVAL_TIME = 1500;
    private long backPressedTime = 0;

    // 0은 국내 확진자 1은 해외 확진자
    private int[] infected = new int[2];
    private String infected_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srchbtn1 = findViewById(R.id.searchBtn1);
        srchbtn2 = findViewById(R.id.searchBtn2);
        srchbtn3 = findViewById(R.id.searchBtn3);
        srchbtn4 = findViewById(R.id.searchBtn4);

        SharedPreferences pref = getSharedPreferences("pref",0);
        srchbtn1.setText(pref.getString("b1","기본"));
        srchEngine1 = pref.getString("e1","http:/m.search.naver.com/search.naver?query=");
        srchbtn2.setText(pref.getString("b2","검색엔진은"));
        srchEngine2 = pref.getString("e2","http:/m.search.naver.com/search.naver?query=");
        srchbtn3.setText(pref.getString("b3","네이버"));
        srchEngine3 = pref.getString("e3","http:/m.search.naver.com/search.naver?query=");
        srchbtn4.setText(pref.getString("b4","입니다."));
        srchEngine4 = pref.getString("e4","http:/m.search.naver.com/search.naver?query=");


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

        getWebsite();
        //텍스트 버튼(크롤링)
        main_txt = findViewById(R.id.main_txt);
        main_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWebsite();
            }
        });
    }

    //크롤링 연습
    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();


                try{
                    Document doc = Jsoup.connect("http://ncov.mohw.go.kr/").get();
                    String title = doc.title();
                    Elements links = doc.select("div.datalist ul li");

                    builder.append(title).append("\n");

                    int i=0;
                    for(Element link : links){
                        String temp = link.select("span.data").text();
                        infected[i++] = Integer.parseInt(temp);
                        builder.append("\n").append(temp);
                    }
                    Elements date = doc.select("div.liveNumOuter h2 a span.livedate");
                    infected_date = date.text().split(",")[0].replace("(","");


                }catch (IOException e){
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        main_txt.setText(Html.fromHtml("<font color=\"#000000\">" + infected_date + "</font><br />" + "<font color=\"#000000\">" + Integer.toString(infected[0] + infected[1]) + "</font>"+ "명" + "<br />국내 감염자 수<br />"+"<font color=\"#9999FF\">" + Integer.toString(infected[0]) + "</font> 명"
                        + "<br />해외 감염자 수<br /><font color=\"#FF9999\">" + Integer.toString(infected[1]) +"</font> 명"));
                        /*Html.fromHtml("<font size='40' color=\"#9999FF\">" + Integer.toString(sharedPreferences.getInt("i_k", -1)) + "</font>" + " 명 "
                                + "<font size='40' color=\"#FF9999\">" + Integer.toString(sharedPreferences.getInt("i_f", -1)) +"</font>" + " 명")*/
                    }
                });
            }
        }).start();
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

    // 설정 후에 최소화 시켰을때를 대비
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        SharedPreferences sharedPreferences = getSharedPreferences("pref",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String btn1 = srchbtn1.getText().toString();
        String Engine1 = srchEngine1;
        String btn2 = srchbtn2.getText().toString();
        String Engine2 = srchEngine2;
        String btn3 = srchbtn3.getText().toString();
        String Engine3 = srchEngine3;
        String btn4 = srchbtn4.getText().toString();
        String Engine4 = srchEngine4;

        editor.putString("b1",btn1);
        editor.putString("b2",btn2);
        editor.putString("b3",btn3);
        editor.putString("b4",btn4);
        editor.putString("e1",Engine1);
        editor.putString("e2",Engine2);
        editor.putString("e3",Engine3);
        editor.putString("e4",Engine4);

        //감염자 수 저장
        editor.putString("i_d",infected_date);
        editor.putInt("i_k",infected[0]);
        editor.putInt("i_f",infected[1]);

        editor.commit();

        //최소화했을때 자동 업데이트
        Intent intent = new Intent(MainActivity.this, Widget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        MainActivity.this.sendBroadcast(intent);
    }
    // 앱 종료 후에 설정 값을 pref에 저장
    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences("pref",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String btn1 = srchbtn1.getText().toString();
        String Engine1 = srchEngine1;
        String btn2 = srchbtn2.getText().toString();
        String Engine2 = srchEngine2;
        String btn3 = srchbtn3.getText().toString();
        String Engine3 = srchEngine3;
        String btn4 = srchbtn4.getText().toString();
        String Engine4 = srchEngine4;

        editor.putString("b1",btn1);
        editor.putString("b2",btn2);
        editor.putString("b3",btn3);
        editor.putString("b4",btn4);
        editor.putString("e1",Engine1);
        editor.putString("e2",Engine2);
        editor.putString("e3",Engine3);
        editor.putString("e4",Engine4);

        //감염자 수 저장
        editor.putString("i_d",infected_date);
        editor.putInt("i_k",infected[0]);
        editor.putInt("i_f",infected[1]);

        editor.commit();

        //종료했을때 자동 업데이트
        Intent intent = new Intent(MainActivity.this, Widget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        MainActivity.this.sendBroadcast(intent);
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(0<=intervalTime && FINISH_INTERVAL_TIME >= intervalTime){
            super.onBackPressed();
        } else{
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
}
