package com.it191.model;

import java.util.ArrayList;

public class CollectionModel {
    private int collectionId;
    private String title;
    private ArrayList<SongModel> songs;

    
    public int getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
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
