package com.example.kurt.timelistview;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Application;

public class ResourceApp extends Application {
	
	private String currentDate;
	
	@SuppressLint("SimpleDateFormat")
	public static String dayIsToday() {
    	String[] days = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
    	SimpleDateFormat format = new SimpleDateFormat("MM月dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return format.format(c.getTime()) + days[dayForWeek-1];
    }
	
	public static String dateFormat(String value){
    	int y = Integer.parseInt(value.substring(0,value.indexOf("-")));
    	int m = Integer.parseInt(value.substring(value.indexOf("-")+1, value.lastIndexOf("-")));
    	int d = Integer.parseInt(value.substring(value.lastIndexOf("-")+1));
    	return y+"-"+m+"-"+d;  	
    }
	
	public int year(String value){
    	return Integer.parseInt(value.substring(0,value.indexOf("-")));
    }
    
    public int month(String value){
    	return Integer.parseInt(value.substring(value.indexOf("-")+1, value.lastIndexOf("-")));
    }
    
    public int day(String value){
    	return Integer.parseInt(value.substring(value.lastIndexOf("-")+1));
    }
    
    public int hour(String value){
    	String type = value.substring(0, 2);
    	int hour = Integer.parseInt(value.substring(3,value.indexOf(":")));
    	
    	if(type.equals("下午"))
    		return hour+12;
    	else
    		return hour;
    	
    }
    
    public String hourAmPm(int value){
    	if(value > 12)
    		return "下午 " + (value-12);
    	else if(value == 12)
    		return "中午 " + value;
    	else
    		return "上午 " + value;
    }
    
    public int minute(String value){
    	return Integer.parseInt(value.substring(value.lastIndexOf(":")+1));
    }

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = year(currentDate) + "-" + month(currentDate) + "-" + day(currentDate);
	}
	
}
