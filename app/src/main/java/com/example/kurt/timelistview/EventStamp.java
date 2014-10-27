package com.example.kurt.timelistview;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

import com.example.kurt.timelistview.Vo.EventVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kurt on 2014/10/7.
 */
public class EventStamp extends Button {

    private final EventVo mEventVo;
    private final String mTitle;
    private final Date mStartTime;
    private final Date mEndTime;

    public EventStamp(Context context, EventVo eventVo) {
        super(context);

        mEventVo = eventVo;
        mTitle = eventVo.getTopic();
        mStartTime = stringToDate(eventVo.getMeetingStartTime(), "HH:mm");
        mEndTime = stringToDate(eventVo.getMeetingEndTime(), "HH:mm");

        setText(mTitle);
        setTextColor(Color.WHITE);
        setBackgroundResource(R.drawable.event_stamp_block);
    }

    public EventVo getEventInfo () {
        return mEventVo;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    private Date stringToDate(String dateString, String formate) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formate);//HH:mm
        //進行轉換
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {

        }finally {
            return date;
        }
    }
}
