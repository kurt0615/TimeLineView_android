package com.example.kurt.timelistview;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class DateDisplayPicker extends TextView /*implements DatePickerDialog.OnDateSetListener*/{
	
	private Context _context;
	private ResourceApp rApp;
	private Boolean btnState;
    private String selectedDate = "";
	 
    public DateDisplayPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _context = context;
        rApp = (ResourceApp)((Activity)_context).getApplicationContext();
    }
 
    public DateDisplayPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        rApp = (ResourceApp)((Activity)_context).getApplicationContext();
        setAttributes();
    }
 
    public DateDisplayPicker(Context context) {
        super(context);
        _context = context;
        rApp = (ResourceApp)((Activity)_context).getApplicationContext();
        setAttributes();
    }
 
    private void setAttributes() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }
    
    private DatePickerDialog.OnDateSetListener DatePickerListener = new DatePickerDialog.OnDateSetListener(){
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			if(btnState){
                selectedDate = String.format("%s/%s/%s", year, monthOfYear+1, dayOfMonth);
				setText(selectedDate);
			}
		}
	};
 
    private void showDateDialog() {
        String date = getText().toString().replace("/", "-");
        DatePickerDialog picker = new DatePickerDialog(_context, DatePickerListener,
        		rApp.year(date), rApp.month(date)-1, rApp.day(date));

    	picker.setCancelable(false);
    	picker.setCanceledOnTouchOutside(false);
    	picker.setButton(DialogInterface.BUTTON_POSITIVE, "確定",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                	btnState = true;
                }
            });

        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                	btnState = false;
                }
            });
        picker.show();
    }

    public String getSelectedDate() {
        return selectedDate;
    }
}
