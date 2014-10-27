package com.example.kurt.timelistview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kurt.timelistview.Vo.EventVo;
import com.example.kurt.timelistview.Vo.LocationVo;
import com.example.kurt.timelistview.Vo.RoomVo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MyActivity extends Activity implements BlockContainer.CallBacks{

    private DateDisplayPicker dateFrom;
    private List<EventVo> eventVoList = new ArrayList<EventVo>();
    private BlockContainer blockContainer;
    private ArrayList<LocationVo> locationDataSource;
    private ArrayList<RoomVo> roomDataSource;
    private Spinner locationSpinner = null;
    private Spinner roomSpinner = null;
    private SpinnerAdapter locationAdapter;
    private SpinnerAdapter roomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        dateFrom = (DateDisplayPicker) findViewById(R.id.dateFrom);
        Calendar calendar = Calendar.getInstance();
        dateFrom.setText(calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH));

        blockContainer = getBlockContainer();
        blockContainer.setListener(this);

        locationDataSource = new ArrayList<LocationVo>();
        LocationVo data11 = new LocationVo();
        data11.setId("886");
        data11.setLocationName("Taiwan");
        LocationVo data22 = new LocationVo();
        data22.setId("881");
        data22.setLocationName("China");
        locationDataSource.add(data11);
        locationDataSource.add(data22);

        locationAdapter = new SpinnerAdapter(this,locationDataSource);
        locationSpinner = (Spinner)findViewById(R.id.location);
        locationSpinner.setAdapter(locationAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(MyActivity.this,String.valueOf(id),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        roomDataSource = new ArrayList<RoomVo>();
        RoomVo data111 = new RoomVo();
        data111.setId("101");
        data111.setRoomName("Room 101");
        RoomVo data222 = new RoomVo();
        data222.setId("202");
        data222.setRoomName("Room 202");
        roomDataSource.add(data111);
        roomDataSource.add(data222);

        roomAdapter = new SpinnerAdapter(this,roomDataSource);
        roomSpinner = (Spinner)findViewById(R.id.room);
        roomSpinner.setAdapter(roomAdapter);
        roomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyActivity.this,String.valueOf(id),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        // for test
        EventVo data1 = new EventVo();
        data1.setMRBookingID("iosapp id");
        data1.setMeetingStartTime("05:25");
        data1.setMeetingEndTime("08:30");
        data1.setTopic("IOS App Meeting");
        data1.setIsOwner("Kurt");
        data1.setExt("3214");

        eventVoList.add(data1);

        EventVo data2 = new EventVo();
        data2.setMRBookingID("webapp id");
        data2.setMeetingStartTime("09:25");
        data2.setMeetingEndTime("12:30");
        data2.setTopic("Web App Meeting");
        data2.setIsOwner("Kurt");
        data2.setExt("3214");

        eventVoList.add(data2);


        addEventStampToTimeline(eventVoList);

        // for test
    }

    public ViewGroup getViewRoot() {
        return (ViewGroup) findViewById(R.id.blockContainer);
    }

    public BlockContainer getBlockContainer() {
        return (BlockContainer) findViewById(R.id.blockContainer);
    }

    public LinearLayout getBottomBarPhoneItem() {
        return (LinearLayout) findViewById(R.id.bottomBarPhone);
    }

    public LinearLayout getBottomBarDetailItem() {
        return (LinearLayout) findViewById(R.id.bottomBarDetail);
    }

    public LinearLayout getBottomBarModalView() {
        return (LinearLayout) findViewById(R.id.bottomBarModalView);
    }

    public DateDisplayPicker getDateDisplayPicker() {
        return (DateDisplayPicker) findViewById(R.id.dateFrom);
    }

    public void addEventStampToTimeline(List<EventVo> datas) {

        ViewGroup viewRoot = getViewRoot();

        for (EventVo data : datas) {
            final EventStamp eventStamp = new EventStamp(this, data);
            eventStamp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //close keyboard
                    //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(MyActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    //set BottomBar item click action
                    getBottomBarPhoneItem().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String phoneNumber = eventStamp.getEventInfo().getExt();
                            Toast.makeText(MyActivity.this, phoneNumber, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + phoneNumber));
                            startActivity(intent);
                        }
                    });

                    getBottomBarDetailItem().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String eventId = eventStamp.getEventInfo().getMRBookingID();
                            Toast.makeText(MyActivity.this, eventId, Toast.LENGTH_SHORT).show();
                        }
                    });

                    //show BottomBar and set click action
                    LinearLayout bottomBarModalView = getBottomBarModalView();
                    bottomBarModalView.setVisibility(View.VISIBLE);
                    bottomBarModalView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.GONE);
                        }
                    });
                    bottomBarModalView.requestFocus();
                }
            });
            viewRoot.addView(eventStamp);
        }
    }

    public void search(View view) {

        long locationId = locationSpinner.getSelectedItemId();
        long roomId = roomSpinner.getSelectedItemId();
        DateDisplayPicker datePicker = getDateDisplayPicker();
        String date = datePicker.getText().toString();


        resetViewRootAndDataSource();

        // for test
        EventVo data = new EventVo();
        data.setMRBookingID("androidapp id");
        data.setMeetingStartTime("03:00");
        data.setMeetingEndTime("04:30");
        data.setTopic("Android App Meeting");
        data.setIsOwner("Kurt");
        data.setExt("3214");

        eventVoList.add(data);

        addEventStampToTimeline(eventVoList);
        // for test
    }

    public void resetViewRootAndDataSource() {
        eventVoList.clear();
        final ViewGroup viewRoot = getViewRoot();
        viewRoot.removeViews(1, viewRoot.getChildCount() - 1);
    }

    public void setScrollBarPosition(Date date) {
        TimeLineView timeLineView = (TimeLineView) findViewById(R.id.timeLine);
        final int offset = timeLineView.getTimeVerticalOffset(date) - 20;
        final ScrollView scrollView = (ScrollView) findViewById(R.id.searchResultScroll);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, offset);
            }
        });
    }

    @Override
    public void didLayout(Date date) {
        setScrollBarPosition(date);
    }
}
