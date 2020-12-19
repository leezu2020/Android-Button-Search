package com.example.exercise;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
    private final String ACTION_BTN1 = "ButtonClick1";
    private final String ACTION_BTN2 = "ButtonClick2";
    private final String ACTION_BTN3 = "ButtonClick3";
    private final String ACTION_BTN4 = "ButtonClick4";
    private static String temp_word1;
    private static String temp_engine1;
    private static String temp_word2;
    private static String temp_engine2;
    private static String temp_word3;
    private static String temp_engine3;
    private static String temp_word4;
    private static String temp_engine4;

    private final long FINISH_INTERVAL_TIME = 1000;

    private static String uri1;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget);

            SharedPreferences sharedPreferences = context.getSharedPreferences("pref",0);
            remoteViews.setTextViewText(R.id.wbtn1, sharedPreferences.getString("b1","실패"));
            remoteViews.setTextViewText(R.id.wbtn2, sharedPreferences.getString("b2","실패"));
            remoteViews.setTextViewText(R.id.wbtn3, sharedPreferences.getString("b3","실패"));
            remoteViews.setTextViewText(R.id.wbtn4, sharedPreferences.getString("b4","실패"));

            Intent intent_re = new Intent(context, Widget.class).setAction(ACTION_RE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent_re, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.refresh, pendingIntent);

            Intent intent_btn1 = new Intent(context, Widget.class).setAction(ACTION_BTN1);
            PendingIntent pendingIntent_btn1 = PendingIntent.getBroadcast(context,0, intent_btn1, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.wbtn1, pendingIntent_btn1);

            Intent intent_btn2 = new Intent(context, Widget.class).setAction(ACTION_BTN2);
            PendingIntent pendingIntent_btn2 = PendingIntent.getBroadcast(context,0, intent_btn2, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.wbtn2, pendingIntent_btn2);

            Intent intent_btn3 = new Intent(context, Widget.class).setAction(ACTION_BTN3);
            PendingIntent pendingIntent_btn3 = PendingIntent.getBroadcast(context,0, intent_btn3, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.wbtn3, pendingIntent_btn3);

            Intent intent_btn4 = new Intent(context, Widget.class).setAction(ACTION_BTN4);
            PendingIntent pendingIntent_btn4 = PendingIntent.getBroadcast(context,0, intent_btn4, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.wbtn4, pendingIntent_btn4);


            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // 새로고침(refresh)버튼 눌렀을때, 버튼 4개 갱신
        if(action.equals(ACTION_RE)){
            //int clickCount = context.getSharedPreferences("cc",Context.MODE_PRIVATE).getInt("clicks",0);
            //context.getSharedPreferences("cc", Context.MODE_PRIVATE).edit().putInt("clicks", ++clickCount).commit();
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - context.getSharedPreferences("cc", Context.MODE_PRIVATE).getLong("rePressedTime",0);
            //두번 클릭했을때
            if(0<=intervalTime && FINISH_INTERVAL_TIME >= intervalTime){
                Toast.makeText(context,"더블클릭 성공." + " "+Long.toString(intervalTime), Toast.LENGTH_SHORT).show();
                context.getSharedPreferences("cc", Context.MODE_PRIVATE).edit().putLong("rePressedTime", (long)0).commit();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName componentName = new ComponentName(context, Widget.class);
                Intent intent_tap_re = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent_tap_re = PendingIntent.getActivity(context, 0, intent_tap_re, 0);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                remoteViews.setOnClickPendingIntent(R.id.refresh, pendingIntent_tap_re);
                appWidgetManager.updateAppWidget(componentName, remoteViews);
                //this.onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass())));
                /*PendingIntent pendingIntent_tap_re = PendingIntent.getActivity(context, 0, intent_tap_re, 0);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                remoteViews.setOnClickPendingIntent(R.id.refresh, pendingIntent_tap_re);
                appWidgetManager.updateAppWidget(componentName, remoteViews);*/
            } else {
                context.getSharedPreferences("cc",Context.MODE_PRIVATE).edit().putLong("rePressedTime",System.currentTimeMillis()).commit();
                SharedPreferences pref = context.getSharedPreferences("pref", 0);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                ComponentName componentName = new ComponentName(context, Widget.class);
                temp_word1 = pref.getString("b1", "실패");
                remoteViews.setTextViewText(R.id.wbtn1, temp_word1);
                temp_word2 = pref.getString("b2", "실패");
                remoteViews.setTextViewText(R.id.wbtn2, temp_word2);
                temp_word3 = pref.getString("b3", "실패");
                remoteViews.setTextViewText(R.id.wbtn3, temp_word3);
                temp_word4 = pref.getString("b4", "실패");
                remoteViews.setTextViewText(R.id.wbtn4, temp_word4);
                Toast.makeText(context, "새로고침 완료"  +" "+ Long.toString(intervalTime), Toast.LENGTH_SHORT).show();
                //appWidgetManager.updateAppWidget(componentName, remoteViews);
                //이거 쓰면 새로고침 누를때마다 한번 더 눌러주세요 뜸.
                this.onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass())));
            }
        }

        if(action.equals(ACTION_BTN1)){
            SharedPreferences pref = context.getSharedPreferences("pref",0);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            ComponentName componentName = new ComponentName(context, Widget.class);
            temp_word1 = pref.getString("b1","실패했다 또");
            temp_engine1 = pref.getString("e1","http:/m.search.naver.com/search.naver?query=");
            Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine1 + temp_word1));
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn1, pendingIntent);
            Toast.makeText(context,"한번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }

        if(action.equals(ACTION_BTN2)){
            SharedPreferences pref = context.getSharedPreferences("pref",0);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            ComponentName componentName = new ComponentName(context, Widget.class);
            temp_word2 = pref.getString("b2","실패했다 또");
            temp_engine2 = pref.getString("e2","http:/m.search.naver.com/search.naver?query=");
            Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine2 + temp_word2));
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn2, pendingIntent);
            Toast.makeText(context,"한번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }

        if(action.equals(ACTION_BTN3)){
            SharedPreferences pref = context.getSharedPreferences("pref",0);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            ComponentName componentName = new ComponentName(context, Widget.class);
            temp_word3 = pref.getString("b3","실패했다 또");
            temp_engine3 = pref.getString("e3","http:/m.search.naver.com/search.naver?query=");
            Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine3 + temp_word3));
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn3, pendingIntent);
            Toast.makeText(context,"한번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
        if(action.equals(ACTION_BTN4)){
            SharedPreferences pref = context.getSharedPreferences("pref",0);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            ComponentName componentName = new ComponentName(context, Widget.class);
            temp_word4 = pref.getString("b4","실패했다 또");
            temp_engine4 = pref.getString("e4","http:/m.search.naver.com/search.naver?query=");
            Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(temp_engine4 + temp_word4));
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
            remoteViews.setOnClickPendingIntent(R.id.wbtn4, pendingIntent);
            Toast.makeText(context,"한번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
        super.onReceive(context, intent);
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

