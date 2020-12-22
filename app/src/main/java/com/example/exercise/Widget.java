package com.example.exercise;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Implementation of App Widget functionality.
 */
public class Widget extends AppWidgetProvider {
    private final String ACTION_RE = "ButtonClick";
    private static String temp_word1;
    private static String temp_engine1;
    private static String temp_word2;
    private static String temp_engine2;
    private static String temp_word3;
    private static String temp_engine3;
    private static String temp_word4;
    private static String temp_engine4;



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("pref",0);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget);
            remoteViews.setTextViewText(R.id.wbtn1, sharedPreferences.getString("b1","실패"));

            remoteViews.setTextViewText(R.id.wbtn2, sharedPreferences.getString("b2","실패"));

            remoteViews.setTextViewText(R.id.wbtn3, sharedPreferences.getString("b3","실패"));

            remoteViews.setTextViewText(R.id.wbtn4, sharedPreferences.getString("b4","실패"));

            remoteViews.setTextViewText(R.id.txt, Html.fromHtml("<font color=\"#9999FF\">" + Integer.toString(sharedPreferences.getInt("i_k", -1)) + "</font>" + " 명 "
                    + "<font color=\"#FF9999\">" + Integer.toString(sharedPreferences.getInt("i_f", -1)) +"</font>" + " 명"))
            ;

            Intent intent_txt = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent_txt = PendingIntent.getActivity(context, 0, intent_txt, 0);
            remoteViews.setOnClickPendingIntent(R.id.txt, pendingIntent_txt);

            //버튼 새로고침
            Intent intent_re = new Intent(context, Widget.class).setAction(ACTION_RE);
            //Intent intent_re = new Intent(context, Widget.class).setAction(appWidgetManager.ACTION_APPWIDGET_UPDATE);
            PendingIntent pendingIntent_re = PendingIntent.getBroadcast(context,0, intent_re, 0);
            remoteViews.setOnClickPendingIntent(R.id.refresh, pendingIntent_re);

            //버튼1
            temp_word1 = sharedPreferences.getString("b1","tw1 불러오기 실패");
            temp_engine1 = sharedPreferences.getString("e1","https://m.google.com/search.google?q=");
            Intent intent_btn1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine1 + temp_word1));
            PendingIntent pendingIntent_btn1 = PendingIntent.getActivity(context,0, intent_btn1, 0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn1, pendingIntent_btn1);
            //버튼2
            temp_word2 = sharedPreferences.getString("b2","tw2 불러오기 실패");
            temp_engine2 = sharedPreferences.getString("e2","https://m.google.com/search.google?q=");
            Intent intent_btn2 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine2 + temp_word2));
            PendingIntent pendingIntent_btn2 = PendingIntent.getActivity(context,0, intent_btn2, 0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn2, pendingIntent_btn2);
            //버튼3
            temp_word3 = sharedPreferences.getString("b3","tw1 불러오기 실패");
            temp_engine3 = sharedPreferences.getString("e3","https://m.google.com/search.google?q=");
            Intent intent_btn3 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine3 + temp_word3));
            PendingIntent pendingIntent_btn3 = PendingIntent.getActivity(context,0, intent_btn3, 0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn3, pendingIntent_btn3);
            //버튼4
            temp_word4 = sharedPreferences.getString("b4","tw1 불러오기 실패");
            temp_engine4 = sharedPreferences.getString("e4","https://m.google.com/search.google?q=");
            Intent intent_btn4 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine4 + temp_word4));
            PendingIntent pendingIntent_btn4 = PendingIntent.getActivity(context,0, intent_btn4, 0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn4, pendingIntent_btn4);


            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName testWidget = new ComponentName(context.getPackageName(), Widget.class.getName());

        int[] widgetIds = appWidgetManager.getAppWidgetIds(testWidget);

        //주기적 또는 어플에서 새로고침 눌렀을때
        if(intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            context.getSharedPreferences("widget", Context.MODE_PRIVATE).edit().putInt("click", 0).commit();
            if(widgetIds != null && widgetIds.length >0) {
                this.onUpdate(context, AppWidgetManager.getInstance(context), widgetIds);
                //Toast.makeText(context, "새로고침 완료", Toast.LENGTH_SHORT).show();
            }
        }

        //위젯에서 새로고침 버튼 눌렀을때, 메세지 출력
        if(intent.getAction().equals(ACTION_RE)){
            context.getSharedPreferences("widget", Context.MODE_PRIVATE).edit().putInt("click", 0).commit();
            if(widgetIds != null && widgetIds.length >0) {
                this.onUpdate(context, AppWidgetManager.getInstance(context), widgetIds);
                Toast.makeText(context, "새로고침 완료", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

