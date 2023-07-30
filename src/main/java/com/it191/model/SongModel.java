package com.it191.model;

public class SongModel {
    private int songId;
    private String title;
    private String artist;
    private String lyrics;
    private int duration;
    private String songPath;
    private String imgPath;

    public SongModel() {}

    // For DEBUG
    public SongModel(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
    
    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getLyrics() {
        return lyrics;
    }
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getSongPath() {
        return songPath;
    }
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
