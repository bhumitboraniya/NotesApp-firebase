package com.example.notes199;
public class Note
{

    String title;
    String description;
    String imagePath;
    long createdTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
    public  void setImagePath(String imagePath){
        this.imagePath = imagePath ;
    }
    public  String getImagePath(){
        return  imagePath;
    }


    public Note(String title, String description, String imagePath) {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
    }
}

