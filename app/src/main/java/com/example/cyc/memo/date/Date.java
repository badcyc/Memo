package com.example.cyc.memo.date;

/**
 * Created by cyc on 17-9-16.
 */

public class Date {
    private String title;
    private String content;
    private String clockTime;
    private String editLast;
    private String createTime;
    private boolean clockIs;
    private String musicName;
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public String getClockTime(){
        return clockTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getEditLast() {
        return editLast;
    }

    public String getMusicName() {
        return musicName;
    }
    public boolean IsClock() {
        return clockIs;
    }

    public void setClockIs(boolean clockIs) {
        this.clockIs = clockIs;
    }

    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setEditLast(String editLast) {
        this.editLast = editLast;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setIsClock(boolean clockIs){
        this.clockIs=clockIs;
    }

    public Date(String createTime,String title,
            String content,
            String clockTime,
            String editLast,boolean clockIs,
            String musicName){
        this.clockIs=clockIs;
        this.clockTime=clockTime;
        this.content=content;
        this.createTime=createTime;
        this.editLast=editLast;
        this.musicName=musicName;
        this.title=title;
    }
    public static String TITLE="title";
    public static String CONTENT="content";
    public static String CREATE_TIME="create_time";
    public static String CLOCK_IS="clock_is";
    public static String CLOCK_TIME="clock_time";
    public static String MUSIC_NAME="music_name";
    public static String EDIT_LAST="edit_last";
}
