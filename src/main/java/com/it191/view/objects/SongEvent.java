package com.it191.view.objects;

import java.util.EventObject;

public class SongEvent extends EventObject {

    private int songId;
    private String title;
    private String artist;
    private String lyrics;
    private int duration;
    private String songPath;
    private String imgPath;
 
    public SongEvent(Object source) {
        super(source);
        //TODO Auto-generated constructor stub
    }

    public SongEvent(Object source, String title, String artist, String imgPath, String lyrics) {
        super(source);
        this.title = title;
        this.artist = artist;
        this.imgPath = imgPath;
        this.lyrics = lyrics;
    }
    

    public SongEvent(Object source, int songId, String title, String artist, String lyrics, int duration,
            String songPath, String imgPath) {
        super(source);
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.lyrics = lyrics;
        this.duration = duration;
        this.songPath = songPath;
        this.imgPath = imgPath;
    }

    public int getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getLyrics() {
        return lyrics;
    }

    public int getDuration() {
        return duration;
    }

    public String getSongPath() {
        return songPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    
}
