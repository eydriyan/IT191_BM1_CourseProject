package com.it191.view.objects;

import java.util.EventObject;

public class SongEvent extends EventObject {

    private String title;
    private String artist;
    private String imgPath;
    private String lyrics;
 
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

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getLyrics() {
        return lyrics;
    }
    
    
}
