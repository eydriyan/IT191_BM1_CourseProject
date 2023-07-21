package com.it191.model;

public class MusicModel {
    public int id;
    public String title;
    public float duration;
    public UserModel artist;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public float getDuration() {
        return duration;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }
    public UserModel getArtist() {
        return artist;
    }
    public void setArtist(UserModel artist) {
        this.artist = artist;
    }
}
