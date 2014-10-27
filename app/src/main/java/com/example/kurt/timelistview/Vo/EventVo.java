package com.example.kurt.timelistview.Vo;

//    "isOwner": "是否為本人預約"
//    "MRBookingID": "會議室預約編號",
//    "MeetingStartTime": "會議室預約開始時間",
//    "MeetingEndTime": "會議室預約結束時間",
//    "ReservedUserID": "會議室預約使用人",
//    "EXT": "預約使用人分機",
//    "Topic": "會議室預約主旨",
//    "Site ": "會議室據點",
//    "MRID ": "會議室",
//    "MeetingDate ": "會議室預約日期"

public class EventVo {

    private String mIsOwner;
    private String mMRBookingID;
    private String mMeetingStartTime;
    private String mMeetingEndTime;
    private String mReservedUserID;
    private String mExt;
    private String mTopic;
    private String mSite;
    private String mMRID;
    private String mMeetingDate;

    public String getIsOwner() {
        return mIsOwner;
    }

    public void setIsOwner(String mIsOwner) {
        this.mIsOwner = mIsOwner;
    }

    public String getMRBookingID() {
        return mMRBookingID;
    }

    public void setMRBookingID(String mMRBookingID) {
        this.mMRBookingID = mMRBookingID;
    }

    public String getMeetingStartTime() {
        return mMeetingStartTime;
    }

    public void setMeetingStartTime(String mMeetingStartTime) {
        this.mMeetingStartTime = mMeetingStartTime;
    }

    public String getMeetingEndTime() {
        return mMeetingEndTime;
    }

    public void setMeetingEndTime(String mMeetingEndTime) {
        this.mMeetingEndTime = mMeetingEndTime;
    }

    public String getReservedUserID() {
        return mReservedUserID;
    }

    public void setReservedUserID(String mReservedUserID) {
        this.mReservedUserID = mReservedUserID;
    }

    public String getExt() {
        return mExt;
    }

    public void setExt(String mExt) {
        this.mExt = mExt;
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String mTopic) {
        this.mTopic = mTopic;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String mSite) {
        this.mSite = mSite;
    }

    public String getMRID() {
        return mMRID;
    }

    public void setMRID(String mMRID) {
        this.mMRID = mMRID;
    }

    public String getMeetingDate() {
        return mMeetingDate;
    }

    public void setMeetingDate(String mMeetingDate) {
        this.mMeetingDate = mMeetingDate;
    }
}
