package com.example.getsize;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetDemo extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		// timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager),
		// 1,60000);
		timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1,
				1000);
		/**点击事件*********************/
		for (int i = 0; i < appWidgetIds.length; i++) {
			//这里TargetActivity为Intent跳转到的Activity，这个Activity类需要另外编写，在第三步创建
			Intent intent = new Intent(context,Abc.class);
			PendingIntent GpendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			RemoteViews GremoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_main);
			//PendingIntent为事件触发是所要执行的PendingIntent
			GremoteViews.setOnClickPendingIntent(R.id.wordcup, GpendingIntent);
			appWidgetManager.updateAppWidget(appWidgetIds[i], GremoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	private class MyTime extends TimerTask {
		RemoteViews remoteViews;
		AppWidgetManager appWidgetManager;
		ComponentName thisWidget;
		Context mContext;

		public MyTime(Context context, AppWidgetManager appWidgetManager) {
			this.appWidgetManager = appWidgetManager;
			remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.activity_main);
			thisWidget = new ComponentName(context, WidgetDemo.class);
			mContext = context;
		}

		public void run() {
			Date date = new Date();
			Calendar calendar = new GregorianCalendar(2020, 06, 11);
			long days = (((calendar.getTimeInMillis() - date.getTime()) / 1000)) / 86400;
			long second = ((calendar.getTimeInMillis() - date.getTime()) / 1000);
			// remoteViews.setTextViewText(R.id.wordcup, "距离南非世界杯还有" + days +
			// "天");
			remoteViews.setTextViewText(R.id.wordcup, "距离南非世界杯还有" + second
					+ "秒");
			appWidgetManager.updateAppWidget(thisWidget, remoteViews);

		}
	}
}
