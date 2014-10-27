package com.example.kurt.timelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Date;


public class BlockContainer extends ViewGroup {

    private CallBacks mContext;
    interface CallBacks{
        public void didLayout(Date date);
    }

    public void setListener(Context context) {
        if (context instanceof CallBacks){
            mContext = (CallBacks)context;
        }
    }

    private int mColumns = 1;

    public BlockContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private TimeLineView getTimeline() {
        return  (TimeLineView) findViewById(R.id.timeLine);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        TimeLineView timeLineView = getTimeline();
        timeLineView.measure(widthMeasureSpec, heightMeasureSpec);

        final int width = timeLineView.getMeasuredWidth();
        final int height = timeLineView.getMeasuredHeight();

        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final TimeLineView timeLineView = getTimeline();
        timeLineView.layout(0, 0, getWidth(), getHeight());

        final int headerWidth = timeLineView.getHeaderWidth();
        final int columnWidth = (getWidth() - headerWidth) / mColumns;

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child instanceof EventStamp) {
                final EventStamp eventStamp = (EventStamp) child;
                final int top = timeLineView.getTimeVerticalOffset(eventStamp.getStartTime()) - 5;
                final int bottom = timeLineView.getTimeVerticalOffset(eventStamp.getEndTime()) + 5;
                final int left = headerWidth + 15;
                final int right = left + columnWidth - 20;
                child.layout(left, top, right, bottom);

                if (getChildCount() > 1 && getChildCount() == i + 1){
                    final View firstChild = getChildAt(1);
                    if (firstChild instanceof EventStamp) {
                        final EventStamp firstEventStamp = (EventStamp)firstChild;
                        mContext.didLayout(firstEventStamp.getStartTime());
                    }
                }
            }
        }
    }
}
