package com.it191.model;

public class SongModel {
    public int songId;
    public String title;
    public String genre;
    public String artist;
    public String lyrics;
    public int duration;
    public String song_path;
    public String img_path;


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
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
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
    public String getSong_path() {
        return song_path;
    }
    public void setSong_path(String song_path) {
        this.song_path = song_path;
    }
    public String getImg_path() {
        return img_path;
    }
    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
