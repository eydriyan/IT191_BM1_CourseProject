package com.it191.model;

import java.util.ArrayList;

public class AlbumModel {
    public int albumId;
    public String title;
    public ArrayList<SongModel> songs;


    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<SongModel> getSongs() {
        return songs;
    }
    public void setSongs(ArrayList<SongModel> songs) {
        this.songs = songs;
    }
}
