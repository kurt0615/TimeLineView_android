package com.example.kurt.timelistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

public class TimeLineView extends View {

    private int mHeaderWidth = 70;
    private int mHourHeight = 120;
    private int mLabelTextSize = 20;
    private int mLabelPaddingLeft = 6;
    private int mLabelColor = Color.GRAY;
    private int mDividerColor = Color.LTGRAY;
    private int mDividerBlockColor = Color.TRANSPARENT;
    private int mStartHour = 0;
    private int mEndHour = 24;

    public TimeLineView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public int getTimeVerticalOffset(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        minutes = minutes + hours * 60;
        int offset = (mHourHeight*minutes) / 60;
        return offset;
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int hours = mEndHour - mStartHour;

        int width = mHeaderWidth;
        int height = mHourHeight * hours;

        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }

    private Paint mDividerPaint = new Paint();
    private Paint mDividerBlockPaint = new Paint();
    private Paint mLabelPaint = new Paint();

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int hourHeight = mHourHeight;
        final Paint dividerPaint = mDividerPaint;
        dividerPaint.setColor(mDividerColor);
        dividerPaint.setStyle(Style.FILL);
        dividerPaint.setStrokeWidth(2.5f);

        final Paint dividerBlockPaint = mDividerBlockPaint;
        dividerBlockPaint.setColor(mDividerBlockColor);
        dividerBlockPaint.setStyle(Style.FILL);

        final Paint labelPaint = mLabelPaint;
        labelPaint.setColor(mLabelColor);
        labelPaint.setTextSize(mLabelTextSize);
        labelPaint.setAntiAlias(true);
        labelPaint.setFakeBoldText(true);

        final Paint.FontMetricsInt metrics = labelPaint.getFontMetricsInt();
        final int labelHeight = Math.abs(metrics.ascent);
        final int labelOffset = labelHeight;
        final int right = getRight();

        final int hours = mEndHour - mStartHour;
        for (int i = 0; i <= hours; ++i) {
            final int dividerY = hourHeight * i;
            final int nextDividerY = hourHeight * (i + 1);

            //divide line
            canvas.drawLine(80, dividerY, right, dividerY, dividerPaint);

            //left time_label block
            canvas.drawRect(0, dividerY, mHeaderWidth, nextDividerY, dividerBlockPaint);

            final int hour = mStartHour + i;
            String label;
            if (hour == 0) {
                label = "12 am";
            } else if (hour <= 11) {
                label = hour + " am";
            } else if (hour == 12) {
                label = "12 pm";
            } else {
                label = (hour - 12) + " pm";
            }

            final float labelWidth = labelPaint.measureText(label);
            canvas.drawText(label, 0, label.length(), mHeaderWidth - labelWidth - mLabelPaddingLeft, dividerY + labelOffset, labelPaint);
        }
    }

    public int getHeaderWidth() {
        return mHeaderWidth;
    }
}
