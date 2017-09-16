package com.example.cyc.memo;

/**
 * Created by cyc on 17-9-14.
 */

public class Category {
    public String title;
    public int number;
    public String getTitle(){
        return title;
    }
    public int getNumber(){
        return number;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setNumber(int number){
        this.number=number;
    }
    public Category(String title,int number){
        this.title=title;
        this.number=number;
    }
}
